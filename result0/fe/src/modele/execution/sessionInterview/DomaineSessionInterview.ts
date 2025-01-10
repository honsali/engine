import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IQuestionnaire } from 'modele/execution/questionnaire/DomaineQuestionnaire';

export interface ISessionInterview {
    id?: number;
    idSessionInterview?: number;
    code?: string;
    dateDebut?: string;
    dateFin?: string;
    heureDebut?: string;
    heureFin?: string;
    questionnaire?: IQuestionnaire;
}

export interface IRequeteSessionInterview extends ISessionInterview, IPagination { }
export interface IListePagineeSessionInterview {
    liste?: ISessionInterview[];
    pagination?: IPagination;
}