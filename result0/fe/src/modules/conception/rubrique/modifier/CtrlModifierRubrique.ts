import ServiceRubrique from 'modele/conception/rubrique/ServiceRubrique';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqModifierRubrique, ResModifierRubrique } from './MdlModifierRubrique';

const enregistrerRubriqueImpl = async (requete: ReqModifierRubrique, resultat: ResModifierRubrique, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceRubrique.enregistrer(dataForm);
};

const initModificationRubriqueImpl = async (requete: ReqModifierRubrique, resultat: ResModifierRubrique, thunkAPI) => {
    resultat.rubrique = await ServiceRubrique.recupererParId(requete.idRubrique);
};

const CtrlModifierRubrique = {
    enregistrerRubrique: action<ReqModifierRubrique, ResModifierRubrique>(enregistrerRubriqueImpl, ActionConception.UcModifierRubrique.ENREGISTRER_RUBRIQUE),
    initModificationRubrique: action<ReqModifierRubrique, ResModifierRubrique>(initModificationRubriqueImpl, ActionConception.UcModifierRubrique.INIT_MODIFICATION_RUBRIQUE),
};

export default CtrlModifierRubrique;