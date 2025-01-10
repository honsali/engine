import ServiceIdentification from 'modele/sinistre/identification/ServiceIdentification';
import { action, util } from 'waxant';
import { ActionIdentification } from '../ActionIdentification';
import { ReqChercherIdentification, ResChercherIdentification } from './MdlChercherIdentification';

const changerPageIdentificationImpl = async (requete: ReqChercherIdentification, resultat: ResChercherIdentification, thunkAPI) => {
    const { mdlChercherIdentification } = thunkAPI.getState() as any;
    resultat.listePagineeIdentification = await ServiceIdentification.chercher({ ...mdlChercherIdentification.filtre, pageCourante: requete.pageCourante });
};

const chercherIdentificationImpl = async (requete: ReqChercherIdentification, resultat: ResChercherIdentification, thunkAPI) => {
    const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());
    resultat.listePagineeIdentification = await ServiceIdentification.chercher({ ...dataForm, pageCourante: 0 });
    resultat.filtre = dataForm;
};

const CtrlChercherIdentification = {
    changerPageIdentification: action<ReqChercherIdentification, ResChercherIdentification>(changerPageIdentificationImpl, ActionIdentification.UcChercherIdentification.CHANGER_PAGE_IDENTIFICATION),
    chercherIdentification: action<ReqChercherIdentification, ResChercherIdentification>(chercherIdentificationImpl, ActionIdentification.UcChercherIdentification.CHERCHER_IDENTIFICATION),
};

export default CtrlChercherIdentification;