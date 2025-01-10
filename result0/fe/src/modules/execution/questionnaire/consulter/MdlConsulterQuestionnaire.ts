import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IQuestionnaire } from 'modele/execution/questionnaire/DomaineQuestionnaire';
import { ISessionInterview } from 'modele/execution/sessionInterview/DomaineSessionInterview';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterQuestionnaire from './CtrlConsulterQuestionnaire';

export interface ReqConsulterQuestionnaire extends IRequete {
    idQuestionnaire?: string;
}

export interface ResConsulterQuestionnaire extends IResultat {
    questionnaire?: IQuestionnaire;
    sessionInterview?: ISessionInterview;
}

const initialState = {
    resultat: {} as ResConsulterQuestionnaire,
    questionnaire: {} as IQuestionnaire,
    sessionInterview: {} as ISessionInterview,
};

type ConsulterQuestionnaireType = typeof initialState;

const SliceConsulterQuestionnaire = createSlice({
    name: 'MdlConsulterQuestionnaire',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterQuestionnaire.creerSessionInterview.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlConsulterQuestionnaire.recupererQuestionnaireParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.questionnaire = action.payload.questionnaire;
            })
            .addMatcher(isPending(CtrlConsulterQuestionnaire.creerSessionInterview, CtrlConsulterQuestionnaire.recupererQuestionnaireParId), (state) => {
                state.resultat = {} as ResConsulterQuestionnaire;
            })
            .addMatcher(isRejected(CtrlConsulterQuestionnaire.creerSessionInterview, CtrlConsulterQuestionnaire.recupererQuestionnaireParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterQuestionnaire;
            });
    },
});

export const MdlConsulterQuestionnaire = SliceConsulterQuestionnaire.actions;

const selectMdlConsulterQuestionnaire = (state) => state.mdlConsulterQuestionnaire;
export const selectConsulterQuestionnaireResultat = createSelector([selectMdlConsulterQuestionnaire], (state: ConsulterQuestionnaireType) => state.resultat);
export const selectQuestionnaire = createSelector([selectMdlConsulterQuestionnaire], (state: ConsulterQuestionnaireType) => state.questionnaire);

export default SliceConsulterQuestionnaire.reducer;