import ServiceEntiteAdverse from 'modele/tiers/entiteAdverse/ServiceEntiteAdverse';
import ServiceVictime from 'modele/tiers/victime/ServiceVictime';
import { action, util } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqModifierVictime, ResModifierVictime } from './MdlModifierVictime';

const enregistrerVictimeImpl = async (requete: ReqModifierVictime, resultat: ResModifierVictime, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceVictime.enregistrer(requete.idSinistre, dataForm);
};

const initModificationVictimeImpl = async (requete: ReqModifierVictime, resultat: ResModifierVictime, thunkAPI) => {
    resultat.victime = await ServiceVictime.recupererParId(requete.idSinistre, requete.idVictime);
    resultat.listeEntiteAdverse = await ServiceEntiteAdverse.lister(requete.idSinistre);
};

const CtrlModifierVictime = {
    enregistrerVictime: action<ReqModifierVictime, ResModifierVictime>(enregistrerVictimeImpl, ActionTiers.UcModifierVictime.ENREGISTRER_VICTIME),
    initModificationVictime: action<ReqModifierVictime, ResModifierVictime>(initModificationVictimeImpl, ActionTiers.UcModifierVictime.INIT_MODIFICATION_VICTIME),
};

export default CtrlModifierVictime;