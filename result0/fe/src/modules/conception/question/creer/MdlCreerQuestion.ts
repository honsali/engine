import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerQuestion from './CtrlCreerQuestion';

export interface ReqCreerQuestion extends IRequete {
    form?: any;
    idRubrique?: string;
}

export interface ResCreerQuestion extends IResultat {
    idQuestion?: string;
    question?: IQuestion;
}

const initialState = {
    resultat: {} as ResCreerQuestion,
    question: {} as IQuestion,
};

type CreerQuestionType = typeof initialState;

const SliceCreerQuestion = createSlice({
    name: 'MdlCreerQuestion',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerQuestion.creerQuestion.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerQuestion.initCreationQuestion.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.question = action.payload.question;
            })
            .addMatcher(isPending(CtrlCreerQuestion.creerQuestion, CtrlCreerQuestion.initCreationQuestion), (state) => {
                state.resultat = {} as ResCreerQuestion;
            })
            .addMatcher(isRejected(CtrlCreerQuestion.creerQuestion, CtrlCreerQuestion.initCreationQuestion), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerQuestion;
            });
    },
});

export const MdlCreerQuestion = SliceCreerQuestion.actions;

const selectMdlCreerQuestion = (state) => state.mdlCreerQuestion;
export const selectCreerQuestionResultat = createSelector([selectMdlCreerQuestion], (state: CreerQuestionType) => state.resultat);

export default SliceCreerQuestion.reducer;