import ServiceEntiteAdverse from 'modele/tiers/entiteAdverse/ServiceEntiteAdverse';
import { action, util } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqModifierEntiteAdverse, ResModifierEntiteAdverse } from './MdlModifierEntiteAdverse';

const enregistrerEntiteAdverseImpl = async (requete: ReqModifierEntiteAdverse, resultat: ResModifierEntiteAdverse, thunkAPI) => {
    await requete.form.validateFields();
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    await ServiceEntiteAdverse.enregistrer(requete.idSinistre, dataForm);
};

const initModificationEntiteAdverseImpl = async (requete: ReqModifierEntiteAdverse, resultat: ResModifierEntiteAdverse, thunkAPI) => {
    resultat.entiteAdverse = await ServiceEntiteAdverse.recupererParId(requete.idSinistre, requete.idEntiteAdverse);
};

const CtrlModifierEntiteAdverse = {
    enregistrerEntiteAdverse: action<ReqModifierEntiteAdverse, ResModifierEntiteAdverse>(enregistrerEntiteAdverseImpl, ActionTiers.UcModifierEntiteAdverse.ENREGISTRER_ENTITE_ADVERSE),
    initModificationEntiteAdverse: action<ReqModifierEntiteAdverse, ResModifierEntiteAdverse>(initModificationEntiteAdverseImpl, ActionTiers.UcModifierEntiteAdverse.INIT_MODIFICATION_ENTITE_ADVERSE),
};

export default CtrlModifierEntiteAdverse;