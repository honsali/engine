import { IPagination } from 'modele/commun/pagination/DomainePagination';
import { IReference } from 'modele/commun/reference/reference/DomaineReference';
import { ISinistre } from 'modele/sinistre/sinistre/DomaineSinistre';

export interface ITache {
    id?: number;
    idTache?: number;
    idProcess?: string;
    nom?: string;
    categorie?: string;
    code?: string;
    codeTenant?: string;
    codeDocument?: string;
    idDocument?: string;
    idSousDossier?: string;
    nomTiers?: string;
    prenomTiers?: string;
    numeroDocument?: string;
    libelleFamille?: string;
    codeFamille?: string;
    codeTypeDocument?: string;
    typeDocumentJugement?: string;
    libelleTypeDocument?: string;
    canal?: string;
    dateCreation?: string;
    dateReception?: string;
    dateCachet?: string;
    dateNumerisation?: string;
    statutDocument?: string;
    utilisateurSource?: string;
    utilisateurDestinataire?: string;
    referenceDocument?: string;
    instanceGed?: string;
    catSinistre?: string;
    exeSinistre?: string;
    numSinistre?: string;
    idSinistre?: string;
    idOuverture?: string;
    idJugement?: string;
    numeroSinistre?: string;
    statut?: string;
    delaiRestant?: string;
    priorite?: string;
    idObject?: string;
    role?: string;
    familleCible?: IReference;
    destinataire?: string;
    action?: string;
    tiers?: string;
    commentaire?: string;
    numeroOrdre?: string;
    chambre?: string;
    exercice?: string;
    dateJugement?: string;
    codeMailType?: string;
    idAffaire?: string;
    numeroAffaire?: string;
    idMission?: string;
    numeroDossierJudiciaire?: string;
    equipe?: string;
    idOffre?: string;
    listeBeneficiaireReglement?: string;
    sinistre?: ISinistre;
}

export interface IRequeteTache extends ITache, IPagination { }
export interface IListePagineeTache {
    liste?: ITache[];
    pagination?: IPagination;
}