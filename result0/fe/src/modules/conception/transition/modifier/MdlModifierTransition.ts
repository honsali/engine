import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { ITransition } from 'modele/conception/transition/DomaineTransition';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierTransition from './CtrlModifierTransition';

export interface ReqModifierTransition extends IRequete {
    form?: any;
    idTransition?: string;
}

export interface ResModifierTransition extends IResultat {
    transition?: ITransition;
}

const initialState = {
    resultat: {} as ResModifierTransition,
    transition: {} as ITransition,
};

type ModifierTransitionType = typeof initialState;

const SliceModifierTransition = createSlice({
    name: 'MdlModifierTransition',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierTransition.enregistrerTransition.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierTransition.initModificationTransition.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.transition = action.payload.transition;
            })
            .addMatcher(isPending(CtrlModifierTransition.enregistrerTransition, CtrlModifierTransition.initModificationTransition), (state) => {
                state.resultat = {} as ResModifierTransition;
            })
            .addMatcher(isRejected(CtrlModifierTransition.enregistrerTransition, CtrlModifierTransition.initModificationTransition), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierTransition;
            });
    },
});

export const MdlModifierTransition = SliceModifierTransition.actions;

const selectMdlModifierTransition = (state) => state.mdlModifierTransition;
export const selectModifierTransitionResultat = createSelector([selectMdlModifierTransition], (state: ModifierTransitionType) => state.resultat);

export default SliceModifierTransition.reducer;