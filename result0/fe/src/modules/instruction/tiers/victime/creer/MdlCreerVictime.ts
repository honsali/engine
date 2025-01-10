import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IReference } from 'modele/commun/reference/DomaineReference';
import { IVictime } from 'modele/tiers/victime/DomaineVictime';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerVictime from './CtrlCreerVictime';

export interface ReqCreerVictime extends IRequete {
    form?: any;
    idSinistre?: string;
}

export interface ResCreerVictime extends IResultat {
    idVictime?: string;
    listeEntiteAdverse?: IReference[];
    victime?: IVictime;
}

const initialState = {
    resultat: {} as ResCreerVictime,
    listeEntiteAdverse: [] as IReference[],
    victime: {} as IVictime,
};

type CreerVictimeType = typeof initialState;

const SliceCreerVictime = createSlice({
    name: 'MdlCreerVictime',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerVictime.creerVictime.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerVictime.initCreationVictime.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.victime = action.payload.victime;
                state.listeEntiteAdverse = action.payload.listeEntiteAdverse;
            })
            .addMatcher(isPending(CtrlCreerVictime.creerVictime, CtrlCreerVictime.initCreationVictime), (state) => {
                state.resultat = {} as ResCreerVictime;
            })
            .addMatcher(isRejected(CtrlCreerVictime.creerVictime, CtrlCreerVictime.initCreationVictime), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerVictime;
            });
    },
});

export const MdlCreerVictime = SliceCreerVictime.actions;

const selectMdlCreerVictime = (state) => state.mdlCreerVictime;
export const selectCreerVictimeResultat = createSelector([selectMdlCreerVictime], (state: CreerVictimeType) => state.resultat);
export const selectListeEntiteAdverse = createSelector([selectMdlCreerVictime], (state: CreerVictimeType) => state.listeEntiteAdverse);

export default SliceCreerVictime.reducer;