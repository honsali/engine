import ServiceEntiteAdverse from 'modele/tiers/entiteAdverse/ServiceEntiteAdverse';
import { action, util } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqCreerEntiteAdverse, ResCreerEntiteAdverse } from './MdlCreerEntiteAdverse';

const creerEntiteAdverseImpl = async (requete: ReqCreerEntiteAdverse, resultat: ResCreerEntiteAdverse, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.idEntiteAdverse = await ServiceEntiteAdverse.creer(requete.idSinistre, dataForm);
};

const initCreationEntiteAdverseImpl = async (requete: ReqCreerEntiteAdverse, resultat: ResCreerEntiteAdverse, thunkAPI) => {
    resultat.entiteAdverse = { personnePhysique: true, degatsMateriel: true, degatsCorporel: false };
};

const CtrlCreerEntiteAdverse = {
    creerEntiteAdverse: action<ReqCreerEntiteAdverse, ResCreerEntiteAdverse>(creerEntiteAdverseImpl, ActionTiers.UcCreerEntiteAdverse.CREER_ENTITE_ADVERSE),
    initCreationEntiteAdverse: action<ReqCreerEntiteAdverse, ResCreerEntiteAdverse>(initCreationEntiteAdverseImpl, ActionTiers.UcCreerEntiteAdverse.INIT_CREATION_ENTITE_ADVERSE),
};

export default CtrlCreerEntiteAdverse;