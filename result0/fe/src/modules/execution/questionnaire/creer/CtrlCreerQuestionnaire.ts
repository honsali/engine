import ServiceQuestionnaire from 'modele/execution/questionnaire/ServiceQuestionnaire';
import { action, util } from 'waxant';
import { ActionExecution } from '../../ActionExecution';
import { ReqCreerQuestionnaire, ResCreerQuestionnaire } from './MdlCreerQuestionnaire';

const creerQuestionnaireImpl = async (requete: ReqCreerQuestionnaire, resultat: ResCreerQuestionnaire, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idQuestionnaire = await ServiceQuestionnaire.creer(dataForm);
};

const initCreationQuestionnaireImpl = async (requete: ReqCreerQuestionnaire, resultat: ResCreerQuestionnaire, thunkAPI) => {
    resultat.questionnaire = {};
};

const CtrlCreerQuestionnaire = {
    creerQuestionnaire: action<ReqCreerQuestionnaire, ResCreerQuestionnaire>(creerQuestionnaireImpl, ActionExecution.UcCreerQuestionnaire.CREER_QUESTIONNAIRE),
    initCreationQuestionnaire: action<ReqCreerQuestionnaire, ResCreerQuestionnaire>(initCreationQuestionnaireImpl, ActionExecution.UcCreerQuestionnaire.INIT_CREATION_QUESTIONNAIRE),
};

export default CtrlCreerQuestionnaire;