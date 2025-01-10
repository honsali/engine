import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IOption } from 'modele/conception/option/DomaineOption';
import { IRequete, IResultat } from 'waxant';
import CtrlCreerOption from './CtrlCreerOption';

export interface ReqCreerOption extends IRequete {
    form?: any;
    idQuestion?: string;
}

export interface ResCreerOption extends IResultat {
    idOption?: string;
    option?: IOption;
}

const initialState = {
    resultat: {} as ResCreerOption,
    option: {} as IOption,
};

type CreerOptionType = typeof initialState;

const SliceCreerOption = createSlice({
    name: 'MdlCreerOption',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlCreerOption.creerOption.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addCase(CtrlCreerOption.initCreationOption.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.option = action.payload.option;
            })
            .addMatcher(isPending(CtrlCreerOption.creerOption, CtrlCreerOption.initCreationOption), (state) => {
                state.resultat = {} as ResCreerOption;
            })
            .addMatcher(isRejected(CtrlCreerOption.creerOption, CtrlCreerOption.initCreationOption), (state) => {
                state.resultat = { rid: 'erreur' } as ResCreerOption;
            });
    },
});

export const MdlCreerOption = SliceCreerOption.actions;

const selectMdlCreerOption = (state) => state.mdlCreerOption;
export const selectCreerOptionResultat = createSelector([selectMdlCreerOption], (state: CreerOptionType) => state.resultat);

export default SliceCreerOption.reducer;