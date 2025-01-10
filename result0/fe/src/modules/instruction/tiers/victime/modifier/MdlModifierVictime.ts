import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IReference } from 'modele/commun/reference/DomaineReference';
import { IVictime } from 'modele/tiers/victime/DomaineVictime';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierVictime from './CtrlModifierVictime';

export interface ReqModifierVictime extends IRequete {
    form?: any;
    idSinistre?: string;
    idVictime?: string;
}

export interface ResModifierVictime extends IResultat {
    listeEntiteAdverse?: IReference[];
    victime?: IVictime;
}

const initialState = {
    resultat: {} as ResModifierVictime,
    listeEntiteAdverse: [] as IReference[],
    victime: {} as IVictime,
};

type ModifierVictimeType = typeof initialState;

const SliceModifierVictime = createSlice({
    name: 'MdlModifierVictime',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierVictime.enregistrerVictime.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierVictime.initModificationVictime.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.victime = action.payload.victime;
                state.listeEntiteAdverse = action.payload.listeEntiteAdverse;
            })
            .addMatcher(isPending(CtrlModifierVictime.enregistrerVictime, CtrlModifierVictime.initModificationVictime), (state) => {
                state.resultat = {} as ResModifierVictime;
            })
            .addMatcher(isRejected(CtrlModifierVictime.enregistrerVictime, CtrlModifierVictime.initModificationVictime), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierVictime;
            });
    },
});

export const MdlModifierVictime = SliceModifierVictime.actions;

const selectMdlModifierVictime = (state) => state.mdlModifierVictime;
export const selectModifierVictimeResultat = createSelector([selectMdlModifierVictime], (state: ModifierVictimeType) => state.resultat);
export const selectListeEntiteAdverse = createSelector([selectMdlModifierVictime], (state: ModifierVictimeType) => state.listeEntiteAdverse);

export default SliceModifierVictime.reducer;