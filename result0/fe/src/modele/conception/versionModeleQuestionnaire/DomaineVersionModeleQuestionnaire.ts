import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IEtatVersionModeleQuestionnaire } from 'modele/conception/etatVersionModeleQuestionnaire/DomaineEtatVersionModeleQuestionnaire';
import { IModeleQuestionnaire } from 'modele/conception/modeleQuestionnaire/DomaineModeleQuestionnaire';

export interface IVersionModeleQuestionnaire {
    id?: number;
    idVersionModeleQuestionnaire?: number;
    version?: string;
    dateCreation?: string;
    dateModification?: string;
    dateDebutUtilisation?: string;
    dateFinUtilisation?: string;
    modele?: IModeleQuestionnaire;
    etat?: IEtatVersionModeleQuestionnaire;
}

export interface IRequeteVersionModeleQuestionnaire extends IVersionModeleQuestionnaire, IPagination { }
export interface IListePagineeVersionModeleQuestionnaire {
    liste?: IVersionModeleQuestionnaire[];
    pagination?: IPagination;
}