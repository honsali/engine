import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IOption } from 'modele/conception/option/DomaineOption';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';
import { ITransition } from 'modele/conception/transition/DomaineTransition';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterQuestion from './CtrlConsulterQuestion';

export interface ReqConsulterQuestion extends IRequete {
    idQuestion?: string;
}

export interface ResConsulterQuestion extends IResultat {
    listeOption?: IOption[];
    listeTransition?: ITransition[];
    question?: IQuestion;
}

const initialState = {
    resultat: {} as ResConsulterQuestion,
    listeOption: [] as IOption[],
    listeTransition: [] as ITransition[],
    question: {} as IQuestion,
};

type ConsulterQuestionType = typeof initialState;

const SliceConsulterQuestion = createSlice({
    name: 'MdlConsulterQuestion',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterQuestion.listerOptionParIdQuestion.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeOption = action.payload.listeOption;
            })
            .addCase(CtrlConsulterQuestion.listerTransitionParIdQuestion.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeTransition = action.payload.listeTransition;
            })
            .addCase(CtrlConsulterQuestion.recupererQuestionParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.question = action.payload.question;
            })
            .addMatcher(isPending(CtrlConsulterQuestion.listerOptionParIdQuestion, CtrlConsulterQuestion.listerTransitionParIdQuestion, CtrlConsulterQuestion.recupererQuestionParId), (state) => {
                state.resultat = {} as ResConsulterQuestion;
            })
            .addMatcher(isRejected(CtrlConsulterQuestion.listerOptionParIdQuestion, CtrlConsulterQuestion.listerTransitionParIdQuestion, CtrlConsulterQuestion.recupererQuestionParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterQuestion;
            });
    },
});

export const MdlConsulterQuestion = SliceConsulterQuestion.actions;

const selectMdlConsulterQuestion = (state) => state.mdlConsulterQuestion;
export const selectConsulterQuestionResultat = createSelector([selectMdlConsulterQuestion], (state: ConsulterQuestionType) => state.resultat);
export const selectListeOption = createSelector([selectMdlConsulterQuestion], (state: ConsulterQuestionType) => state.listeOption);
export const selectListeTransition = createSelector([selectMdlConsulterQuestion], (state: ConsulterQuestionType) => state.listeTransition);
export const selectQuestion = createSelector([selectMdlConsulterQuestion], (state: ConsulterQuestionType) => state.question);

export default SliceConsulterQuestion.reducer;