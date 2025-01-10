import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';
import { IRubrique } from 'modele/conception/rubrique/DomaineRubrique';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterRubrique from './CtrlConsulterRubrique';

export interface ReqConsulterRubrique extends IRequete {
    idRubrique?: string;
}

export interface ResConsulterRubrique extends IResultat {
    listeQuestion?: IQuestion[];
    rubrique?: IRubrique;
}

const initialState = {
    resultat: {} as ResConsulterRubrique,
    listeQuestion: [] as IQuestion[],
    rubrique: {} as IRubrique,
};

type ConsulterRubriqueType = typeof initialState;

const SliceConsulterRubrique = createSlice({
    name: 'MdlConsulterRubrique',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterRubrique.listerQuestionParIdRubrique.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeQuestion = action.payload.listeQuestion;
            })
            .addCase(CtrlConsulterRubrique.recupererRubriqueParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.rubrique = action.payload.rubrique;
            })
            .addMatcher(isPending(CtrlConsulterRubrique.listerQuestionParIdRubrique, CtrlConsulterRubrique.recupererRubriqueParId), (state) => {
                state.resultat = {} as ResConsulterRubrique;
            })
            .addMatcher(isRejected(CtrlConsulterRubrique.listerQuestionParIdRubrique, CtrlConsulterRubrique.recupererRubriqueParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterRubrique;
            });
    },
});

export const MdlConsulterRubrique = SliceConsulterRubrique.actions;

const selectMdlConsulterRubrique = (state) => state.mdlConsulterRubrique;
export const selectConsulterRubriqueResultat = createSelector([selectMdlConsulterRubrique], (state: ConsulterRubriqueType) => state.resultat);
export const selectListeQuestion = createSelector([selectMdlConsulterRubrique], (state: ConsulterRubriqueType) => state.listeQuestion);
export const selectRubrique = createSelector([selectMdlConsulterRubrique], (state: ConsulterRubriqueType) => state.rubrique);

export default SliceConsulterRubrique.reducer;