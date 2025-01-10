import ServiceOption from 'modele/conception/option/ServiceOption';
import ServiceQuestion from 'modele/conception/question/ServiceQuestion';
import ServiceTransition from 'modele/conception/transition/ServiceTransition';
import { action } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqConsulterQuestion, ResConsulterQuestion } from './MdlConsulterQuestion';

const listerOptionParIdQuestionImpl = async (requete: ReqConsulterQuestion, resultat: ResConsulterQuestion, thunkAPI) => {
    resultat.listeOption = await ServiceOption.listerParIdQuestion(requete.idQuestion);
};

const listerTransitionParIdQuestionImpl = async (requete: ReqConsulterQuestion, resultat: ResConsulterQuestion, thunkAPI) => {
    resultat.listeTransition = await ServiceTransition.listerParIdQuestion(requete.idQuestion);
};

const recupererQuestionParIdImpl = async (requete: ReqConsulterQuestion, resultat: ResConsulterQuestion, thunkAPI) => {
    resultat.question = await ServiceQuestion.recupererParId(requete.idQuestion);
};

const CtrlConsulterQuestion = {
    listerOptionParIdQuestion: action<ReqConsulterQuestion, ResConsulterQuestion>(listerOptionParIdQuestionImpl, ActionConception.UcConsulterQuestion.LISTER_OPTION_PAR_ID_QUESTION),
    listerTransitionParIdQuestion: action<ReqConsulterQuestion, ResConsulterQuestion>(listerTransitionParIdQuestionImpl, ActionConception.UcConsulterQuestion.LISTER_TRANSITION_PAR_ID_QUESTION),
    recupererQuestionParId: action<ReqConsulterQuestion, ResConsulterQuestion>(recupererQuestionParIdImpl, ActionConception.UcConsulterQuestion.RECUPERER_QUESTION_PAR_ID),
};

export default CtrlConsulterQuestion;