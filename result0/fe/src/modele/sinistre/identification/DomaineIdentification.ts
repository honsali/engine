import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface IIdentification {
    id?: number;
    idIdentification?: number;
    libelleStatut?: string;
    dateOuverture?: string;
    numeroPolice?: string;
    numeroSerie?: string;
    dateSouscription?: string;
    codeIntermediaire?: string;
    libelleIntermediaire?: string;
    libelleAssure?: string;
    numeroIdentificationAssure?: string;
    numeroVehicule?: string;
    numeroImmatriculation?: string;
    referenceDocument?: string;
    numeroDocument?: string;
    familleDocument?: IReference;
    typeDocument?: IReference;
    dateCachet?: string;
    dateReception?: string;
    prestataire?: IReference;
    numeroSinistre?: string;
    dateSurvenance?: string;
    numeroIdentificationTiers?: string;
    libelleTiers?: string;
    compagnieAdverse?: IReference;
    villeTribunal?: IReference;
    tribunal?: IReference;
    numeroDossier?: string;
    numeroChambre?: IReference;
    phase?: IReference;
    exercice?: string;
    dateAudience?: string;
    dateJugement?: string;
}

export interface IRequeteIdentification extends IIdentification, IPagination { }
export interface IListePagineeIdentification {
    liste?: IIdentification[];
    pagination?: IPagination;
}