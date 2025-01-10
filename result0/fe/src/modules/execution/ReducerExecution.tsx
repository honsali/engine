import MdlConsulterQuestionnaire from './questionnaire/consulter/MdlConsulterQuestionnaire';
import MdlCreerQuestionnaire from './questionnaire/creer/MdlCreerQuestionnaire';
import MdlListerQuestionnaire from './questionnaire/lister/MdlListerQuestionnaire';
import MdlModifierReponse from './reponse/modifier/MdlModifierReponse';

const ReducerExecution = {
    mdlConsulterQuestionnaire: MdlConsulterQuestionnaire,
    mdlCreerQuestionnaire: MdlCreerQuestionnaire,
    mdlListerQuestionnaire: MdlListerQuestionnaire,
    mdlModifierReponse: MdlModifierReponse,
};

export default ReducerExecution;