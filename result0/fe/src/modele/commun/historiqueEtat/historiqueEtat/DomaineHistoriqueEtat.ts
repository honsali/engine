import { IEtat } from 'modele/commun/historiqueEtat/etat/DomaineEtat';
import { IMotif } from 'modele/commun/historiqueEtat/motif/DomaineMotif';
import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IHistoriqueEtat {
    id?: number;
    idHistoriqueEtat?: number;
    dateEffet?: string;
    commentaire?: string;
    etat?: IEtat;
    motif?: IMotif;
    gestionnaire?: string;
    nomEntite?: string;
    idEntite?: string;
}

export interface IRequeteHistoriqueEtat extends IHistoriqueEtat, IPagination { }
export interface IListePagineeHistoriqueEtat {
    liste?: IHistoriqueEtat[];
    pagination?: IPagination;
}