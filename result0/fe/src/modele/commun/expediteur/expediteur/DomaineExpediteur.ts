import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface IExpediteur {
    id?: number;
    idExpediteur?: number;
    code?: string;
    libelle?: string;
    description?: string;
    actif?: string;
    typeExpediteur?: IReference;
    ville?: IReference;
    telephone?: string;
    email?: string;
    adresse?: string;
    region?: string;
}

export interface IRequeteExpediteur extends IExpediteur, IPagination { }
export interface IListePagineeExpediteur {
    liste?: IExpediteur[];
    pagination?: IPagination;
}