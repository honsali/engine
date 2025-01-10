import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IDocumentGed {
    id?: number;
    idDocumentGed?: number;
    idDocument?: number;
    codeDocument?: string;
    referenceDocument?: string;
    codeFamille?: string;
    mimeType?: string;
    numeroPage?: number;
    instanceGed?: string;
    nombreTotalPage?: number;
    pageURL?: string;
}

export interface IRequeteDocumentGed extends IDocumentGed, IPagination { }
export interface IListePagineeDocumentGed {
    liste?: IDocumentGed[];
    pagination?: IPagination;
}