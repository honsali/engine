import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IEtat {
    id?: number;
    idEtat?: number;
    libelle?: string;
}

export interface IRequeteEtat extends IEtat, IPagination { }
export interface IListePagineeEtat {
    liste?: IEtat[];
    pagination?: IPagination;
}