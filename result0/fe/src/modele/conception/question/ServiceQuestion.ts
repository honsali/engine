import axios from 'axios';
import { API_URL } from 'commun';
import { IQuestion } from './DomaineQuestion';

const resourceUri = API_URL + '/question';

const creer = async (idRubrique: string, question: IQuestion) => {
    return (await axios.post(`${resourceUri}/rubrique/${idRubrique}`, question)).data.id;
};

const enregistrer = async (question: IQuestion) => {
    return (await axios.put(`${resourceUri}`, question)).data;
};

const listerParIdRubrique = async (idRubrique: string) => {
    const listeQuestion: IQuestion[] = (await axios.get<IQuestion[]>(`${resourceUri}/listerParIdRubrique/${idRubrique}`)).data;
    return listeQuestion;
};

const recupererParId = async (idQuestion: string) => {
    const question: IQuestion = (await axios.get<IQuestion>(`${resourceUri}/${idQuestion}`)).data;
    return question;
};

const ServiceQuestion = {
    creer,
    enregistrer,
    listerParIdRubrique,
    recupererParId,
};

export default ServiceQuestion;