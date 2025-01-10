import axios from 'axios';
import { API_URL } from 'commun';

const resourceUri = API_URL + '/sessionInterview';

const creer = async (idQuestionnaire: string) => {
    return (await axios.post(`${resourceUri}/questionnaire/${idQuestionnaire}`)).data;
};

const ServiceSessionInterview = {
    creer,
};

export default ServiceSessionInterview;