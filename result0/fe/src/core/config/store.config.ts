import { AnyAction, combineReducers, configureStore, ThunkAction } from '@reduxjs/toolkit';
import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';

import notificationMiddleWare from 'core/util/notificationMiddleWare';
import StoreAuth from 'modele/auth/auth.store';
import StoreSession from 'modele/session/session.store';
import MdlConsulterTransition from 'modules/modules/conception/transition/consulter/MdlConsulterTransition';
import MdlCreerVictime from 'modules/modules/instruction/tiers/victime/creer/MdlCreerVictime';
import MdlModifierRubrique from 'modules/modules/conception/rubrique/modifier/MdlModifierRubrique';
import MdlConsulterEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/consulter/MdlConsulterEntiteAdverse';
import MdlModifierVictime from 'modules/modules/instruction/tiers/victime/modifier/MdlModifierVictime';
import MdlCreerQuestion from 'modules/modules/conception/question/creer/MdlCreerQuestion';
import MdlConsulterQuestionnaire from 'modules/modules/execution/questionnaire/consulter/MdlConsulterQuestionnaire';
import MdlModifierEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/modifier/MdlModifierEntiteAdverse';
import MdlModifierQuestion from 'modules/modules/conception/question/modifier/MdlModifierQuestion';
import MdlCreerAyantDroit from 'modules/modules/instruction/tiers/ayantDroit/creer/MdlCreerAyantDroit';
import MdlCreerTransition from 'modules/modules/conception/transition/creer/MdlCreerTransition';
import MdlConsulterVersionModeleQuestionnaire from 'modules/modules/conception/versionModeleQuestionnaire/consulter/MdlConsulterVersionModeleQuestionnaire';
import MdlModifierReponse from 'modules/modules/execution/reponse/modifier/MdlModifierReponse';
import MdlConsulterAyantDroit from 'modules/modules/instruction/tiers/ayantDroit/consulter/MdlConsulterAyantDroit';
import MdlConsulterOption from 'modules/modules/conception/option/consulter/MdlConsulterOption';
import MdlModifierVersionModeleQuestionnaire from 'modules/modules/conception/versionModeleQuestionnaire/modifier/MdlModifierVersionModeleQuestionnaire';
import MdlListerEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/lister/MdlListerEntiteAdverse';
import MdlConsulterRubrique from 'modules/modules/conception/rubrique/consulter/MdlConsulterRubrique';
import MdlCreerModeleQuestionnaire from 'modules/modules/conception/modeleQuestionnaire/creer/MdlCreerModeleQuestionnaire';
import MdlListerQuestionnaire from 'modules/modules/execution/questionnaire/lister/MdlListerQuestionnaire';
import MdlCreerQuestionnaire from 'modules/modules/execution/questionnaire/creer/MdlCreerQuestionnaire';
import MdlModifierAyantDroit from 'modules/modules/instruction/tiers/ayantDroit/modifier/MdlModifierAyantDroit';
import MdlListerModeleQuestionnaire from 'modules/modules/conception/modeleQuestionnaire/lister/MdlListerModeleQuestionnaire';
import MdlCreerOption from 'modules/modules/conception/option/creer/MdlCreerOption';
import MdlConsulterVictime from 'modules/modules/instruction/tiers/victime/consulter/MdlConsulterVictime';
import MdlConsulterQuestion from 'modules/modules/conception/question/consulter/MdlConsulterQuestion';
import MdlModifierTransition from 'modules/modules/conception/transition/modifier/MdlModifierTransition';
import MdlModifierOption from 'modules/modules/conception/option/modifier/MdlModifierOption';
import MdlListerTiers from 'modules/modules/instruction/tiers/lister/MdlListerTiers';
import MdlChercherIdentification from 'modules/modules/identification/chercher/MdlChercherIdentification';
import MdlCreerRubrique from 'modules/modules/conception/rubrique/creer/MdlCreerRubrique';
import MdlListerVictime from 'modules/modules/instruction/tiers/victime/lister/MdlListerVictime';
import MdlCreerEntiteAdverse from 'modules/modules/instruction/tiers/entiteAdverse/creer/MdlCreerEntiteAdverse';

const rootReducer = combineReducers({
    storeAuth: StoreAuth,
    storeSession: StoreSession,
    mdlConsulterTransition: MdlConsulterTransition,
    mdlCreerVictime: MdlCreerVictime,
    mdlModifierRubrique: MdlModifierRubrique,
    mdlConsulterEntiteAdverse: MdlConsulterEntiteAdverse,
    mdlModifierVictime: MdlModifierVictime,
    mdlCreerQuestion: MdlCreerQuestion,
    mdlConsulterQuestionnaire: MdlConsulterQuestionnaire,
    mdlModifierEntiteAdverse: MdlModifierEntiteAdverse,
    mdlModifierQuestion: MdlModifierQuestion,
    mdlCreerAyantDroit: MdlCreerAyantDroit,
    mdlCreerTransition: MdlCreerTransition,
    mdlConsulterVersionModeleQuestionnaire: MdlConsulterVersionModeleQuestionnaire,
    mdlModifierReponse: MdlModifierReponse,
    mdlConsulterAyantDroit: MdlConsulterAyantDroit,
    mdlConsulterOption: MdlConsulterOption,
    mdlModifierVersionModeleQuestionnaire: MdlModifierVersionModeleQuestionnaire,
    mdlListerEntiteAdverse: MdlListerEntiteAdverse,
    mdlConsulterRubrique: MdlConsulterRubrique,
    mdlCreerModeleQuestionnaire: MdlCreerModeleQuestionnaire,
    mdlListerQuestionnaire: MdlListerQuestionnaire,
    mdlCreerQuestionnaire: MdlCreerQuestionnaire,
    mdlModifierAyantDroit: MdlModifierAyantDroit,
    mdlListerModeleQuestionnaire: MdlListerModeleQuestionnaire,
    mdlCreerOption: MdlCreerOption,
    mdlConsulterVictime: MdlConsulterVictime,
    mdlConsulterQuestion: MdlConsulterQuestion,
    mdlModifierTransition: MdlModifierTransition,
    mdlModifierOption: MdlModifierOption,
    mdlListerTiers: MdlListerTiers,
    mdlChercherIdentification: MdlChercherIdentification,
    mdlCreerRubrique: MdlCreerRubrique,
    mdlListerVictime: MdlListerVictime,
    mdlCreerEntiteAdverse: MdlCreerEntiteAdverse,
});

const store = configureStore({
    reducer: rootReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().prepend(notificationMiddleWare),
});

const getStore = () => {
    return store;
};

export type IRootState = ReturnType<typeof rootReducer>;
export type AppDispatch = typeof store.dispatch;

export const useAppSelector: TypedUseSelectorHook<IRootState> = useSelector;
export const useAppDispatch = () => useDispatch<AppDispatch>();
export type AppThunk<ReturnType = void> = ThunkAction<ReturnType, IRootState, unknown, AnyAction>;

export default getStore;