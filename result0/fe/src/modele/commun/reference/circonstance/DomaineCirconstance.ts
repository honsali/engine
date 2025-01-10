import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface ICirconstance {
    id?: number;
    idCirconstance?: number;
    partResponsabiliteCode?: string;
    partResponsabiliteLibelle?: string;
}

export interface IRequeteCirconstance extends ICirconstance, IPagination { }
export interface IListePagineeCirconstance {
    liste?: ICirconstance[];
    pagination?: IPagination;
}