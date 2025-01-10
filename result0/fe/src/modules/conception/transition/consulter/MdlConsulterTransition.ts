import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { ITransition } from 'modele/conception/transition/DomaineTransition';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterTransition from './CtrlConsulterTransition';

export interface ReqConsulterTransition extends IRequete {
    idTransition?: string;
}

export interface ResConsulterTransition extends IResultat {
    transition?: ITransition;
}

const initialState = {
    resultat: {} as ResConsulterTransition,
    transition: {} as ITransition,
};

type ConsulterTransitionType = typeof initialState;

const SliceConsulterTransition = createSlice({
    name: 'MdlConsulterTransition',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterTransition.recupererTransitionParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.transition = action.payload.transition;
            })
            .addCase(CtrlConsulterTransition.supprimerTransition.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addMatcher(isPending(CtrlConsulterTransition.recupererTransitionParId, CtrlConsulterTransition.supprimerTransition), (state) => {
                state.resultat = {} as ResConsulterTransition;
            })
            .addMatcher(isRejected(CtrlConsulterTransition.recupererTransitionParId, CtrlConsulterTransition.supprimerTransition), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterTransition;
            });
    },
});

export const MdlConsulterTransition = SliceConsulterTransition.actions;

const selectMdlConsulterTransition = (state) => state.mdlConsulterTransition;
export const selectConsulterTransitionResultat = createSelector([selectMdlConsulterTransition], (state: ConsulterTransitionType) => state.resultat);
export const selectTransition = createSelector([selectMdlConsulterTransition], (state: ConsulterTransitionType) => state.transition);

export default SliceConsulterTransition.reducer;