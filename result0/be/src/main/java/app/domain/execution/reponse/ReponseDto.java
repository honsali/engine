package app.domain.execution.reponse;

import java.time.LocalDate;
import app.domain.conception.question.QuestionDto;
import app.domain.conception.typeOptionSansReponse.TypeOptionSansReponseDto;
import app.domain.execution.questionnaire.QuestionnaireDto;
import app.domain.execution.sessionInterview.SessionInterviewDto;

public record ReponseDto(//
        Long id, //
        Long idReponse, //
        String libelle, //
        String code, //
        Integer position, //
        String valeurTexte, //
        Double valeurNumerique, //
        Double valeurNumeriqueFin, //
        LocalDate valeurDate, //
        LocalDate valeurDateFin, //
        Boolean valeurBooleen, //
        TypeOptionSansReponseDto typeOptionSansReponse, //
        Boolean autreOption, //
        Boolean option1, //
        Boolean option2, //
        Boolean option3, //
        Boolean option4, //
        Boolean option5, //
        Boolean option6, //
        Boolean option7, //
        Boolean option8, //
        Boolean option9, //
        Boolean option10, //
        QuestionDto question, //
        SessionInterviewDto sessionInterview, //
        QuestionnaireDto questionnaire //
) {

    public static ReponseDto asEntity(Reponse entity) {
        return entity == null ? null
                : new ReponseDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        entity.getPosition(), //
                        entity.getValeurTexte(), //
                        entity.getValeurNumerique(), //
                        entity.getValeurNumeriqueFin(), //
                        entity.getValeurDate(), //
                        entity.getValeurDateFin(), //
                        entity.getValeurBooleen(), //
                        TypeOptionSansReponseDto.asRef(entity.getTypeOptionSansReponse()), //
                        entity.getAutreOption(), //
                        entity.getOption1(), //
                        entity.getOption2(), //
                        entity.getOption3(), //
                        entity.getOption4(), //
                        entity.getOption5(), //
                        entity.getOption6(), //
                        entity.getOption7(), //
                        entity.getOption8(), //
                        entity.getOption9(), //
                        entity.getOption10(), //
                        QuestionDto.asRef(entity.getQuestion()), //
                        SessionInterviewDto.asRef(entity.getSessionInterview()), //
                        QuestionnaireDto.asRef(entity.getQuestionnaire()) //
                );
    }

    public static ReponseDto asRef(Reponse entity) {
        return entity == null ? null
                : new ReponseDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
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