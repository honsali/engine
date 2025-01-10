import ServiceModeleQuestionnaire from 'modele/conception/modeleQuestionnaire/ServiceModeleQuestionnaire';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqCreerModeleQuestionnaire, ResCreerModeleQuestionnaire } from './MdlCreerModeleQuestionnaire';

const creerModeleQuestionnaireImpl = async (requete: ReqCreerModeleQuestionnaire, resultat: ResCreerModeleQuestionnaire, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idModeleQuestionnaire = await ServiceModeleQuestionnaire.creer(dataForm);
};

const initCreationModeleQuestionnaireImpl = async (requete: ReqCreerModeleQuestionnaire, resultat: ResCreerModeleQuestionnaire, thunkAPI) => {
    resultat.modeleQuestionnaire = {};
};

const CtrlCreerModeleQuestionnaire = {
    creerModeleQuestionnaire: action<ReqCreerModeleQuestionnaire, ResCreerModeleQuestionnaire>(creerModeleQuestionnaireImpl, ActionConception.UcCreerModeleQuestionnaire.CREER_MODELE_QUESTIONNAIRE),
    initCreationModeleQuestionnaire: action<ReqCreerModeleQuestionnaire, ResCreerModeleQuestionnaire>(initCreationModeleQuestionnaireImpl, ActionConception.UcCreerModeleQuestionnaire.INIT_CREATION_MODELE_QUESTIONNAIRE),
};

export default CtrlCreerModeleQuestionnaire;