import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IIdentificationAssure {
    id?: number;
    idIdentificationAssure?: number;
    libelleAssure?: string;
    numeroIdentification?: string;
    numeroVehicule?: string;
    numeroImmatriculation?: string;
}

export interface IRequeteIdentificationAssure extends IIdentificationAssure, IPagination { }
export interface IListePagineeIdentificationAssure {
    liste?: IIdentificationAssure[];
    pagination?: IPagination;
}