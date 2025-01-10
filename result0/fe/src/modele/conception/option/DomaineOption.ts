import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';

export interface IOption {
    id?: number;
    idOption?: number;
    code?: string;
    valeur?: string;
    libelle?: string;
    position?: number;
    question?: IQuestion;
}

export interface IRequeteOption extends IOption, IPagination { }
export interface IListePagineeOption {
    liste?: IOption[];
    pagination?: IPagination;
}