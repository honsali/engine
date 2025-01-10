import ServiceAyantDroit from 'modele/tiers/ayantDroit/ServiceAyantDroit';
import { action, util } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqCreerAyantDroit, ResCreerAyantDroit } from './MdlCreerAyantDroit';

const creerAyantDroitImpl = async (requete: ReqCreerAyantDroit, resultat: ResCreerAyantDroit, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idAyantDroit = await ServiceAyantDroit.creer(requete.idSinistre, requete.idSousDossier, dataForm);
};

const initCreationAyantDroitImpl = async (requete: ReqCreerAyantDroit, resultat: ResCreerAyantDroit, thunkAPI) => {
    resultat.ayantDroit = { perteRessource: true };
};

const CtrlCreerAyantDroit = {
    creerAyantDroit: action<ReqCreerAyantDroit, ResCreerAyantDroit>(creerAyantDroitImpl, ActionTiers.UcCreerAyantDroit.CREER_AYANT_DROIT),
    initCreationAyantDroit: action<ReqCreerAyantDroit, ResCreerAyantDroit>(initCreationAyantDroitImpl, ActionTiers.UcCreerAyantDroit.INIT_CREATION_AYANT_DROIT),
};

export default CtrlCreerAyantDroit;