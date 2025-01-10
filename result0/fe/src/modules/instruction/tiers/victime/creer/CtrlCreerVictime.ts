import ServiceEntiteAdverse from 'modele/tiers/entiteAdverse/ServiceEntiteAdverse';
import ServiceVictime from 'modele/tiers/victime/ServiceVictime';
import { action, util } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqCreerVictime, ResCreerVictime } from './MdlCreerVictime';

const creerVictimeImpl = async (requete: ReqCreerVictime, resultat: ResCreerVictime, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idVictime = await ServiceVictime.creer(requete.idSinistre, dataForm);
};

const initCreationVictimeImpl = async (requete: ReqCreerVictime, resultat: ResCreerVictime, thunkAPI) => {
    resultat.victime = { typeIdentification: { code: 'CIN' } };
    resultat.listeEntiteAdverse = await ServiceEntiteAdverse.lister(requete.idSinistre);
};

const CtrlCreerVictime = {
    creerVictime: action<ReqCreerVictime, ResCreerVictime>(creerVictimeImpl, ActionTiers.UcCreerVictime.CREER_VICTIME),
    initCreationVictime: action<ReqCreerVictime, ResCreerVictime>(initCreationVictimeImpl, ActionTiers.UcCreerVictime.INIT_CREATION_VICTIME),
};

export default CtrlCreerVictime;