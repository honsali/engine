import axios from 'axios';
import { API_URL } from 'commun';

const resourceUri = API_URL + '/reponse';

const enregistrer = async (reponse: IReponse) => {
    return (await axios.put(`${resourceUri}`, reponse)).data;
};

const ServiceReponse = {
    enregistrer,
};

export default ServiceReponse;