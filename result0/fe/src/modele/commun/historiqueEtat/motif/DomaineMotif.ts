import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IMotif {
    id?: number;
    idMotif?: number;
    libelle?: string;
}

export interface IRequeteMotif extends IMotif, IPagination { }
export interface IListePagineeMotif {
    liste?: IMotif[];
    pagination?: IPagination;
}