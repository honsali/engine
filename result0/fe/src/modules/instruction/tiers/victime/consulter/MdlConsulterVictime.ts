import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IVictime } from 'modele/tiers/victime/DomaineVictime';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterVictime from './CtrlConsulterVictime';

export interface ReqConsulterVictime extends IRequete {
    idSinistre?: string;
    idVictime?: string;
}

export interface ResConsulterVictime extends IResultat {
    victime?: IVictime;
}

const initialState = {
    resultat: {} as ResConsulterVictime,
    victime: {} as IVictime,
};

type ConsulterVictimeType = typeof initialState;

const SliceConsulterVictime = createSlice({
    name: 'MdlConsulterVictime',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterVictime.envoyerSMSVictime.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlConsulterVictime.recupererVictimeParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.victime = action.payload.victime;
            })
            .addMatcher(isPending(CtrlConsulterVictime.envoyerSMSVictime, CtrlConsulterVictime.recupererVictimeParId), (state) => {
                state.resultat = {} as ResConsulterVictime;
            })
            .addMatcher(isRejected(CtrlConsulterVictime.envoyerSMSVictime, CtrlConsulterVictime.recupererVictimeParId), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterVictime;
            });
    },
});

export const MdlConsulterVictime = SliceConsulterVictime.actions;

const selectMdlConsulterVictime = (state) => state.mdlConsulterVictime;
export const selectConsulterVictimeResultat = createSelector([selectMdlConsulterVictime], (state: ConsulterVictimeType) => state.resultat);
export const selectVictime = createSelector([selectMdlConsulterVictime], (state: ConsulterVictimeType) => state.victime);

export default SliceConsulterVictime.reducer;