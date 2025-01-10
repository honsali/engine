import { IExpediteur } from 'modele/commun/expediteur/expediteur/DomaineExpediteur';
import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface IIdentificationDocument {
    id?: number;
    idIdentificationDocument?: number;
    referenceDocument?: string;
    numeroDocument?: string;
    familleDocument?: IReference;
    typeDocument?: IReference;
    dateCachet?: string;
    dateReception?: string;
    typeExpediteur?: IReference;
    prestataire?: IExpediteur;
}

export interface IRequeteIdentificationDocument extends IIdentificationDocument, IPagination { }
export interface IListePagineeIdentificationDocument {
    liste?: IIdentificationDocument[];
    pagination?: IPagination;
}