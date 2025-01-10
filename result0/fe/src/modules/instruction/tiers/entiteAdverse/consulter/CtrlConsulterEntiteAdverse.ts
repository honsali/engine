import ServiceEntiteAdverse from 'modele/tiers/entiteAdverse/ServiceEntiteAdverse';
import { action } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqConsulterEntiteAdverse, ResConsulterEntiteAdverse } from './MdlConsulterEntiteAdverse';

const envoyerSMSEntiteAdverseImpl = async (requete: ReqConsulterEntiteAdverse, resultat: ResConsulterEntiteAdverse, thunkAPI) => {
    await ServiceEntiteAdverse.envoyerSMS(requete.idSinistre, requete.idEntiteAdverse);
};

const recupererEntiteAdverseParIdImpl = async (requete: ReqConsulterEntiteAdverse, resultat: ResConsulterEntiteAdverse, thunkAPI) => {
    resultat.entiteAdverse = await ServiceEntiteAdverse.recupererParId(requete.idSinistre, requete.idEntiteAdverse);
};

const CtrlConsulterEntiteAdverse = {
    envoyerSMSEntiteAdverse: action<ReqConsulterEntiteAdverse, ResConsulterEntiteAdverse>(envoyerSMSEntiteAdverseImpl, ActionTiers.UcConsulterEntiteAdverse.ENVOYER_SMS_ENTITE_ADVERSE),
    recupererEntiteAdverseParId: action<ReqConsulterEntiteAdverse, ResConsulterEntiteAdverse>(recupererEntiteAdverseParIdImpl, ActionTiers.UcConsulterEntiteAdverse.RECUPERER_ENTITE_ADVERSE_PAR_ID),
};

export default CtrlConsulterEntiteAdverse;