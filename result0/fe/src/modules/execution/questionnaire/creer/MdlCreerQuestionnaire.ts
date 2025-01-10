import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IQuestionnaire } from 'modele/execution/questionnaire/DomaineQuestionnaire';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerQuestionnaire from './CtrlCreerQuestionnaire';

export interface ReqCreerQuestionnaire extends IRequete {
    form?: any;
}

export interface ResCreerQuestionnaire extends IResultat {
    idQuestionnaire?: string;
    questionnaire?: IQuestionnaire;
}

const initialState = {
    resultat: {} as ResCreerQuestionnaire,
    questionnaire: {} as IQuestionnaire,
};

type CreerQuestionnaireType = typeof initialState;

const SliceCreerQuestionnaire = createSlice({
    name: 'MdlCreerQuestionnaire',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerQuestionnaire.creerQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerQuestionnaire.initCreationQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.questionnaire = action.payload.questionnaire;
            })
            .addMatcher(isPending(CtrlCreerQuestionnaire.creerQuestionnaire, CtrlCreerQuestionnaire.initCreationQuestionnaire), (state) => {
                state.resultat = {} as ResCreerQuestionnaire;
            })
            .addMatcher(isRejected(CtrlCreerQuestionnaire.creerQuestionnaire, CtrlCreerQuestionnaire.initCreationQuestionnaire), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerQuestionnaire;
            });
    },
});

export const MdlCreerQuestionnaire = SliceCreerQuestionnaire.actions;

const selectMdlCreerQuestionnaire = (state) => state.mdlCreerQuestionnaire;
export const selectCreerQuestionnaireResultat = createSelector([selectMdlCreerQuestionnaire], (state: CreerQuestionnaireType) => state.resultat);

export default SliceCreerQuestionnaire.reducer;