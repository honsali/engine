import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';
import { ISinistre } from 'modele/sinistre/sinistre/DomaineSinistre';
import { ISousDossier } from 'modele/sinistre/sousDossier/DomaineSousDossier';

export interface IAyantDroit {
    id?: number;
    idAyantDroit?: number;
    idTiers?: string;
    nom?: string;
    prenom?: string;
    nomPrenomArabe?: string;
    typeIdentification?: IReference;
    numeroIdentification?: string;
    sexe?: IReference;
    dateNaissance?: string;
    situationFamiliale?: IReference;
    lienParente?: IReference;
    adresse?: string;
    ville?: IReference;
    tel?: string;
    email?: string;
    infirme?: boolean;
    idSinistre?: string;
    idSousDossier?: string;
    idSousDossierVictimeDecede?: string;
    obligationAlimentaire?: boolean;
    perteRessource?: boolean;
    sousDossier?: ISousDossier;
}

export interface IRequeteAyantDroit extends IAyantDroit, IPagination { }
export interface IListePagineeAyantDroit {
    liste?: IAyantDroit[];
    pagination?: IPagination;
}