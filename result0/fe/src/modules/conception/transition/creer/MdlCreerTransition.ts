import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { ITransition } from 'modele/conception/transition/DomaineTransition';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerTransition from './CtrlCreerTransition';

export interface ReqCreerTransition extends IRequete {
    form?: any;
    idQuestion?: string;
}

export interface ResCreerTransition extends IResultat {
    idTransition?: string;
    transition?: ITransition;
}

const initialState = {
    resultat: {} as ResCreerTransition,
    transition: {} as ITransition,
};

type CreerTransitionType = typeof initialState;

const SliceCreerTransition = createSlice({
    name: 'MdlCreerTransition',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerTransition.creerTransition.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerTransition.initCreationTransition.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.transition = action.payload.transition;
            })
            .addMatcher(isPending(CtrlCreerTransition.creerTransition, CtrlCreerTransition.initCreationTransition), (state) => {
                state.resultat = {} as ResCreerTransition;
            })
            .addMatcher(isRejected(CtrlCreerTransition.creerTransition, CtrlCreerTransition.initCreationTransition), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerTransition;
            });
    },
});

export const MdlCreerTransition = SliceCreerTransition.actions;

const selectMdlCreerTransition = (state) => state.mdlCreerTransition;
export const selectCreerTransitionResultat = createSelector([selectMdlCreerTransition], (state: CreerTransitionType) => state.resultat);

export default SliceCreerTransition.reducer;