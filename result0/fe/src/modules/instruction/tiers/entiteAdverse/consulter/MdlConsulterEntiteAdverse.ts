import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IEntiteAdverse } from 'modele/tiers/entiteAdverse/DomaineEntiteAdverse';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterEntiteAdverse from './CtrlConsulterEntiteAdverse';

export interface ReqConsulterEntiteAdverse extends IRequete {
    idEntiteAdverse?: string;
    idSinistre?: string;
}

export interface ResConsulterEntiteAdverse extends IResultat {
    entiteAdverse?: IEntiteAdverse;
}

const initialState = {
    resultat: {} as ResConsulterEntiteAdverse,
    entiteAdverse: {} as IEntiteAdverse,
};

type ConsulterEntiteAdverseType = typeof initialState;

const SliceConsulterEntiteAdverse = createSlice({
    name: 'MdlConsulterEntiteAdverse',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterEntiteAdverse.envoyerSMSEntiteAdverse.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlConsulterEntiteAdverse.recupererEntiteAdverseParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.entiteAdverse = action.payload.entiteAdverse;
            })
            .addMatcher(isPending(CtrlConsulterEntiteAdverse.envoyerSMSEntiteAdverse, CtrlConsulterEntiteAdverse.recupererEntiteAdverseParId), (state) => {
                state.resultat = {} as ResConsulterEntiteAdverse;
            })
            .addMatcher(isRejected(CtrlConsulterEntiteAdverse.envoyerSMSEntiteAdverse, CtrlConsulterEntiteAdverse.recupererEntiteAdverseParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterEntiteAdverse;
            });
    },
});

export const MdlConsulterEntiteAdverse = SliceConsulterEntiteAdverse.actions;

const selectMdlConsulterEntiteAdverse = (state) => state.mdlConsulterEntiteAdverse;
export const selectConsulterEntiteAdverseResultat = createSelector([selectMdlConsulterEntiteAdverse], (state: ConsulterEntiteAdverseType) => state.resultat);
export const selectEntiteAdverse = createSelector([selectMdlConsulterEntiteAdverse], (state: ConsulterEntiteAdverseType) => state.entiteAdverse);

export default SliceConsulterEntiteAdverse.reducer;