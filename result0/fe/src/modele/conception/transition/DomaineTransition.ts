import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IOption } from 'modele/conception/option/DomaineOption';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';

export interface ITransition {
    id?: number;
    idTransition?: number;
    raison?: string;
    optionChoisie?: IOption;
    questionCible?: IQuestion;
    question?: IQuestion;
}

export interface IRequeteTransition extends ITransition, IPagination { }
export interface IListePagineeTransition {
    liste?: ITransition[];
    pagination?: IPagination;
}