import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IRubrique } from 'modele/conception/rubrique/DomaineRubrique';
import { IVersionModeleQuestionnaire } from 'modele/conception/versionModeleQuestionnaire/DomaineVersionModeleQuestionnaire';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterVersionModeleQuestionnaire from './CtrlConsulterVersionModeleQuestionnaire';

export interface ReqConsulterVersionModeleQuestionnaire extends IRequete {
    idVersionModeleQuestionnaire?: string;
}

export interface ResConsulterVersionModeleQuestionnaire extends IResultat {
    listeRubrique?: IRubrique[];
    versionModeleQuestionnaire?: IVersionModeleQuestionnaire;
}

const initialState = {
    resultat: {} as ResConsulterVersionModeleQuestionnaire,
    listeRubrique: [] as IRubrique[],
    versionModeleQuestionnaire: {} as IVersionModeleQuestionnaire,
};

type ConsulterVersionModeleQuestionnaireType = typeof initialState;

const SliceConsulterVersionModeleQuestionnaire = createSlice({
    name: 'MdlConsulterVersionModeleQuestionnaire',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterVersionModeleQuestionnaire.listerRubriqueParIdVersionModeleQuestionnaire.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeRubrique = action.payload.listeRubrique;
            })
            .addCase(CtrlConsulterVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.versionModeleQuestionnaire = action.payload.versionModeleQuestionnaire;
            })
            .addMatcher(isPending(CtrlConsulterVersionModeleQuestionnaire.listerRubriqueParIdVersionModeleQuestionnaire, CtrlConsulterVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId), (state) => {
                state.resultat = {} as ResConsulterVersionModeleQuestionnaire;
            })
            .addMatcher(isRejected(CtrlConsulterVersionModeleQuestionnaire.listerRubriqueParIdVersionModeleQuestionnaire, CtrlConsulterVersionModeleQuestionnaire.recupererVersionModeleQuestionnaireParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterVersionModeleQuestionnaire;
            });
    },
});

export const MdlConsulterVersionModeleQuestionnaire = SliceConsulterVersionModeleQuestionnaire.actions;

const selectMdlConsulterVersionModeleQuestionnaire = (state) => state.mdlConsulterVersionModeleQuestionnaire;
export const selectConsulterVersionModeleQuestionnaireResultat = createSelector([selectMdlConsulterVersionModeleQuestionnaire], (state: ConsulterVersionModeleQuestionnaireType) => state.resultat);
export const selectListeRubrique = createSelector([selectMdlConsulterVersionModeleQuestionnaire], (state: ConsulterVersionModeleQuestionnaireType) => state.listeRubrique);
export const selectVersionModeleQuestionnaire = createSelector([selectMdlConsulterVersionModeleQuestionnaire], (state: ConsulterVersionModeleQuestionnaireType) => state.versionModeleQuestionnaire);

export default SliceConsulterVersionModeleQuestionnaire.reducer;