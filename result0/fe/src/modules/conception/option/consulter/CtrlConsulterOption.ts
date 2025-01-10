import ServiceOption from 'modele/conception/option/ServiceOption';
import { action } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqConsulterOption, ResConsulterOption } from './MdlConsulterOption';

const recupererOptionParIdImpl = async (requete: ReqConsulterOption, resultat: ResConsulterOption, thunkAPI) => {
    resultat.option = await ServiceOption.recupererParId(requete.idOption);
};

const supprimerOptionImpl = async (requete: ReqConsulterOption, resultat: ResConsulterOption, thunkAPI) => {
    await ServiceOption.supprimer(requete.idOption);
};

const CtrlConsulterOption = {
    recupererOptionParId: action<ReqConsulterOption, ResConsulterOption>(recupererOptionParIdImpl, ActionConception.UcConsulterOption.RECUPERER_OPTION_PAR_ID),
    supprimerOption: action<ReqConsulterOption, ResConsulterOption>(supprimerOptionImpl, ActionConception.UcConsulterOption.SUPPRIMER_OPTION),
};

export default CtrlConsulterOption;