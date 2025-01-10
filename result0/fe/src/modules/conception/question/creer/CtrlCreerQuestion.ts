import ServiceQuestion from 'modele/conception/question/ServiceQuestion';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqCreerQuestion, ResCreerQuestion } from './MdlCreerQuestion';

const creerQuestionImpl = async (requete: ReqCreerQuestion, resultat: ResCreerQuestion, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idQuestion = await ServiceQuestion.creer(requete.idRubrique, dataForm);
};

const initCreationQuestionImpl = async (requete: ReqCreerQuestion, resultat: ResCreerQuestion, thunkAPI) => {
    resultat.question = {};
};

const CtrlCreerQuestion = {
    creerQuestion: action<ReqCreerQuestion, ResCreerQuestion>(creerQuestionImpl, ActionConception.UcCreerQuestion.CREER_QUESTION),
    initCreationQuestion: action<ReqCreerQuestion, ResCreerQuestion>(initCreationQuestionImpl, ActionConception.UcCreerQuestion.INIT_CREATION_QUESTION),
};

export default CtrlCreerQuestion;