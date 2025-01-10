import ServiceAyantDroit from 'modele/tiers/ayantDroit/ServiceAyantDroit';
import { action, util } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqModifierAyantDroit, ResModifierAyantDroit } from './MdlModifierAyantDroit';

const enregistrerAyantDroitImpl = async (requete: ReqModifierAyantDroit, resultat: ResModifierAyantDroit, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceAyantDroit.enregistrer(requete.idSinistre, requete.idSousDossier, dataForm);
};

const initModificationAyantDroitImpl = async (requete: ReqModifierAyantDroit, resultat: ResModifierAyantDroit, thunkAPI) => {
    resultat.ayantDroit = await ServiceAyantDroit.recupererParId(requete.idSinistre, requete.idSousDossier, requete.idAyantDroit);
};

const CtrlModifierAyantDroit = {
    enregistrerAyantDroit: action<ReqModifierAyantDroit, ResModifierAyantDroit>(enregistrerAyantDroitImpl, ActionTiers.UcModifierAyantDroit.ENREGISTRER_AYANT_DROIT),
    initModificationAyantDroit: action<ReqModifierAyantDroit, ResModifierAyantDroit>(initModificationAyantDroitImpl, ActionTiers.UcModifierAyantDroit.INIT_MODIFICATION_AYANT_DROIT),
};

export default CtrlModifierAyantDroit;