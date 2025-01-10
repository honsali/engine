import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IUtilisateur {
    id?: number;
    idUtilisateur?: number;
    login?: string;
    etat?: boolean;
}

export interface IRequeteUtilisateur extends IUtilisateur, IPagination { }
export interface IListePagineeUtilisateur {
    liste?: IUtilisateur[];
    pagination?: IPagination;
}