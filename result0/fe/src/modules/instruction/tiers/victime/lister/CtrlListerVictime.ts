import ServiceAyantDroit from 'modele/tiers/ayantDroit/ServiceAyantDroit';
import ServiceVictime from 'modele/tiers/victime/ServiceVictime';
import { action } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqListerVictime, ResListerVictime } from './MdlListerVictime';

const listerAyantDroitImpl = async (requete: ReqListerVictime, resultat: ResListerVictime, thunkAPI) => {
    resultat.listeAyantDroit = await ServiceAyantDroit.lister(requete.idSousDossier);
};

const listerVictimeImpl = async (requete: ReqListerVictime, resultat: ResListerVictime, thunkAPI) => {
    resultat.listeVictime = await ServiceVictime.lister(requete.idSinistre);
};

const CtrlListerVictime = {
    listerAyantDroit: action<ReqListerVictime, ResListerVictime>(listerAyantDroitImpl, ActionTiers.UcListerVictime.LISTER_AYANT_DROIT),
    listerVictime: action<ReqListerVictime, ResListerVictime>(listerVictimeImpl, ActionTiers.UcListerVictime.LISTER_VICTIME),
};

export default CtrlListerVictime;