import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IOption } from 'modele/conception/option/DomaineOption';
import { IReponse } from 'modele/execution/reponse/DomaineReponse';

export interface IOptionReponse {
    id?: number;
    idOptionReponse?: number;
    code?: string;
    option?: IOption;
    reponse?: IReponse;
}

export interface IRequeteOptionReponse extends IOptionReponse, IPagination { }
export interface IListePagineeOptionReponse {
    liste?: IOptionReponse[];
    pagination?: IPagination;
}