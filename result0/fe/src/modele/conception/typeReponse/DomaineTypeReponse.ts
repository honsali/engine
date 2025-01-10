import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface ITypeReponse {
    id?: number;
    idTypeReponse?: number;
    code?: string;
    libelle?: string;
}

export interface IRequeteTypeReponse extends ITypeReponse, IPagination { }
export interface IListePagineeTypeReponse {
    liste?: ITypeReponse[];
    pagination?: IPagination;
}