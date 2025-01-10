import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IAyantDroit } from 'modele/tiers/ayantDroit/DomaineAyantDroit';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierAyantDroit from './CtrlModifierAyantDroit';

export interface ReqModifierAyantDroit extends IRequete {
    form?: any;
    idAyantDroit?: string;
    idSinistre?: string;
    idSousDossier?: string;
}

export interface ResModifierAyantDroit extends IResultat {
    ayantDroit?: IAyantDroit;
}

const initialState = {
    resultat: {} as ResModifierAyantDroit,
    ayantDroit: {} as IAyantDroit,
};

type ModifierAyantDroitType = typeof initialState;

const SliceModifierAyantDroit = createSlice({
    name: 'MdlModifierAyantDroit',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierAyantDroit.enregistrerAyantDroit.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierAyantDroit.initModificationAyantDroit.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.ayantDroit = action.payload.ayantDroit;
            })
            .addMatcher(isPending(CtrlModifierAyantDroit.enregistrerAyantDroit, CtrlModifierAyantDroit.initModificationAyantDroit), (state) => {
                state.resultat = {} as ResModifierAyantDroit;
            })
            .addMatcher(isRejected(CtrlModifierAyantDroit.enregistrerAyantDroit, CtrlModifierAyantDroit.initModificationAyantDroit), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierAyantDroit;
            });
    },
});

export const MdlModifierAyantDroit = SliceModifierAyantDroit.actions;

const selectMdlModifierAyantDroit = (state) => state.mdlModifierAyantDroit;
export const selectModifierAyantDroitResultat = createSelector([selectMdlModifierAyantDroit], (state: ModifierAyantDroitType) => state.resultat);

export default SliceModifierAyantDroit.reducer;