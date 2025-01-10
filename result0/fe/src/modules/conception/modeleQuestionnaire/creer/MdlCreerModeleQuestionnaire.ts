import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IModeleQuestionnaire } from 'modele/conception/modeleQuestionnaire/DomaineModeleQuestionnaire';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerModeleQuestionnaire from './CtrlCreerModeleQuestionnaire';

export interface ReqCreerModeleQuestionnaire extends IRequete {
    form?: any;
}

export interface ResCreerModeleQuestionnaire extends IResultat {
    idModeleQuestionnaire?: string;
    modeleQuestionnaire?: IModeleQuestionnaire;
}

const initialState = {
    resultat: {} as ResCreerModeleQuestionnaire,
    modeleQuestionnaire: {} as IModeleQuestionnaire,
};

type CreerModeleQuestionnaireType = typeof initialState;

const SliceCreerModeleQuestionnaire = createSlice({
    name: 'MdlCreerModeleQuestionnaire',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerModeleQuestionnaire.creerModeleQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerModeleQuestionnaire.initCreationModeleQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.modeleQuestionnaire = action.payload.modeleQuestionnaire;
            })
            .addMatcher(isPending(CtrlCreerModeleQuestionnaire.creerModeleQuestionnaire, CtrlCreerModeleQuestionnaire.initCreationModeleQuestionnaire), (state) => {
                state.resultat = {} as ResCreerModeleQuestionnaire;
            })
            .addMatcher(isRejected(CtrlCreerModeleQuestionnaire.creerModeleQuestionnaire, CtrlCreerModeleQuestionnaire.initCreationModeleQuestionnaire), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerModeleQuestionnaire;
            });
    },
});

export const MdlCreerModeleQuestionnaire = SliceCreerModeleQuestionnaire.actions;

const selectMdlCreerModeleQuestionnaire = (state) => state.mdlCreerModeleQuestionnaire;
export const selectCreerModeleQuestionnaireResultat = createSelector([selectMdlCreerModeleQuestionnaire], (state: CreerModeleQuestionnaireType) => state.resultat);

export default SliceCreerModeleQuestionnaire.reducer;