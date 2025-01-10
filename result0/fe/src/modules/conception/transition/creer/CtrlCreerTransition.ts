import ServiceTransition from 'modele/conception/transition/ServiceTransition';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqCreerTransition, ResCreerTransition } from './MdlCreerTransition';

const creerTransitionImpl = async (requete: ReqCreerTransition, resultat: ResCreerTransition, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idTransition = await ServiceTransition.creer(requete.idQuestion, dataForm);
};

const initCreationTransitionImpl = async (requete: ReqCreerTransition, resultat: ResCreerTransition, thunkAPI) => {
    resultat.transition = {};
};

const CtrlCreerTransition = {
    creerTransition: action<ReqCreerTransition, ResCreerTransition>(creerTransitionImpl, ActionConception.UcCreerTransition.CREER_TRANSITION),
    initCreationTransition: action<ReqCreerTransition, ResCreerTransition>(initCreationTransitionImpl, ActionConception.UcCreerTransition.INIT_CREATION_TRANSITION),
};

export default CtrlCreerTransition;