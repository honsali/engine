import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IAyantDroit } from 'modele/tiers/ayantDroit/DomaineAyantDroit';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterAyantDroit from './CtrlConsulterAyantDroit';

export interface ReqConsulterAyantDroit extends IRequete {
    idAyantDroit?: string;
    idSinistre?: string;
    idSousDossier?: string;
}

export interface ResConsulterAyantDroit extends IResultat {
    ayantDroit?: IAyantDroit;
}

const initialState = {
    resultat: {} as ResConsulterAyantDroit,
    ayantDroit: {} as IAyantDroit,
};

type ConsulterAyantDroitType = typeof initialState;

const SliceConsulterAyantDroit = createSlice({
    name: 'MdlConsulterAyantDroit',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterAyantDroit.recupererAyantDroitParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.ayantDroit = action.payload.ayantDroit;
            })
            .addMatcher(isPending(CtrlConsulterAyantDroit.recupererAyantDroitParId), (state) => {
                state.resultat = {} as ResConsulterAyantDroit;
            })
            .addMatcher(isRejected(CtrlConsulterAyantDroit.recupererAyantDroitParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterAyantDroit;
            });
    },
});

export const MdlConsulterAyantDroit = SliceConsulterAyantDroit.actions;

const selectMdlConsulterAyantDroit = (state) => state.mdlConsulterAyantDroit;
export const selectConsulterAyantDroitResultat = createSelector([selectMdlConsulterAyantDroit], (state: ConsulterAyantDroitType) => state.resultat);
export const selectAyantDroit = createSelector([selectMdlConsulterAyantDroit], (state: ConsulterAyantDroitType) => state.ayantDroit);

export default SliceConsulterAyantDroit.reducer;