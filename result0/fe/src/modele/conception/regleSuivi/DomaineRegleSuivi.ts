import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';

export interface IRegleSuivi {
    id?: number;
    idRegleSuivi?: number;
    code?: string;
    operateur?: string;
    valeur?: string;
    question?: IQuestion;
    questionSuivante?: IQuestion;
}

export interface IRequeteRegleSuivi extends IRegleSuivi, IPagination { }
export interface IListePagineeRegleSuivi {
    liste?: IRegleSuivi[];
    pagination?: IPagination;
}