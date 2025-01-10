package app.domain.conception.versionModeleQuestionnaire;

import java.time.LocalDate;
import app.domain.conception.etatVersionModeleQuestionnaire.EtatVersionModeleQuestionnaireDto;
import app.domain.conception.modeleQuestionnaire.ModeleQuestionnaireDto;

public record VersionModeleQuestionnaireDto(//
        Long id, //
        Long idVersionModeleQuestionnaire, //
        String libelle, //
        String version, //
        LocalDate dateCreation, //
        LocalDate dateModification, //
        LocalDate dateDebutUtilisation, //
        LocalDate dateFinUtilisation, //
        ModeleQuestionnaireDto modele, //
        EtatVersionModeleQuestionnaireDto etat //
) {

    public static VersionModeleQuestionnaireDto asEntity(VersionModeleQuestionnaire entity) {
        return entity == null ? null
                : new VersionModeleQuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getVersion(), //
                        entity.getVersion(), //
                        entity.getDateCreation(), //
                        entity.getDateModification(), //
                        entity.getDateDebutUtilisation(), //
                        entity.getDateFinUtilisation(), //
                        ModeleQuestionnaireDto.asRef(entity.getModele()), //
                        EtatVersionModeleQuestionnaireDto.asRef(entity.getEtat()) //
                );
    }

    public static VersionModeleQuestionnaireDto asRef(VersionModeleQuestionnaire entity) {
        return entity == null ? null
                : new VersionModeleQuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getVersion(), //
                        entity.getVersion(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}