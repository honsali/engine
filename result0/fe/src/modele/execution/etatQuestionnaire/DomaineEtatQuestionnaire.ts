import { IPagination } from 'modele/commun/pagination/DomainePagination';

export interface IEtatQuestionnaire {
    id?: number;
    idEtatQuestionnaire?: number;
    libelle?: string;
}

export interface IRequeteEtatQuestionnaire extends IEtatQuestionnaire, IPagination { }
export interface IListePagineeEtatQuestionnaire {
    liste?: IEtatQuestionnaire[];
    pagination?: IPagination;
}