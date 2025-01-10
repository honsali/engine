import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IReponse } from 'modele/execution/reponse/DomaineReponse';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierReponse from './CtrlModifierReponse';

export interface ReqModifierReponse extends IRequete {
    form?: any;
    idQuestionnaire?: string;
    idReponse?: string;
}

export interface ResModifierReponse extends IResultat {
    reponse?: IReponse;
}

const initialState = {
    resultat: {} as ResModifierReponse,
    reponse: {} as IReponse,
};

type ModifierReponseType = typeof initialState;

const SliceModifierReponse = createSlice({
    name: 'MdlModifierReponse',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierReponse.enregistrerReponse.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierReponse.initModificationReponse.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.reponse = action.payload.reponse;
            })
            .addMatcher(isPending(CtrlModifierReponse.enregistrerReponse, CtrlModifierReponse.initModificationReponse), (state) => {
                state.resultat = {} as ResModifierReponse;
            })
            .addMatcher(isRejected(CtrlModifierReponse.enregistrerReponse, CtrlModifierReponse.initModificationReponse), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierReponse;
            });
    },
});

export const MdlModifierReponse = SliceModifierReponse.actions;

const selectMdlModifierReponse = (state) => state.mdlModifierReponse;
export const selectModifierReponseResultat = createSelector([selectMdlModifierReponse], (state: ModifierReponseType) => state.resultat);

export default SliceModifierReponse.reducer;