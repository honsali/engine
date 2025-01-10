import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface INotification {
    id?: number;
    idNotification?: number;
    message?: string;
    utilisateur?: string;
    active?: boolean;
    typeNotification?: IReference;
    dateCreation?: string;
    createur?: string;
}

export interface IRequeteNotification extends INotification, IPagination { }
export interface IListePagineeNotification {
    liste?: INotification[];
    pagination?: IPagination;
}