import ServiceTransition from 'modele/conception/transition/ServiceTransition';
import { action } from 'waxant';
import { ActionConception } from '../../ActionConception';
import { ReqConsulterTransition, ResConsulterTransition } from './MdlConsulterTransition';

const recupererTransitionParIdImpl = async (requete: ReqConsulterTransition, resultat: ResConsulterTransition, thunkAPI) => {
    resultat.transition = await ServiceTransition.recupererParId(requete.idTransition);
};

const supprimerTransitionImpl = async (requete: ReqConsulterTransition, resultat: ResConsulterTransition, thunkAPI) => {
    await ServiceTransition.supprimer(requete.idTransition);
};

const CtrlConsulterTransition = {
    recupererTransitionParId: action<ReqConsulterTransition, ResConsulterTransition>(recupererTransitionParIdImpl, ActionConception.UcConsulterTransition.RECUPERER_TRANSITION_PAR_ID),
    supprimerTransition: action<ReqConsulterTransition, ResConsulterTransition>(supprimerTransitionImpl, ActionConception.UcConsulterTransition.SUPPRIMER_TRANSITION),
};

export default CtrlConsulterTransition;