import axios from 'axios';
import { API_URL } from 'commun';
import { IAyantDroit } from './DomaineAyantDroit';

const resourceUri = API_URL + '/ayantDroit';

const creer = async (idSinistre, idSousDossier: string, ayantDroit: IAyantDroit) => {
    return (await axios.post(`${resourceUri}/sinistre/${idSinistre}/sousDossier/${idSousDossier}`, ayantDroit)).data.id;
};

const enregistrer = async (idSinistre, idSousDossier: string, ayantDroit: IAyantDroit) => {
    return (await axios.put(`${resourceUri}/sinistre/${idSinistre}/sousDossier/${idSousDossier}`, ayantDroit)).data;
};

const lister = async (idSousDossier: string) => {
    const listeAyantDroit: IAyantDroit[] = (await axios.get<IAyantDroit[]>(`${resourceUri}/lister/sousDossier/${idSousDossier}`)).data;
    return listeAyantDroit;
};

const recupererParId = async (idAyantDroit: string) => {
    const ayantDroit: IAyantDroit = (await axios.get<IAyantDroit>(`${resourceUri}/${idAyantDroit}`)).data;
    return ayantDroit;
};

const ServiceAyantDroit = {
    creer,
    enregistrer,
    lister,
    recupererParId,
};

export default ServiceAyantDroit;