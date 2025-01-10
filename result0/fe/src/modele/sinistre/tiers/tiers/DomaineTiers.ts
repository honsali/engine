import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';

export interface ITiers {
    id?: number;
    idTiers?: number;
    nom?: string;
    prenom?: string;
    dateNaissance?: string;
    nomPrenomArabe?: string;
    sexe?: IReference;
    typeIdentification?: IReference;
    numeroIdentification?: string;
    ville?: IReference;
    qualiteVictime?: IReference;
    mineur?: boolean;
    ayantDroit?: boolean;
    idClient?: string;
    idTiersAssocie?: number;
    situationFamiliale?: IReference;
    tel?: string;
    email?: string;
    categorie?: string;
    adresse?: string;
    numeroPoliceExterne?: string;
    profession?: IReference;
    assureur?: IReference;
    idSousDossierVictime?: string;
    lienParente?: IReference;
    sansMateriel?: boolean;
    entiteAdverse?: IReference;
}

export interface IRequeteTiers extends ITiers, IPagination { }
export interface IListePagineeTiers {
    liste?: ITiers[];
    pagination?: IPagination;
}