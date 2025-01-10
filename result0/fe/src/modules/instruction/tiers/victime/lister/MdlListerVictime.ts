import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IAyantDroit } from 'modele/tiers/ayantDroit/DomaineAyantDroit';
import { IVictime } from 'modele/tiers/victime/DomaineVictime';
import { IRequete, IResultat } from 'waxant';
import CtrlListerVictime from './CtrlListerVictime';

export interface ReqListerVictime extends IRequete {
    idSinistre?: string;
    idSousDossier?: string;
}

export interface ResListerVictime extends IResultat {
    listeAyantDroit?: IAyantDroit[];
    listeVictime?: IVictime[];
}

const initialState = {
    resultat: {} as ResListerVictime,
    listeAyantDroit: [] as IAyantDroit[],
    listeVictime: [] as IVictime[],
};

type ListerVictimeType = typeof initialState;

const SliceListerVictime = createSlice({
    name: 'MdlListerVictime',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlListerVictime.listerAyantDroit.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeAyantDroit = action.payload.listeAyantDroit;
            })
            .addCase(CtrlListerVictime.listerVictime.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listeVictime = action.payload.listeVictime;
            })
            .addMatcher(isPending(CtrlListerVictime.listerAyantDroit, CtrlListerVictime.listerVictime), (state) => {
                state.resultat = {} as ResListerVictime;
            })
            .addMatcher(isRejected(CtrlListerVictime.listerAyantDroit, CtrlListerVictime.listerVictime), (state) => {
                state.resultat = { rid: 'erreur' } as ResListerVictime;
            });
    },
});

export const MdlListerVictime = SliceListerVictime.actions;

const selectMdlListerVictime = (state) => state.mdlListerVictime;
export const selectListerVictimeResultat = createSelector([selectMdlListerVictime], (state: ListerVictimeType) => state.resultat);
export const selectListeAyantDroit = createSelector([selectMdlListerVictime], (state: ListerVictimeType) => state.listeAyantDroit);
export const selectListeVictime = createSelector([selectMdlListerVictime], (state: ListerVictimeType) => state.listeVictime);

export default SliceListerVictime.reducer;