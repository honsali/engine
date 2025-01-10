package app.domain.execution.sessionInterview;

import java.time.LocalDate;
import java.time.LocalTime;
import app.domain.execution.questionnaire.QuestionnaireDto;

public record SessionInterviewDto(//
        Long id, //
        Long idSessionInterview, //
        String libelle, //
        String code, //
        LocalDate dateDebut, //
        LocalDate dateFin, //
        LocalTime heureDebut, //
        LocalTime heureFin, //
        QuestionnaireDto questionnaire //
) {

    public static SessionInterviewDto asEntity(SessionInterview entity) {
        return entity == null ? null
                : new SessionInterviewDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        entity.getDateDebut(), //
                        entity.getDateFin(), //
                        entity.getHeureDebut(), //
                        entity.getHeureFin(), //
                        QuestionnaireDto.asRef(entity.getQuestionnaire()) //
                );
    }

    public static SessionInterviewDto asRef(SessionInterview entity) {
        return entity == null ? null
                : new SessionInterviewDto(//
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