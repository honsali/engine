import ServiceRubrique from 'modele/conception/rubrique/ServiceRubrique';
import ServiceVersionModeleQuestionnaire from 'modele/conception/versionModeleQuestionnaire/ServiceVersionModeleQuestionnaire';
import { action } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqModifierVersionModeleQuestionnaire, ResModifierVersionModeleQuestionnaire } from './MdlModifierVersionModeleQuestionnaire';

const listerRubriqueParIdVersionModeleQuestionnaireImpl = async (requete: ReqModifierVersionModeleQuestionnaire, resultat: ResModifierVersionModeleQuestionnaire, thunkAPI) => {
    resultat.listeRubrique = await ServiceRubrique.listerParIdVersionModeleQuestionnaire(requete.idVersionModeleQuestionnaire);
};

const recupererVersionModeleQuestionnaireParIdImpl = async (requete: ReqModifierVersionModeleQuestionnaire, resultat: ResModifierVersionModeleQuestionnaire, thunkAPI) => {
    resultat.versionModeleQuestionnaire = await ServiceVersionModeleQuestionnaire.recupererParId(requete.idVersionModeleQuestionnaire);
};

const CtrlModifierVersionModeleQuestionnaire = {
    listerRubriqueParIdVersionModeleQuestionnaire: action<ReqModifierVersionModeleQuestionnaire, ResModifierVersionModeleQuestionnaire>(listerRubriqueParIdVersionModeleQuestionnaireImpl, ActionConception.UcModifierVersionModeleQuestionnaire.LISTER_RUBRIQUE_PAR_ID_VERSION_MODELE_QUESTIONNAIRE),
    recupererVersionModeleQuestionnaireParId: action<ReqModifierVersionModeleQuestionnaire, ResModifierVersionModeleQuestionnaire>(recupererVersionModeleQuestionnaireParIdImpl, ActionConception.UcModifierVersionModeleQuestionnaire.RECUPERER_VERSION_MODELE_QUESTIONNAIRE_PAR_ID),
};

export default CtrlModifierVersionModeleQuestionnaire;