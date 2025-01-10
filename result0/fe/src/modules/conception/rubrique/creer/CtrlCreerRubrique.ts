import ServiceRubrique from 'modele/conception/rubrique/ServiceRubrique';
import { action, util } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqCreerRubrique, ResCreerRubrique } from './MdlCreerRubrique';

const creerRubriqueImpl = async (requete: ReqCreerRubrique, resultat: ResCreerRubrique, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idRubrique = await ServiceRubrique.creer(requete.idVersionModeleQuestionnaire, dataForm);
};

const initCreationRubriqueImpl = async (requete: ReqCreerRubrique, resultat: ResCreerRubrique, thunkAPI) => {
    resultat.rubrique = {};
};

const CtrlCreerRubrique = {
    creerRubrique: action<ReqCreerRubrique, ResCreerRubrique>(creerRubriqueImpl, ActionConception.UcCreerRubrique.CREER_RUBRIQUE),
    initCreationRubrique: action<ReqCreerRubrique, ResCreerRubrique>(initCreationRubriqueImpl, ActionConception.UcCreerRubrique.INIT_CREATION_RUBRIQUE),
};

export default CtrlCreerRubrique;