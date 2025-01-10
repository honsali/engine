import { IExpediteur } from 'modele/commun/expediteur/expediteur/DomaineExpediteur';
import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface ISinistre {
    id?: number;
    idSinistre?: number;
    numeroSinistre?: string;
    dateOuverture?: string;
    dateSurvenance?: string;
    heureSurvenance?: string;
    dateHeureSurvenance?: string;
    etat?: string;
    montantReserveGlobal?: number;
    segmentDep?: string;
    numeroDeclaration?: string;
    dateDeclaration?: string;
    numeroSinistreIntermediaire?: string;
    dateEffetContrat?: string;
    dateFinContrat?: string;
    validite?: string;
    etatPolice?: IReference;
    numeroPolice?: number;
    categorie?: string;
    libelleProduitTechnique?: string;
    intermediaire?: IExpediteur;
    nomOuRaisonSocialeClient?: string;
    produitCommercial?: IReference;
}

export interface IRequeteSinistre extends ISinistre, IPagination { }
export interface IListePagineeSinistre {
    liste?: ISinistre[];
    pagination?: IPagination;
}