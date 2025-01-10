import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IIdentificationContrat {
    id?: number;
    idIdentificationContrat?: number;
    categoriePolice?: string;
    numeroPolice?: string;
    numeroSerie?: string;
    dateSouscription?: string;
    codeIntermediaire?: string;
    libelleIntermediaire?: string;
}

export interface IRequeteIdentificationContrat extends IIdentificationContrat, IPagination { }
export interface IListePagineeIdentificationContrat {
    liste?: IIdentificationContrat[];
    pagination?: IPagination;
}