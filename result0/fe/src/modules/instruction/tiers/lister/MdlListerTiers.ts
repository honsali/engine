import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IRequete, IResultat } from 'waxant';
import CtrlListerTiers from './CtrlListerTiers';

export interface ReqListerTiers extends IRequete {
}

export interface ResListerTiers extends IResultat {
}

const initialState = {
    resultat: {} as ResListerTiers,
};

type ListerTiersType = typeof initialState;

const SliceListerTiers = createSlice({
    name: 'MdlListerTiers',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addMatcher(isPending(), (state) => {
                state.resultat = {} as ResListerTiers;
            })
            .addMatcher(isRejected(), (state) => {
                state.resultat = { rid: 'erreur' } as ResListerTiers;
            });
    },
});

export const MdlListerTiers = SliceListerTiers.actions;

const selectMdlListerTiers = (state) => state.mdlListerTiers;
export const selectListerTiersResultat = createSelector([selectMdlListerTiers], (state: ListerTiersType) => state.resultat);

export default SliceListerTiers.reducer;