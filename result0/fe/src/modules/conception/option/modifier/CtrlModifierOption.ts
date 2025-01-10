import ServiceOption from 'modele/conception/option/ServiceOption';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqModifierOption, ResModifierOption } from './MdlModifierOption';

const enregistrerOptionImpl = async (requete: ReqModifierOption, resultat: ResModifierOption, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceOption.enregistrer(dataForm);
};

const initModificationOptionImpl = async (requete: ReqModifierOption, resultat: ResModifierOption, thunkAPI) => {
    resultat.option = await ServiceOption.recupererParId(requete.idOption);
};

const CtrlModifierOption = {
    enregistrerOption: action<ReqModifierOption, ResModifierOption>(enregistrerOptionImpl, ActionConception.UcModifierOption.ENREGISTRER_OPTION),
    initModificationOption: action<ReqModifierOption, ResModifierOption>(initModificationOptionImpl, ActionConception.UcModifierOption.INIT_MODIFICATION_OPTION),
};

export default CtrlModifierOption;