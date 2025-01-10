import ServiceModeleQuestionnaire from 'modele/conception/modeleQuestionnaire/ServiceModeleQuestionnaire';
import { action } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqListerModeleQuestionnaire, ResListerModeleQuestionnaire } from './MdlListerModeleQuestionnaire';

const listerModeleQuestionnaireImpl = async (requete: ReqListerModeleQuestionnaire, resultat: ResListerModeleQuestionnaire, thunkAPI) => {
    resultat.listeModeleQuestionnaire = await ServiceModeleQuestionnaire.lister();
};

const CtrlListerModeleQuestionnaire = {
    listerModeleQuestionnaire: action<ReqListerModeleQuestionnaire, ResListerModeleQuestionnaire>(listerModeleQuestionnaireImpl, ActionConception.UcListerModeleQuestionnaire.LISTER_MODELE_QUESTIONNAIRE),
};

export default CtrlListerModeleQuestionnaire;