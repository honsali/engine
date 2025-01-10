import ServiceVictime from 'modele/tiers/victime/ServiceVictime';
import { action } from 'waxant';
import { ActionTiers } from '../../ActionTiers';
import { ReqConsulterVictime, ResConsulterVictime } from './MdlConsulterVictime';

const envoyerSMSVictimeImpl = async (requete: ReqConsulterVictime, resultat: ResConsulterVictime, thunkAPI) => {
    await ServiceVictime.envoyerSMS(requete.idSinistre, requete.idVictime);
};

const recupererVictimeParIdImpl = async (requete: ReqConsulterVictime, resultat: ResConsulterVictime, thunkAPI) => {
    resultat.victime = await ServiceVictime.recupererParId(requete.idSinistre, requete.idVictime);
};

const CtrlConsulterVictime = {
    envoyerSMSVictime: action<ReqConsulterVictime, ResConsulterVictime>(envoyerSMSVictimeImpl, ActionTiers.UcConsulterVictime.ENVOYER_SMS_VICTIME),
    recupererVictimeParId: action<ReqConsulterVictime, ResConsulterVictime>(recupererVictimeParIdImpl, ActionTiers.UcConsulterVictime.RECUPERER_VICTIME_PAR_ID),
};

export default CtrlConsulterVictime;