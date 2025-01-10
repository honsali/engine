import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IAyantDroit } from 'modele/tiers/ayantDroit/DomaineAyantDroit';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerAyantDroit from './CtrlCreerAyantDroit';

export interface ReqCreerAyantDroit extends IRequete {
    form?: any;
    idSinistre?: string;
    idSousDossier?: string;
}

export interface ResCreerAyantDroit extends IResultat {
    ayantDroit?: IAyantDroit;
    idAyantDroit?: string;
}

const initialState = {
    resultat: {} as ResCreerAyantDroit,
    ayantDroit: {} as IAyantDroit,
};

type CreerAyantDroitType = typeof initialState;

const SliceCreerAyantDroit = createSlice({
    name: 'MdlCreerAyantDroit',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerAyantDroit.creerAyantDroit.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerAyantDroit.initCreationAyantDroit.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.ayantDroit = action.payload.ayantDroit;
            })
            .addMatcher(isPending(CtrlCreerAyantDroit.creerAyantDroit, CtrlCreerAyantDroit.initCreationAyantDroit), (state) => {
                state.resultat = {} as ResCreerAyantDroit;
            })
            .addMatcher(isRejected(CtrlCreerAyantDroit.creerAyantDroit, CtrlCreerAyantDroit.initCreationAyantDroit), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerAyantDroit;
            });
    },
});

export const MdlCreerAyantDroit = SliceCreerAyantDroit.actions;

const selectMdlCreerAyantDroit = (state) => state.mdlCreerAyantDroit;
export const selectCreerAyantDroitResultat = createSelector([selectMdlCreerAyantDroit], (state: CreerAyantDroitType) => state.resultat);

export default SliceCreerAyantDroit.reducer;