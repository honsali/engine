import ServiceQuestion from 'modele/conception/question/ServiceQuestion';
import ServiceRubrique from 'modele/conception/rubrique/ServiceRubrique';
import { action } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqConsulterRubrique, ResConsulterRubrique } from './MdlConsulterRubrique';

const listerQuestionParIdRubriqueImpl = async (requete: ReqConsulterRubrique, resultat: ResConsulterRubrique, thunkAPI) => {
    resultat.listeQuestion = await ServiceQuestion.listerParIdRubrique(requete.idRubrique);
};

const recupererRubriqueParIdImpl = async (requete: ReqConsulterRubrique, resultat: ResConsulterRubrique, thunkAPI) => {
    resultat.rubrique = await ServiceRubrique.recupererParId(requete.idRubrique);
};

const CtrlConsulterRubrique = {
    listerQuestionParIdRubrique: action<ReqConsulterRubrique, ResConsulterRubrique>(listerQuestionParIdRubriqueImpl, ActionConception.UcConsulterRubrique.LISTER_QUESTION_PAR_ID_RUBRIQUE),
    recupererRubriqueParId: action<ReqConsulterRubrique, ResConsulterRubrique>(recupererRubriqueParIdImpl, ActionConception.UcConsulterRubrique.RECUPERER_RUBRIQUE_PAR_ID),
};

export default CtrlConsulterRubrique;