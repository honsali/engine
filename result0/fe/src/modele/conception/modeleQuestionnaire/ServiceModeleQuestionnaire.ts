import axios from 'axios';
import { API_URL } from 'commun';
import { IModeleQuestionnaire } from './DomaineModeleQuestionnaire';

const resourceUri = API_URL + '/modeleQuestionnaire';

const creer = async (modeleQuestionnaire: IModeleQuestionnaire) => {
    return (await axios.post(`${resourceUri}`, modeleQuestionnaire)).data.id;
};

const lister = async () => {
    const listeModeleQuestionnaire: IModeleQuestionnaire[] = (await axios.get<IModeleQuestionnaire[]>(`${resourceUri}/lister`)).data;
    return listeModeleQuestionnaire;
};

const ServiceModeleQuestionnaire = {
    creer,
    lister,
};

export default ServiceModeleQuestionnaire;