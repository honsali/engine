import axios from 'axios';
import { API_URL } from 'commun';
import { IVersionModeleQuestionnaire } from './DomaineVersionModeleQuestionnaire';

const resourceUri = API_URL + '/versionModeleQuestionnaire';

const recupererParId = async (idVersionModeleQuestionnaire: string) => {
    const versionModeleQuestionnaire: IVersionModeleQuestionnaire = (await axios.get<IVersionModeleQuestionnaire>(`${resourceUri}/${idVersionModeleQuestionnaire}`)).data;
    return versionModeleQuestionnaire;
};

const ServiceVersionModeleQuestionnaire = {
    recupererParId,
};

export default ServiceVersionModeleQuestionnaire;