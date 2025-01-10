import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IRubrique } from 'modele/conception/rubrique/DomaineRubrique';
import { ITypeReponse } from 'modele/conception/typeReponse/DomaineTypeReponse';

export interface IQuestion {
    id?: number;
    idQuestion?: number;
    code?: string;
    titre?: string;
    description?: string;
    position?: number;
    autreOption?: boolean;
    autreLibelle?: string;
    min?: number;
    max?: number;
    precision?: number;
    typeReponse?: ITypeReponse;
    rubrique?: IRubrique;
}

export interface IRequeteQuestion extends IQuestion, IPagination { }
export interface IListePagineeQuestion {
    liste?: IQuestion[];
    pagination?: IPagination;
}