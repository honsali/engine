import ServiceRubrique from 'modele/conception/rubrique/ServiceRubrique';
import ServiceVersionModeleQuestionnaire from 'modele/conception/versionModeleQuestionnaire/ServiceVersionModeleQuestionnaire';
import { action } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqConsulterVersionModeleQuestionnaire, ResConsulterVersionModeleQuestionnaire } from './MdlConsulterVersionModeleQuestionnaire';

const listerRubriqueParIdVersionModeleQuestionnaireImpl = async (requete: ReqConsulterVersionModeleQuestionnaire, resultat: ResConsulterVersionModeleQuestionnaire, thunkAPI) => {
    resultat.listeRubrique = await ServiceRubrique.listerParIdVersionModeleQuestionnaire(requete.idVersionModeleQuestionnaire);
};

const recupererVersionModeleQuestionnaireParIdImpl = async (requete: ReqConsulterVersionModeleQuestionnaire, resultat: ResConsulterVersionModeleQuestionnaire, thunkAPI) => {
    resultat.versionModeleQuestionnaire = await ServiceVersionModeleQuestionnaire.recupererParId(requete.idVersionModeleQuestionnaire);
};

const CtrlConsulterVersionModeleQuestionnaire = {
    listerRubriqueParIdVersionModeleQuestionnaire: action<ReqConsulterVersionModeleQuestionnaire, ResConsulterVersionModeleQuestionnaire>(listerRubriqueParIdVersionModeleQuestionnaireImpl, ActionConception.UcConsulterVersionModeleQuestionnaire.LISTER_RUBRIQUE_PAR_ID_VERSION_MODELE_QUESTIONNAIRE),
    recupererVersionModeleQuestionnaireParId: action<ReqConsulterVersionModeleQuestionnaire, ResConsulterVersionModeleQuestionnaire>(recupererVersionModeleQuestionnaireParIdImpl, ActionConception.UcConsulterVersionModeleQuestionnaire.RECUPERER_VERSION_MODELE_QUESTIONNAIRE_PAR_ID),
};

export default CtrlConsulterVersionModeleQuestionnaire;