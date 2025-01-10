import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IVersionModeleQuestionnaire } from 'modele/conception/versionModeleQuestionnaire/DomaineVersionModeleQuestionnaire';

export interface IRubrique {
    id?: number;
    idRubrique?: number;
    titre?: string;
    description?: string;
    position?: number;
    versionModeleQuestionnaire?: IVersionModeleQuestionnaire;
}

export interface IRequeteRubrique extends IRubrique, IPagination { }
export interface IListePagineeRubrique {
    liste?: IRubrique[];
    pagination?: IPagination;
}