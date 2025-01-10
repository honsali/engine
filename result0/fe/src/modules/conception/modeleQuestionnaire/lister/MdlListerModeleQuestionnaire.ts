import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IModeleQuestionnaire } from 'modele/conception/modeleQuestionnaire/DomaineModeleQuestionnaire';
import { IRequete, IResultat } from 'waxant';
import CtrlListerModeleQuestionnaire from './CtrlListerModeleQuestionnaire';

export interface ReqListerModeleQuestionnaire extends IRequete {
}

export interface ResListerModeleQuestionnaire extends IResultat {
    listeModeleQuestionnaire?: IModeleQuestionnaire[];
}

const initialState = {
    resultat: {} as ResListerModeleQuestionnaire,
    listeModeleQuestionnaire: [] as IModeleQuestionnaire[],
};

type ListerModeleQuestionnaireType = typeof initialState;

const SliceListerModeleQuestionnaire = createSlice({
    name: 'MdlListerModeleQuestionnaire',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlListerModeleQuestionnaire.listerModeleQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeModeleQuestionnaire = action.payload.listeModeleQuestionnaire;
            })
            .addMatcher(isPending(CtrlListerModeleQuestionnaire.listerModeleQuestionnaire), (state) => {
                state.resultat = {} as ResListerModeleQuestionnaire;
            })
            .addMatcher(isRejected(CtrlListerModeleQuestionnaire.listerModeleQuestionnaire), (state) => {
                state.resultat = { rid: 'erreur' } as ResListerModeleQuestionnaire;
            });
    },
});

export const MdlListerModeleQuestionnaire = SliceListerModeleQuestionnaire.actions;

const selectMdlListerModeleQuestionnaire = (state) => state.mdlListerModeleQuestionnaire;
export const selectListerModeleQuestionnaireResultat = createSelector([selectMdlListerModeleQuestionnaire], (state: ListerModeleQuestionnaireType) => state.resultat);
export const selectListeModeleQuestionnaire = createSelector([selectMdlListerModeleQuestionnaire], (state: ListerModeleQuestionnaireType) => state.listeModeleQuestionnaire);

export default SliceListerModeleQuestionnaire.reducer;