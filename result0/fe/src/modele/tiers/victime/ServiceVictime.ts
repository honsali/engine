import axios from 'axios';
import { API_URL } from 'commun';
import { IVictime } from './DomaineVictime';

const resourceUri = API_URL + '/victime';

const creer = async (idSinistre: string, victime: IVictime) => {
    return (await axios.post(`${resourceUri}/sinistre/${idSinistre}`, victime)).data.id;
};

const enregistrer = async (idSinistre: string, victime: IVictime) => {
    return (await axios.put(`${resourceUri}/sinistre/${idSinistre}`, victime)).data;
};

const envoyerSMS = async (idSinistre: string, idVictime: string) => {
    return (await axios.get(`${resourceUri}/sinistre/${idSinistre}/${idVictime}`)).data;
};

const lister = async (idSinistre: string) => {
    const listeVictime: IVictime[] = (await axios.get<IVictime[]>(`${resourceUri}/lister/sinistre/${idSinistre}`)).data;
    return listeVictime;
};

const recupererParId = async (idVictime: string) => {
    const victime: IVictime = (await axios.get<IVictime>(`${resourceUri}/${idVictime}`)).data;
    return victime;
};

const ServiceVictime = {
    creer,
    enregistrer,
    envoyerSMS,
    lister,
    recupererParId,
};

export default ServiceVictime;