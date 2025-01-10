import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IEntiteAdverse } from 'modele/tiers/entiteAdverse/DomaineEntiteAdverse';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerEntiteAdverse from './CtrlCreerEntiteAdverse';

export interface ReqCreerEntiteAdverse extends IRequete {
    form?: any;
    idSinistre?: string;
}

export interface ResCreerEntiteAdverse extends IResultat {
    entiteAdverse?: IEntiteAdverse;
    idEntiteAdverse?: string;
}

const initialState = {
    resultat: {} as ResCreerEntiteAdverse,
    entiteAdverse: {} as IEntiteAdverse,
};

type CreerEntiteAdverseType = typeof initialState;

const SliceCreerEntiteAdverse = createSlice({
    name: 'MdlCreerEntiteAdverse',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerEntiteAdverse.creerEntiteAdverse.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerEntiteAdverse.initCreationEntiteAdverse.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.entiteAdverse = action.payload.entiteAdverse;
            })
            .addMatcher(isPending(CtrlCreerEntiteAdverse.creerEntiteAdverse, CtrlCreerEntiteAdverse.initCreationEntiteAdverse), (state) => {
                state.resultat = {} as ResCreerEntiteAdverse;
            })
            .addMatcher(isRejected(CtrlCreerEntiteAdverse.creerEntiteAdverse, CtrlCreerEntiteAdverse.initCreationEntiteAdverse), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerEntiteAdverse;
            });
    },
});

export const MdlCreerEntiteAdverse = SliceCreerEntiteAdverse.actions;

const selectMdlCreerEntiteAdverse = (state) => state.mdlCreerEntiteAdverse;
export const selectCreerEntiteAdverseResultat = createSelector([selectMdlCreerEntiteAdverse], (state: CreerEntiteAdverseType) => state.resultat);

export default SliceCreerEntiteAdverse.reducer;