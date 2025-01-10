import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IVersionModeleQuestionnaire } from 'modele/conception/versionModeleQuestionnaire/DomaineVersionModeleQuestionnaire';
import { IEtatQuestionnaire } from 'modele/execution/etatQuestionnaire/DomaineEtatQuestionnaire';

export interface IQuestionnaire {
    id?: number;
    idQuestionnaire?: number;
    code?: string;
    dateCreation?: string;
    nombreQuestion?: number;
    derniereQuestion?: number;
    versionModeleQuestionnaire?: IVersionModeleQuestionnaire;
    etat?: IEtatQuestionnaire;
}

export interface IRequeteQuestionnaire extends IQuestionnaire, IPagination { }
export interface IListePagineeQuestionnaire {
    liste?: IQuestionnaire[];
    pagination?: IPagination;
}