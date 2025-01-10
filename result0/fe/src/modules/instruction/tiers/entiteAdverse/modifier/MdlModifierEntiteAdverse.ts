import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IEntiteAdverse } from 'modele/tiers/entiteAdverse/DomaineEntiteAdverse';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierEntiteAdverse from './CtrlModifierEntiteAdverse';

export interface ReqModifierEntiteAdverse extends IRequete {
    form?: any;
    idEntiteAdverse?: string;
    idSinistre?: string;
}

export interface ResModifierEntiteAdverse extends IResultat {
    entiteAdverse?: IEntiteAdverse;
}

const initialState = {
    resultat: {} as ResModifierEntiteAdverse,
    entiteAdverse: {} as IEntiteAdverse,
};

type ModifierEntiteAdverseType = typeof initialState;

const SliceModifierEntiteAdverse = createSlice({
    name: 'MdlModifierEntiteAdverse',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierEntiteAdverse.enregistrerEntiteAdverse.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierEntiteAdverse.initModificationEntiteAdverse.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.entiteAdverse = action.payload.entiteAdverse;
            })
            .addMatcher(isPending(CtrlModifierEntiteAdverse.enregistrerEntiteAdverse, CtrlModifierEntiteAdverse.initModificationEntiteAdverse), (state) => {
                state.resultat = {} as ResModifierEntiteAdverse;
            })
            .addMatcher(isRejected(CtrlModifierEntiteAdverse.enregistrerEntiteAdverse, CtrlModifierEntiteAdverse.initModificationEntiteAdverse), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierEntiteAdverse;
            });
    },
});

export const MdlModifierEntiteAdverse = SliceModifierEntiteAdverse.actions;

const selectMdlModifierEntiteAdverse = (state) => state.mdlModifierEntiteAdverse;
export const selectModifierEntiteAdverseResultat = createSelector([selectMdlModifierEntiteAdverse], (state: ModifierEntiteAdverseType) => state.resultat);

export default SliceModifierEntiteAdverse.reducer;