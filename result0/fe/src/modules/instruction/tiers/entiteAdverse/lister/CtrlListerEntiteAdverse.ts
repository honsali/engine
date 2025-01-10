import ServiceEntiteAdverse from 'modele/tiers/entiteAdverse/ServiceEntiteAdverse';
import { action } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqListerEntiteAdverse, ResListerEntiteAdverse } from './MdlListerEntiteAdverse';

const listerEntiteAdverseImpl = async (requete: ReqListerEntiteAdverse, resultat: ResListerEntiteAdverse, thunkAPI) => {
    resultat.listeEntiteAdverse = await ServiceEntiteAdverse.lister(requete.idSinistre);
};

const CtrlListerEntiteAdverse = {
    listerEntiteAdverse: action<ReqListerEntiteAdverse, ResListerEntiteAdverse>(listerEntiteAdverseImpl, ActionTiers.UcListerEntiteAdverse.LISTER_ENTITE_ADVERSE),
};

export default CtrlListerEntiteAdverse;