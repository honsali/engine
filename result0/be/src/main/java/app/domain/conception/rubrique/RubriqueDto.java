package app.domain.conception.rubrique;

import app.domain.conception.versionModeleQuestionnaire.VersionModeleQuestionnaireDto;

public record RubriqueDto(//
        Long id, //
        Long idRubrique, //
        String libelle, //
        String titre, //
        String description, //
        Integer position, //
        VersionModeleQuestionnaireDto versionModeleQuestionnaire //
) {

    public static RubriqueDto asEntity(Rubrique entity) {
        return entity == null ? null
                : new RubriqueDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getTitre(), //
                        entity.getTitre(), //
                        entity.getDescription(), //
                        entity.getPosition(), //
                        VersionModeleQuestionnaireDto.asRef(entity.getVersionModeleQuestionnaire()) //
                );
    }

    public static RubriqueDto asRef(Rubrique entity) {
        return entity == null ? null
                : new RubriqueDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getTitre(), //
                        entity.getTitre(), //
                        null, //
                        null, //
                        null //
                );
    }
}