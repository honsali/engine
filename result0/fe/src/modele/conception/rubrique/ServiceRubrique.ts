import axios from 'axios';
import { API_URL } from 'commun';
import { IRubrique } from './DomaineRubrique';

const resourceUri = API_URL + '/rubrique';

const creer = async (idVersionModeleQuestionnaire: string, rubrique: IRubrique) => {
    return (await axios.post(`${resourceUri}/versionModeleQuestionnaire/${idVersionModeleQuestionnaire}`, rubrique)).data.id;
};

const enregistrer = async (rubrique: IRubrique) => {
    return (await axios.put(`${resourceUri}`, rubrique)).data;
};

const listerParIdVersionModeleQuestionnaire = async (idVersionModeleQuestionnaire: string) => {
    const listeRubrique: IRubrique[] = (await axios.get<IRubrique[]>(`${resourceUri}/listerParIdVersionModeleQuestionnaire/${idVersionModeleQuestionnaire}`)).data;
    return listeRubrique;
};

const recupererParId = async (idRubrique: string) => {
    const rubrique: IRubrique = (await axios.get<IRubrique>(`${resourceUri}/${idRubrique}`)).data;
    return rubrique;
};

const ServiceRubrique = {
    creer,
    enregistrer,
    listerParIdVersionModeleQuestionnaire,
    recupererParId,
};

export default ServiceRubrique;