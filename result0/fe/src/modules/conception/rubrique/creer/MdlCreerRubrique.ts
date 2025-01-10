import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IRubrique } from 'modele/conception/rubrique/DomaineRubrique';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerRubrique from './CtrlCreerRubrique';

export interface ReqCreerRubrique extends IRequete {
    form?: any;
    idVersionModeleQuestionnaire?: string;
}

export interface ResCreerRubrique extends IResultat {
    idRubrique?: string;
    rubrique?: IRubrique;
}

const initialState = {
    resultat: {} as ResCreerRubrique,
    rubrique: {} as IRubrique,
};

type CreerRubriqueType = typeof initialState;

const SliceCreerRubrique = createSlice({
    name: 'MdlCreerRubrique',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerRubrique.creerRubrique.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerRubrique.initCreationRubrique.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.rubrique = action.payload.rubrique;
            })
            .addMatcher(isPending(CtrlCreerRubrique.creerRubrique, CtrlCreerRubrique.initCreationRubrique), (state) => {
                state.resultat = {} as ResCreerRubrique;
            })
            .addMatcher(isRejected(CtrlCreerRubrique.creerRubrique, CtrlCreerRubrique.initCreationRubrique), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerRubrique;
            });
    },
});

export const MdlCreerRubrique = SliceCreerRubrique.actions;

const selectMdlCreerRubrique = (state) => state.mdlCreerRubrique;
export const selectCreerRubriqueResultat = createSelector([selectMdlCreerRubrique], (state: CreerRubriqueType) => state.resultat);

export default SliceCreerRubrique.reducer;