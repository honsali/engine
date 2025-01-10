import ServiceOption from 'modele/conception/option/ServiceOption';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqCreerOption, ResCreerOption } from './MdlCreerOption';

const creerOptionImpl = async (requete: ReqCreerOption, resultat: ResCreerOption, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idOption = await ServiceOption.creer(requete.idQuestion, dataForm);
};

const initCreationOptionImpl = async (requete: ReqCreerOption, resultat: ResCreerOption, thunkAPI) => {
    resultat.option = {};
};

const CtrlCreerOption = {
    creerOption: action<ReqCreerOption, ResCreerOption>(creerOptionImpl, ActionConception.UcCreerOption.CREER_OPTION),
    initCreationOption: action<ReqCreerOption, ResCreerOption>(initCreationOptionImpl, ActionConception.UcCreerOption.INIT_CREATION_OPTION),
};

export default CtrlCreerOption;