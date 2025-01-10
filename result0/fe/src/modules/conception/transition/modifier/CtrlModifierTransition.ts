import ServiceTransition from 'modele/conception/transition/ServiceTransition';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqModifierTransition, ResModifierTransition } from './MdlModifierTransition';

const enregistrerTransitionImpl = async (requete: ReqModifierTransition, resultat: ResModifierTransition, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceTransition.enregistrer(dataForm);
};

const initModificationTransitionImpl = async (requete: ReqModifierTransition, resultat: ResModifierTransition, thunkAPI) => {
    resultat.transition = await ServiceTransition.recupererParId(requete.idTransition);
};

const CtrlModifierTransition = {
    enregistrerTransition: action<ReqModifierTransition, ResModifierTransition>(enregistrerTransitionImpl, ActionConception.UcModifierTransition.ENREGISTRER_TRANSITION),
    initModificationTransition: action<ReqModifierTransition, ResModifierTransition>(initModificationTransitionImpl, ActionConception.UcModifierTransition.INIT_MODIFICATION_TRANSITION),
};

export default CtrlModifierTransition;