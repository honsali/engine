import { IDocumentGed } from 'modele/commun/documentGed/documentGed/DomaineDocumentGed';
import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IEmail {
    id?: number;
    idEmail?: number;
    adresseMail?: string;
    cc?: string;
    cci?: string;
    expediteurCode?: string;
    objet?: string;
    message?: string;
    documentGed?: IDocumentGed;
    cible?: string;
}

export interface IRequeteEmail extends IEmail, IPagination { }
export interface IListePagineeEmail {
    liste?: IEmail[];
    pagination?: IPagination;
}