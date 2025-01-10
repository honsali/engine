import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IListePagineeIdentification, IRequeteIdentification } from 'modele/sinistre/identification/DomaineIdentification';
import { IRequete, IResultat } from 'waxant';
import CtrlChercherIdentification from './CtrlChercherIdentification';

export interface ReqChercherIdentification extends IRequete {
    form?: any;
    pageCourante?: number;
}

export interface ResChercherIdentification extends IResultat {
    filtre?: IRequeteIdentification;
    listePagineeIdentification?: IListePagineeIdentification;
}

const initialState = {
    resultat: {} as ResChercherIdentification,
    filtre: {} as IRequeteIdentification,
    listePagineeIdentification: {} as IListePagineeIdentification,
};

type ChercherIdentificationType = typeof initialState;

const SliceChercherIdentification = createSlice({
    name: 'MdlChercherIdentification',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlChercherIdentification.changerPageIdentification.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listePagineeIdentification = action.payload.listePagineeIdentification;
            })
            .addCase(CtrlChercherIdentification.chercherIdentification.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.listePagineeIdentification = action.payload.listePagineeIdentification;
                state.filtre = action.payload.filtre;
            })
            .addMatcher(isPending(CtrlChercherIdentification.changerPageIdentification, CtrlChercherIdentification.chercherIdentification), (state) => {
                state.resultat = {} as ResChercherIdentification;
            })
            .addMatcher(isRejected(CtrlChercherIdentification.changerPageIdentification, CtrlChercherIdentification.chercherIdentification), (state) => {
                state.resultat = { rid: 'erreur' } as ResChercherIdentification;
            });
    },
});

export const MdlChercherIdentification = SliceChercherIdentification.actions;

const selectMdlChercherIdentification = (state) => state.mdlChercherIdentification;
export const selectChercherIdentificationResultat = createSelector([selectMdlChercherIdentification], (state: ChercherIdentificationType) => state.resultat);
export const selectListePagineeIdentification = createSelector([selectMdlChercherIdentification], (state: ChercherIdentificationType) => state.listePagineeIdentification);

export default SliceChercherIdentification.reducer;