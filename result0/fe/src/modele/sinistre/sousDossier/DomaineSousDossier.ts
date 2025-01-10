import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { ITiers } from 'modele/sinistre/tiers/tiers/DomaineTiers';

export interface ISousDossier {
    id?: number;
    idSousDossier?: number;
    dateSurvenance?: string;
    heureSurvenance?: string;
    etat?: string;
    dateOuverture?: string;
    dateDeclaration?: string;
    estMaster?: boolean;
    idUtilisateur?: string;
    idSinistre?: string;
    profession?: string;
    revenu?: number;
    typeRevenu?: string;
    decede?: boolean;
    degatMat?: boolean;
    degatCorpo?: boolean;
    tiers?: ITiers;
}

export interface IRequeteSousDossier extends ISousDossier, IPagination { }
export interface IListePagineeSousDossier {
    liste?: ISousDossier[];
    pagination?: IPagination;
}