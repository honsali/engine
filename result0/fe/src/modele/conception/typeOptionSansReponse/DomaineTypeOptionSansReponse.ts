import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface ITypeOptionSansReponse {
    id?: number;
    idTypeOptionSansReponse?: number;
    code?: string;
    libelle?: string;
}

export interface IRequeteTypeOptionSansReponse extends ITypeOptionSansReponse, IPagination { }
export interface IListePagineeTypeOptionSansReponse {
    liste?: ITypeOptionSansReponse[];
    pagination?: IPagination;
}