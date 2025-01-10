package app.domain.commun.tache.tache;

import java.time.LocalDate;
import app.domain.commun.reference.reference.ReferenceDto;
import app.domain.sinistre.sinistre.SinistreDto;

public record TacheDto(//
        Long id, //
        Long idTache, //
        String libelle, //
        String idProcess, //
        String nom, //
        String categorie, //
        String code, //
        String codeTenant, //
        String codeDocument, //
        String idDocument, //
        String idSousDossier, //
        String nomTiers, //
        String prenomTiers, //
        String numeroDocument, //
        String libelleFamille, //
        String codeFamille, //
        String codeTypeDocument, //
        String typeDocumentJugement, //
        String libelleTypeDocument, //
        String canal, //
        LocalDate dateCreation, //
        String dateReception, //
        String dateCachet, //
        String dateNumerisation, //
        String statutDocument, //
        String utilisateurSource, //
        String utilisateurDestinataire, //
        String referenceDocument, //
        String instanceGed, //
        String catSinistre, //
        String exeSinistre, //
        String numSinistre, //
        String idSinistre, //
        String idOuverture, //
        String idJugement, //
        String numeroSinistre, //
        String statut, //
        String delaiRestant, //
        String priorite, //
        String idObject, //
        String role, //
        ReferenceDto familleCible, //
        String destinataire, //
        String action, //
        String tiers, //
        String commentaire, //
        String numeroOrdre, //
        String chambre, //
        String exercice, //
        String dateJugement, //
        String codeMailType, //
        String idAffaire, //
        String numeroAffaire, //
        String idMission, //
        String numeroDossierJudiciaire, //
        String equipe, //
        String idOffre, //
        String listeBeneficiaireReglement, //
        SinistreDto sinistre //
) {

    public static TacheDto asEntity(Tache entity) {
        return entity == null ? null
                : new TacheDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        entity.getIdProcess(), //
                        entity.getNom(), //
                        entity.getCategorie(), //
                        entity.getCode(), //
                        entity.getCodeTenant(), //
                        entity.getCodeDocument(), //
                        entity.getIdDocument(), //
                        entity.getIdSousDossier(), //
                        entity.getNomTiers(), //
                        entity.getPrenomTiers(), //
                        entity.getNumeroDocument(), //
                        entity.getLibelleFamille(), //
                        entity.getCodeFamille(), //
                        entity.getCodeTypeDocument(), //
                        entity.getTypeDocumentJugement(), //
                        entity.getLibelleTypeDocument(), //
                        entity.getCanal(), //
                        entity.getDateCreation(), //
                        entity.getDateReception(), //
                        entity.getDateCachet(), //
                        entity.getDateNumerisation(), //
                        entity.getStatutDocument(), //
                        entity.getUtilisateurSource(), //
                        entity.getUtilisateurDestinataire(), //
                        entity.getReferenceDocument(), //
                        entity.getInstanceGed(), //
                        entity.getCatSinistre(), //
                        entity.getExeSinistre(), //
                        entity.getNumSinistre(), //
                        entity.getIdSinistre(), //
                        entity.getIdOuverture(), //
                        entity.getIdJugement(), //
                        entity.getNumeroSinistre(), //
                        entity.getStatut(), //
                        entity.getDelaiRestant(), //
                        entity.getPriorite(), //
                        entity.getIdObject(), //
                        entity.getRole(), //
                        ReferenceDto.asRef(entity.getFamilleCible()), //
                        entity.getDestinataire(), //
                        entity.getAction(), //
                        entity.getTiers(), //
                        entity.getCommentaire(), //
                        entity.getNumeroOrdre(), //
                        entity.getChambre(), //
                        entity.getExercice(), //
                        entity.getDateJugement(), //
                        entity.getCodeMailType(), //
                        entity.getIdAffaire(), //
                        entity.getNumeroAffaire(), //
                        entity.getIdMission(), //
                        entity.getNumeroDossierJudiciaire(), //
                        entity.getEquipe(), //
                        entity.getIdOffre(), //
                        entity.getListeBeneficiaireReglement(), //
                        SinistreDto.asRef(entity.getSinistre()) //
                );
    }

    public static TacheDto asRef(Tache entity) {
        return entity == null ? null
                : new TacheDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        null, //
                        entity.getNom(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}