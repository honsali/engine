import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IVersionModeleQuestionnaire } from 'modele/conception/versionModeleQuestionnaire/DomaineVersionModeleQuestionnaire';

export interface IModeleQuestionnaire {
    id?: number;
    idModeleQuestionnaire?: number;
    nom?: string;
    description?: string;
    dateCreation?: string;
    versionProduction?: IVersionModeleQuestionnaire;
    versionDeveloppement?: IVersionModeleQuestionnaire;
}

export interface IRequeteModeleQuestionnaire extends IModeleQuestionnaire, IPagination { }
export interface IListePagineeModeleQuestionnaire {
    liste?: IModeleQuestionnaire[];
    pagination?: IPagination;
}