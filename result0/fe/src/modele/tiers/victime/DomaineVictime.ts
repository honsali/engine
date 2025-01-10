import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';
import { ISinistre } from 'modele/sinistre/sinistre/DomaineSinistre';
import { IEntiteAdverse } from 'modele/tiers/entiteAdverse/DomaineEntiteAdverse';

export interface IVictime {
    id?: number;
    idVictime?: number;
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
    entiteAdverse?: IEntiteAdverse;
    sinistre?: ISinistre;
}

export interface IRequeteVictime extends IVictime, IPagination { }
export interface IListePagineeVictime {
    liste?: IVictime[];
    pagination?: IPagination;
}