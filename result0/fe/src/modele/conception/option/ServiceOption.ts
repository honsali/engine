import axios from 'axios';
import { API_URL } from 'commun';
import { IOption } from './DomaineOption';

const resourceUri = API_URL + '/option';

const creer = async (idQuestion: string, option: IOption) => {
    return (await axios.post(`${resourceUri}/question/${idQuestion}`, option)).data.id;
};

const enregistrer = async (option: IOption) => {
    return (await axios.put(`${resourceUri}`, option)).data;
};

const listerParIdQuestion = async (idQuestion: string) => {
    const listeOption: IOption[] = (await axios.get<IOption[]>(`${resourceUri}/listerParIdQuestion/${idQuestion}`)).data;
    return listeOption;
};

const recupererParId = async (idOption: string) => {
    const option: IOption = (await axios.get<IOption>(`${resourceUri}/${idOption}`)).data;
    return option;
};

const supprimer = async (idOption: string) => {
    return (await axios.delete(`${resourceUri}/${idOption}`)).data;
};

const ServiceOption = {
    creer,
    enregistrer,
    listerParIdQuestion,
    recupererParId,
    supprimer,
};

export default ServiceOption;