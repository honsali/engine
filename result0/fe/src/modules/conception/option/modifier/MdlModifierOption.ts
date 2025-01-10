import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IOption } from 'modele/conception/option/DomaineOption';
import { IRequete, IResultat } from 'waxant';
import CtrlModifierOption from './CtrlModifierOption';

export interface ReqModifierOption extends IRequete {
    form?: any;
    idOption?: string;
}

export interface ResModifierOption extends IResultat {
    option?: IOption;
}

const initialState = {
    resultat: {} as ResModifierOption,
    option: {} as IOption,
};

type ModifierOptionType = typeof initialState;

const SliceModifierOption = createSlice({
    name: 'MdlModifierOption',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlModifierOption.enregistrerOption.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlModifierOption.initModificationOption.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.option = action.payload.option;
            })
            .addMatcher(isPending(CtrlModifierOption.enregistrerOption, CtrlModifierOption.initModificationOption), (state) => {
                state.resultat = {} as ResModifierOption;
            })
            .addMatcher(isRejected(CtrlModifierOption.enregistrerOption, CtrlModifierOption.initModificationOption), (state) => {
                state.resultat = { rid: 'erreur' } as ResModifierOption;
            });
    },
});

export const MdlModifierOption = SliceModifierOption.actions;

const selectMdlModifierOption = (state) => state.mdlModifierOption;
export const selectModifierOptionResultat = createSelector([selectMdlModifierOption], (state: ModifierOptionType) => state.resultat);

export default SliceModifierOption.reducer;