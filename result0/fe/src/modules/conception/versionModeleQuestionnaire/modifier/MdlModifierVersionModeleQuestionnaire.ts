import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IRubrique } from 'modele/conception/rubrique/DomaineRubrique';
import { IVersionModeleQuestionnaire } from 'modele/conception/versionModeleQuestionnaire/DomaineVersionModeleQuestionnaire';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierVersionModeleQuestionnaire from './CtrlModifierVersionModeleQuestionnaire';

export interface ReqModifierVersionModeleQuestionnaire extends IRequete {
    idVersionModeleQuestionnaire?: string;
}

export interface ResModifierVersionModeleQuestionnaire extends IResultat {
    listeRubrique?: IRubrique[];
    versionModeleQuestionnaire?: IVersionModeleQuestionnaire;
}

const initialState = {
    resultat: {} as ResModifierVersionModeleQuestionnaire,
    listeRubrique: [] as IRubrique[],
    versionModeleQuestionnaire: {} as IVersionModeleQuestionnaire,
};

type ModifierVersionModeleQuestionnaireType = typeof initialState;

const SliceModifierVersionModeleQuestionnaire = createSlice({
    name: 'MdlModifierVersionModeleQuestionnaire',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierVersionModeleQuestionnaire.listerRubriqueParIdVersionModeleQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeRubrique = action.payload.listeRubrique;
            })
            .addCase(CtrlModifierVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.versionModeleQuestionnaire = action.payload.versionModeleQuestionnaire;
            })
            .addMatcher(isPending(CtrlModifierVersionModeleQuestionnaire.listerRubriqueParIdVersionModeleQuestionnaire, CtrlModifierVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId), (state) => {
                state.resultat = {} as ResModifierVersionModeleQuestionnaire;
            })
            .addMatcher(isRejected(CtrlModifierVersionModeleQuestionnaire.listerRubriqueParIdVersionModeleQuestionnaire, CtrlModifierVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierVersionModeleQuestionnaire;
            });
    },
});

export const MdlModifierVersionModeleQuestionnaire = SliceModifierVersionModeleQuestionnaire.actions;

const selectMdlModifierVersionModeleQuestionnaire = (state) => state.mdlModifierVersionModeleQuestionnaire;
export const selectModifierVersionModeleQuestionnaireResultat = createSelector([selectMdlModifierVersionModeleQuestionnaire], (state: ModifierVersionModeleQuestionnaireType) => state.resultat);
export const selectListeRubrique = createSelector([selectMdlModifierVersionModeleQuestionnaire], (state: ModifierVersionModeleQuestionnaireType) => state.listeRubrique);
export const selectVersionModeleQuestionnaire = createSelector([selectMdlModifierVersionModeleQuestionnaire], (state: ModifierVersionModeleQuestionnaireType) => state.versionModeleQuestionnaire);

export default SliceModifierVersionModeleQuestionnaire.reducer;