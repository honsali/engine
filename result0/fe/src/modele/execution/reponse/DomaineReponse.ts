import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IQuestion } from 'modele/conception/question/DomaineQuestion';
import { ITypeOptionSansReponse } from 'modele/conception/typeOptionSansReponse/DomaineTypeOptionSansReponse';
import { IQuestionnaire } from 'modele/execution/questionnaire/DomaineQuestionnaire';
import { ISessionInterview } from 'modele/execution/sessionInterview/DomaineSessionInterview';

export interface IReponse {
    id?: number;
    idReponse?: number;
    code?: string;
    position?: number;
    valeurTexte?: string;
    valeurNumerique?: number;
    valeurNumeriqueFin?: number;
    valeurDate?: string;
    valeurDateFin?: string;
    valeurBooleen?: boolean;
    typeOptionSansReponse?: ITypeOptionSansReponse;
    autreOption?: boolean;
    option1?: boolean;
    option2?: boolean;
    option3?: boolean;
    option4?: boolean;
    option5?: boolean;
    option6?: boolean;
    option7?: boolean;
    option8?: boolean;
    option9?: boolean;
    option10?: boolean;
    question?: IQuestion;
    sessionInterview?: ISessionInterview;
    questionnaire?: IQuestionnaire;
}

export interface IRequeteReponse extends IReponse, IPagination { }
export interface IListePagineeReponse {
    liste?: IReponse[];
    pagination?: IPagination;
}