import ServiceReponse from 'modele/execution/reponse/ServiceReponse';
import { action, util } from 'waxant';
import { ActionExecution } from '../../ActionExecution';
import { ReqModifierReponse, ResModifierReponse } from './MdlModifierReponse';

const enregistrerReponseImpl = async (requete: ReqModifierReponse, resultat: ResModifierReponse, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceReponse.enregistrer(dataForm);
};

const initModificationReponseImpl = async (requete: ReqModifierReponse, resultat: ResModifierReponse, thunkAPI) => {
    resultat.reponse = await ServiceReponse.recupererParId(requete.idQuestionnaire, requete.idReponse);
};

const CtrlModifierReponse = {
    enregistrerReponse: action<ReqModifierReponse, ResModifierReponse>(enregistrerReponseImpl, ActionExecution.UcModifierReponse.ENREGISTRER_REPONSE),
    initModificationReponse: action<ReqModifierReponse, ResModifierReponse>(initModificationReponseImpl, ActionExecution.UcModifierReponse.INIT_MODIFICATION_REPONSE),
};

export default CtrlModifierReponse;