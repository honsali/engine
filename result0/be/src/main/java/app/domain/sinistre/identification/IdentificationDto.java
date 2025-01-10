package app.domain.sinistre.identification;

import java.time.LocalDate;
import app.domain.commun.reference.reference.ReferenceDto;

public record IdentificationDto(//
        Long id, //
        Long idIdentification, //
        String libelle, //
        String libelleStatut, //
        LocalDate dateOuverture, //
        String numeroPolice, //
        String numeroSerie, //
        LocalDate dateSouscription, //
        String codeIntermediaire, //
        String libelleIntermediaire, //
        String libelleAssure, //
        String numeroIdentificationAssure, //
        String numeroVehicule, //
        String numeroImmatriculation, //
        String referenceDocument, //
        String numeroDocument, //
        ReferenceDto familleDocument, //
        ReferenceDto typeDocument, //
        LocalDate dateCachet, //
        LocalDate dateReception, //
        ReferenceDto prestataire, //
        String numeroSinistre, //
        LocalDate dateSurvenance, //
        String numeroIdentificationTiers, //
        String libelleTiers, //
        ReferenceDto compagnieAdverse, //
        ReferenceDto villeTribunal, //
        ReferenceDto tribunal, //
        String numeroDossier, //
        ReferenceDto numeroChambre, //
        ReferenceDto phase, //
        LocalDate exercice, //
        LocalDate dateAudience, //
        LocalDate dateJugement //
) {

    public static IdentificationDto asEntity(Identification entity) {
        return entity == null ? null
                : new IdentificationDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelleStatut(), //
                        entity.getLibelleStatut(), //
                        entity.getDateOuverture(), //
                        entity.getNumeroPolice(), //
                        entity.getNumeroSerie(), //
                        entity.getDateSouscription(), //
                        entity.getCodeIntermediaire(), //
                        entity.getLibelleIntermediaire(), //
                        entity.getLibelleAssure(), //
                        entity.getNumeroIdentificationAssure(), //
                        entity.getNumeroVehicule(), //
                        entity.getNumeroImmatriculation(), //
                        entity.getReferenceDocument(), //
                        entity.getNumeroDocument(), //
                        ReferenceDto.asRef(entity.getFamilleDocument()), //
                        ReferenceDto.asRef(entity.getTypeDocument()), //
                        entity.getDateCachet(), //
                        entity.getDateReception(), //
                        ReferenceDto.asRef(entity.getPrestataire()), //
                        entity.getNumeroSinistre(), //
                        entity.getDateSurvenance(), //
                        entity.getNumeroIdentificationTiers(), //
                        entity.getLibelleTiers(), //
                        ReferenceDto.asRef(entity.getCompagnieAdverse()), //
                        ReferenceDto.asRef(entity.getVilleTribunal()), //
                        ReferenceDto.asRef(entity.getTribunal()), //
                        entity.getNumeroDossier(), //
                        ReferenceDto.asRef(entity.getNumeroChambre()), //
                        ReferenceDto.asRef(entity.getPhase()), //
                        entity.getExercice(), //
                        entity.getDateAudience(), //
                        entity.getDateJugement() //
                );
    }

    public static IdentificationDto asRef(Identification entity) {
        return entity == null ? null
                : new IdentificationDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelleStatut(), //
                        entity.getLibelleStatut(), //
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