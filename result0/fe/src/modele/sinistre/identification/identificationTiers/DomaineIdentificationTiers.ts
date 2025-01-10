import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface IIdentificationTiers {
    id?: number;
    idIdentificationTiers?: number;
    numeroIdentification?: string;
    libelleTiers?: string;
    compagnieAdverse?: IReference;
}

export interface IRequeteIdentificationTiers extends IIdentificationTiers, IPagination { }
export interface IListePagineeIdentificationTiers {
    liste?: IIdentificationTiers[];
    pagination?: IPagination;
}