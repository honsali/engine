import ServiceQuestion from 'modele/conception/question/ServiceQuestion';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqModifierQuestion, ResModifierQuestion } from './MdlModifierQuestion';

const enregistrerQuestionImpl = async (requete: ReqModifierQuestion, resultat: ResModifierQuestion, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceQuestion.enregistrer(dataForm);
};

const initModificationQuestionImpl = async (requete: ReqModifierQuestion, resultat: ResModifierQuestion, thunkAPI) => {
    resultat.question = await ServiceQuestion.recupererParId(requete.idQuestion);
};

const CtrlModifierQuestion = {
    enregistrerQuestion: action<ReqModifierQuestion, ResModifierQuestion>(enregistrerQuestionImpl, ActionConception.UcModifierQuestion.ENREGISTRER_QUESTION),
    initModificationQuestion: action<ReqModifierQuestion, ResModifierQuestion>(initModificationQuestionImpl, ActionConception.UcModifierQuestion.INIT_MODIFICATION_QUESTION),
};

export default CtrlModifierQuestion;