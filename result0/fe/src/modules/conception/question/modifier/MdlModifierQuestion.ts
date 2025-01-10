import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierQuestion from './CtrlModifierQuestion';

export interface ReqModifierQuestion extends IRequete {
    form?: any;
    idQuestion?: string;
}

export interface ResModifierQuestion extends IResultat {
    question?: IQuestion;
}

const initialState = {
    resultat: {} as ResModifierQuestion,
    question: {} as IQuestion,
};

type ModifierQuestionType = typeof initialState;

const SliceModifierQuestion = createSlice({
    name: 'MdlModifierQuestion',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierQuestion.enregistrerQuestion.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierQuestion.initModificationQuestion.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.question = action.payload.question;
            })
            .addMatcher(isPending(CtrlModifierQuestion.enregistrerQuestion, CtrlModifierQuestion.initModificationQuestion), (state) => {
                state.resultat = {} as ResModifierQuestion;
            })
            .addMatcher(isRejected(CtrlModifierQuestion.enregistrerQuestion, CtrlModifierQuestion.initModificationQuestion), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierQuestion;
            });
    },
});

export const MdlModifierQuestion = SliceModifierQuestion.actions;

const selectMdlModifierQuestion = (state) => state.mdlModifierQuestion;
export const selectModifierQuestionResultat = createSelector([selectMdlModifierQuestion], (state: ModifierQuestionType) => state.resultat);

export default SliceModifierQuestion.reducer;