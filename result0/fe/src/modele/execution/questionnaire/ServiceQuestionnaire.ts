import axios from 'axios';
import { API_URL } from 'commun';
import { IQuestionnaire } from './DomaineQuestionnaire';

const resourceUri = API_URL + '/questionnaire';

const creer = async (questionnaire: IQuestionnaire) => {
    return (await axios.post(`${resourceUri}`, questionnaire)).data.id;
};

const lister = async () => {
    const listeQuestionnaire: IQuestionnaire[] = (await axios.get<IQuestionnaire[]>(`${resourceUri}/lister`)).data;
    return listeQuestionnaire;
};

const recupererParId = async (idQuestionnaire: string) => {
    const questionnaire: IQuestionnaire = (await axios.get<IQuestionnaire>(`${resourceUri}/${idQuestionnaire}`)).data;
    return questionnaire;
};

const ServiceQuestionnaire = {
    creer,
    lister,
    recupererParId,
};

export default ServiceQuestionnaire;