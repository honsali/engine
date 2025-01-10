package app.domain.conception.modeleQuestionnaire;

import java.time.LocalDate;
import app.domain.conception.versionModeleQuestionnaire.VersionModeleQuestionnaireDto;

public record ModeleQuestionnaireDto(//
        Long id, //
        Long idModeleQuestionnaire, //
        String libelle, //
        String nom, //
        String description, //
        LocalDate dateCreation, //
        VersionModeleQuestionnaireDto versionProduction, //
        VersionModeleQuestionnaireDto versionDeveloppement //
) {

    public static ModeleQuestionnaireDto asEntity(ModeleQuestionnaire entity) {
        return entity == null ? null
                : new ModeleQuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        entity.getNom(), //
                        entity.getDescription(), //
                        entity.getDateCreation(), //
                        VersionModeleQuestionnaireDto.asRef(entity.getVersionProduction()), //
                        VersionModeleQuestionnaireDto.asRef(entity.getVersionDeveloppement()) //
                );
    }

    public static ModeleQuestionnaireDto asRef(ModeleQuestionnaire entity) {
        return entity == null ? null
                : new ModeleQuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        entity.getNom(), //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}