import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IRubrique } from 'modele/conception/rubrique/DomaineRubrique';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierRubrique from './CtrlModifierRubrique';

export interface ReqModifierRubrique extends IRequete {
    form?: any;
    idRubrique?: string;
}

export interface ResModifierRubrique extends IResultat {
    rubrique?: IRubrique;
}

const initialState = {
    resultat: {} as ResModifierRubrique,
    rubrique: {} as IRubrique,
};

type ModifierRubriqueType = typeof initialState;

const SliceModifierRubrique = createSlice({
    name: 'MdlModifierRubrique',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierRubrique.enregistrerRubrique.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierRubrique.initModificationRubrique.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.rubrique = action.payload.rubrique;
            })
            .addMatcher(isPending(CtrlModifierRubrique.enregistrerRubrique, CtrlModifierRubrique.initModificationRubrique), (state) => {
                state.resultat = {} as ResModifierRubrique;
            })
            .addMatcher(isRejected(CtrlModifierRubrique.enregistrerRubrique, CtrlModifierRubrique.initModificationRubrique), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierRubrique;
            });
    },
});

export const MdlModifierRubrique = SliceModifierRubrique.actions;

const selectMdlModifierRubrique = (state) => state.mdlModifierRubrique;
export const selectModifierRubriqueResultat = createSelector([selectMdlModifierRubrique], (state: ModifierRubriqueType) => state.resultat);

export default SliceModifierRubrique.reducer;