import { createSelector, createSlice, isPending, isRejected } from '@reduxjs/toolkit';
import { IOption } from 'modele/conception/option/DomaineOption';
import { IRequete, IResultat } from 'waxant';
import CtrlConsulterOption from './CtrlConsulterOption';

export interface ReqConsulterOption extends IRequete {
    idOption?: string;
}

export interface ResConsulterOption extends IResultat {
    option?: IOption;
}

const initialState = {
    resultat: {} as ResConsulterOption,
    option: {} as IOption,
};

type ConsulterOptionType = typeof initialState;

const SliceConsulterOption = createSlice({
    name: 'MdlConsulterOption',
    initialState,
    reducers: {},
    extraReducers(builder) {
        builder
            .addCase(CtrlConsulterOption.recupererOptionParId.fulfilled, (state, action) => {
                state.resultat = action.payload;
                state.option = action.payload.option;
            })
            .addCase(CtrlConsulterOption.supprimerOption.fulfilled, (state, action) => {
                state.resultat = action.payload;
            })
            .addMatcher(isPending(CtrlConsulterOption.recupererOptionParId, CtrlConsulterOption.supprimerOption), (state) => {
                state.resultat = {} as ResConsulterOption;
            })
            .addMatcher(isRejected(CtrlConsulterOption.recupererOptionParId, CtrlConsulterOption.supprimerOption), (state) => {
                state.resultat = { rid: 'erreur' } as ResConsulterOption;
            });
    },
});

export const MdlConsulterOption = SliceConsulterOption.actions;

const selectMdlConsulterOption = (state) => state.mdlConsulterOption;
export const selectConsulterOptionResultat = createSelector([selectMdlConsulterOption], (state: ConsulterOptionType) => state.resultat);
export const selectOption = createSelector([selectMdlConsulterOption], (state: ConsulterOptionType) => state.option);

export default SliceConsulterOption.reducer;