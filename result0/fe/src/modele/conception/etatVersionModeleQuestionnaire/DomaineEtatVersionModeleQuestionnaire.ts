import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IEtatVersionModeleQuestionnaire {
    id?: number;
    idEtatVersionModeleQuestionnaire?: number;
    libelle?: string;
}

export interface IRequeteEtatVersionModeleQuestionnaire extends IEtatVersionModeleQuestionnaire, IPagination { }
export interface IListePagineeEtatVersionModeleQuestionnaire {
    liste?: IEtatVersionModeleQuestionnaire[];
    pagination?: IPagination;
}