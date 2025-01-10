import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IIdentificationSinistre {
    id?: number;
    idIdentificationSinistre?: number;
    numeroSinistre?: string;
    dateSurvenance?: string;
    dateOuverture?: string;
}

export interface IRequeteIdentificationSinistre extends IIdentificationSinistre, IPagination { }
export interface IListePagineeIdentificationSinistre {
    liste?: IIdentificationSinistre[];
    pagination?: IPagination;
}