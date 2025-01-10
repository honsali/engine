import ServiceQuestionnaire from 'modele/execution/questionnaire/ServiceQuestionnaire';
import { action } from 'waxant';
import { ActionExecution } from '../../ActionExecution';
import { ReqListerQuestionnaire, ResListerQuestionnaire } from './MdlListerQuestionnaire';

const listerQuestionnaireImpl = async (requete: ReqListerQuestionnaire, resultat: ResListerQuestionnaire, thunkAPI) => {
    resultat.listeQuestionnaire = await ServiceQuestionnaire.lister();
};

const CtrlListerQuestionnaire = {
    listerQuestionnaire: action<ReqListerQuestionnaire, ResListerQuestionnaire>(listerQuestionnaireImpl, ActionExecution.UcListerQuestionnaire.LISTER_QUESTIONNAIRE),
};

export default CtrlListerQuestionnaire;