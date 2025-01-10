import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IQuestionnaire } from 'modele/execution/questionnaire/DomaineQuestionnaire';
import { IRequete, IResultat } from 'waxant';
import CtrlListerQuestionnaire from './CtrlListerQuestionnaire';

export interface ReqListerQuestionnaire extends IRequete {
}

export interface ResListerQuestionnaire extends IResultat {
    listeQuestionnaire?: IQuestionnaire[];
}

const initialState = {
    resultat: {} as ResListerQuestionnaire,
    listeQuestionnaire: [] as IQuestionnaire[],
};

type ListerQuestionnaireType = typeof initialState;

const SliceListerQuestionnaire = createSlice({
    name: 'MdlListerQuestionnaire',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlListerQuestionnaire.listerQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeQuestionnaire = action.payload.listeQuestionnaire;
            })
            .addMatcher(isPending(CtrlListerQuestionnaire.listerQuestionnaire), (state) => {
                state.resultat = {} as ResListerQuestionnaire;
            })
            .addMatcher(isRejected(CtrlListerQuestionnaire.listerQuestionnaire), (state) => {
                state.resultat = { rid: 'erreur' } as ResListerQuestionnaire;
            });
    },
});

export const MdlListerQuestionnaire = SliceListerQuestionnaire.actions;

const selectMdlListerQuestionnaire = (state) => state.mdlListerQuestionnaire;
export const selectListerQuestionnaireResultat = createSelector([selectMdlListerQuestionnaire], (state: ListerQuestionnaireType) => state.resultat);
export const selectListeQuestionnaire = createSelector([selectMdlListerQuestionnaire], (state: ListerQuestionnaireType) => state.listeQuestionnaire);

export default SliceListerQuestionnaire.reducer;