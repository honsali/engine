import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IReference {
    id?: number;
    idReference?: number;
    code?: string;
    libelle?: string;
    description?: string;
    taux?: string;
    nomOuRaisonSociale?: string;
}

export interface IRequeteReference extends IReference, IPagination { }
export interface IListePagineeReference {
    liste?: IReference[];
    pagination?: IPagination;
}