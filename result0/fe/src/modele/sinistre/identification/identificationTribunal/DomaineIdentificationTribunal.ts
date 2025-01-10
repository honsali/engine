import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface IIdentificationTribunal {
    id?: number;
    idIdentificationTribunal?: number;
    villeTribunal?: IReference;
    tribunal?: IReference;
    numeroDossier?: string;
    numeroChambre?: IReference;
    phase?: IReference;
    exercice?: string;
    dateAudience?: string;
    dateJugement?: string;
}

export interface IRequeteIdentificationTribunal extends IIdentificationTribunal, IPagination { }
export interface IListePagineeIdentificationTribunal {
    liste?: IIdentificationTribunal[];
    pagination?: IPagination;
}