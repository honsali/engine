import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';
import { ISinistre } from 'modele/sinistre/sinistre/DomaineSinistre';

export interface IEntiteAdverse {
    id?: number;
    idEntiteAdverse?: number;
    nom?: string;
    prenom?: string;
    typeIdentification?: IReference;
    numeroIdentification?: string;
    adresse?: string;
    assureur?: IReference;
    codeIntermediaire?: string;
    nomIntermediaire?: string;
    immatriculationVehicule?: string;
    numeroPolice?: string;
    dateDebutContrat?: string;
    dateFinContrat?: string;
    degatsMateriel?: boolean;
    degatsCorporel?: boolean;
    personnePhysique?: boolean;
    personneMorale?: boolean;
    dateNaissance?: string;
    sexe?: IReference;
    ville?: IReference;
    qualiteVictime?: IReference;
    situationFamiliale?: IReference;
    tel?: string;
    email?: string;
    profession?: IReference;
    sinistre?: ISinistre;
}

export interface IRequeteEntiteAdverse extends IEntiteAdverse, IPagination { }
export interface IListePagineeEntiteAdverse {
    liste?: IEntiteAdverse[];
    pagination?: IPagination;
}