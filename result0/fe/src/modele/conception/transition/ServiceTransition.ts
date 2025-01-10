import axios from 'axios';
import { API_URL } from 'commun';
import { ITransition } from './DomaineTransition';

const resourceUri = API_URL + '/transition';

const creer = async (idQuestion: string, transition: ITransition) => {
    return (await axios.post(`${resourceUri}/question/${idQuestion}`, transition)).data.id;
};

const enregistrer = async (transition: ITransition) => {
    return (await axios.put(`${resourceUri}`, transition)).data;
};

const listerParIdQuestion = async (idQuestion: string) => {
    const listeTransition: ITransition[] = (await axios.get<ITransition[]>(`${resourceUri}/listerParIdQuestion/${idQuestion}`)).data;
    return listeTransition;
};

const recupererParId = async (idTransition: string) => {
    const transition: ITransition = (await axios.get<ITransition>(`${resourceUri}/${idTransition}`)).data;
    return transition;
};

const supprimer = async (idTransition: string) => {
    return (await axios.delete(`${resourceUri}/${idTransition}`)).data;
};

const ServiceTransition = {
    creer,
    enregistrer,
    listerParIdQuestion,
    recupererParId,
    supprimer,
};

export default ServiceTransition;