import ServiceAyantDroit from 'modele/tiers/ayantDroit/ServiceAyantDroit';
import { action } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqConsulterAyantDroit, ResConsulterAyantDroit } from './MdlConsulterAyantDroit';

const recupererAyantDroitParIdImpl = async (requete: ReqConsulterAyantDroit, resultat: ResConsulterAyantDroit, thunkAPI) => {
    resultat.ayantDroit = await ServiceAyantDroit.recupererParId(requete.idSinistre, requete.idSousDossier, requete.idAyantDroit);
};

const CtrlConsulterAyantDroit = {
    recupererAyantDroitParId: action<ReqConsulterAyantDroit, ResConsulterAyantDroit>(recupererAyantDroitParIdImpl, ActionTiers.UcConsulterAyantDroit.RECUPERER_AYANT_DROIT_PAR_ID),
};

export default CtrlConsulterAyantDroit;