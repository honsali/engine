import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IContrat {
    id?: number;
    idContrat?: number;
    categoriePolice?: string;
    numeroPolice?: string;
}

export interface IRequeteContrat extends IContrat, IPagination { }
export interface IListePagineeContrat {
    liste?: IContrat[];
    pagination?: IPagination;
}