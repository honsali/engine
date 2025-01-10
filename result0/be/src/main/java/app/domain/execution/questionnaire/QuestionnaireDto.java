package app.domain.execution.questionnaire;

import java.time.LocalDate;
import app.domain.conception.versionModeleQuestionnaire.VersionModeleQuestionnaireDto;
import app.domain.execution.etatQuestionnaire.EtatQuestionnaireDto;

public record QuestionnaireDto(//
        Long id, //
        Long idQuestionnaire, //
        String libelle, //
        String code, //
        LocalDate dateCreation, //
        Integer nombreQuestion, //
        Integer derniereQuestion, //
        VersionModeleQuestionnaireDto versionModeleQuestionnaire, //
        EtatQuestionnaireDto etat //
) {

    public static QuestionnaireDto asEntity(Questionnaire entity) {
        return entity == null ? null
                : new QuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        entity.getDateCreation(), //
                        entity.getNombreQuestion(), //
                        entity.getDerniereQuestion(), //
                        VersionModeleQuestionnaireDto.asRef(entity.getVersionModeleQuestionnaire()), //
                        EtatQuestionnaireDto.asRef(entity.getEtat()) //
                );
    }

    public static QuestionnaireDto asRef(Questionnaire entity) {
        return entity == null ? null
                : new QuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}