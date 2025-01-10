import axios from 'axios';
import { API_URL } from 'commun';
import { IEntiteAdverse } from './DomaineEntiteAdverse';

const resourceUri = API_URL + '/entiteAdverse';

const creer = async (idSinistre: string, entiteAdverse: IEntiteAdverse) => {
    return (await axios.post(`${resourceUri}/sinistre/${idSinistre}`, entiteAdverse)).data.id;
};

const enregistrer = async (idSinistre: string, entiteAdverse: IEntiteAdverse) => {
    return (await axios.put(`${resourceUri}/sinistre/${idSinistre}`, entiteAdverse)).data;
};

const envoyerSMS = async (idSinistre: string, idEntiteAdverse: string) => {
    return (await axios.get(`${resourceUri}/sinistre/${idSinistre}/${idEntiteAdverse}`)).data;
};

const lister = async (idSinistre: string) => {
    const listeEntiteAdverse: IEntiteAdverse[] = (await axios.get<IEntiteAdverse[]>(`${resourceUri}/lister/sinistre/${idSinistre}`)).data;
    return listeEntiteAdverse;
};

const recupererParId = async (idEntiteAdverse: string) => {
    const entiteAdverse: IEntiteAdverse = (await axios.get<IEntiteAdverse>(`${resourceUri}/${idEntiteAdverse}`)).data;
    return entiteAdverse;
};

const ServiceEntiteAdverse = {
    creer,
    enregistrer,
    envoyerSMS,
    lister,
    recupererParId,
};

export default ServiceEntiteAdverse;