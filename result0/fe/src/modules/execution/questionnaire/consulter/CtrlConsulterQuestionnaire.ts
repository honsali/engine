import ServiceQuestionnaire from 'modele/execution/questionnaire/ServiceQuestionnaire';
import ServiceSessionInterview from 'modele/execution/sessionInterview/ServiceSessionInterview';
import { action } from 'waxant';
import { ActionExecution } from '../../ActionExecution';
import { ReqConsulterQuestionnaire, ResConsulterQuestionnaire } from './MdlConsulterQuestionnaire';

const creerSessionInterviewImpl = async (requete: ReqConsulterQuestionnaire, resultat: ResConsulterQuestionnaire, thunkAPI) => {
    await ServiceSessionInterview.creer(requete.idQuestionnaire);
};

const recupererQuestionnaireParIdImpl = async (requete: ReqConsulterQuestionnaire, resultat: ResConsulterQuestionnaire, thunkAPI) => {
    resultat.questionnaire = await ServiceQuestionnaire.recupererParId(requete.idQuestionnaire);
};

const CtrlConsulterQuestionnaire = {
    creerSessionInterview: action<ReqConsulterQuestionnaire, ResConsulterQuestionnaire>(creerSessionInterviewImpl, ActionExecution.UcConsulterQuestionnaire.CREER_SESSION_INTERVIEW),
    recupererQuestionnaireParId: action<ReqConsulterQuestionnaire, ResConsulterQuestionnaire>(recupererQuestionnaireParIdImpl, ActionExecution.UcConsulterQuestionnaire.RECUPERER_QUESTIONNAIRE_PAR_ID),
};

export default CtrlConsulterQuestionnaire;