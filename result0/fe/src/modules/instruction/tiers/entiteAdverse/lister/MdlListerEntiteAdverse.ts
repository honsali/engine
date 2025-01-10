import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IEntiteAdverse } from 'modele/tiers/entiteAdverse/DomaineEntiteAdverse';
import { IRequete, IResultat } from 'waxant';
import CtrlListerEntiteAdverse from './CtrlListerEntiteAdverse';

export interface ReqListerEntiteAdverse extends IRequete {
    idSinistre?: string;
}

export interface ResListerEntiteAdverse extends IResultat {
    listeEntiteAdverse?: IEntiteAdverse[];
}

const initialState = {
    resultat: {} as ResListerEntiteAdverse,
    listeEntiteAdverse: [] as IEntiteAdverse[],
};

type ListerEntiteAdverseType = typeof initialState;

const SliceListerEntiteAdverse = createSlice({
    name: 'MdlListerEntiteAdverse',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlListerEntiteAdverse.listerEntiteAdverse.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeEntiteAdverse = action.payload.listeEntiteAdverse;
            })
            .addMatcher(isPending(CtrlListerEntiteAdverse.listerEntiteAdverse), (state) => {
                state.resultat = {} as ResListerEntiteAdverse;
            })
            .addMatcher(isRejected(CtrlListerEntiteAdverse.listerEntiteAdverse), (state) => {
                state.resultat = { rid: 'erreur' } as ResListerEntiteAdverse;
            });
    },
});

export const MdlListerEntiteAdverse = SliceListerEntiteAdverse.actions;

const selectMdlListerEntiteAdverse = (state) => state.mdlListerEntiteAdverse;
export const selectListerEntiteAdverseResultat = createSelector([selectMdlListerEntiteAdverse], (state: ListerEntiteAdverseType) => state.resultat);
export const selectListeEntiteAdverse = createSelector([selectMdlListerEntiteAdverse], (state: ListerEntiteAdverseType) => state.listeEntiteAdverse);

export default SliceListerEntiteAdverse.reducer;