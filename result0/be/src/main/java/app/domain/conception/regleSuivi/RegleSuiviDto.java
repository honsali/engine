package app.domain.conception.regleSuivi;

import app.domain.conception.question.QuestionDto;

public record RegleSuiviDto(//
        Long id, //
        Long idRegleSuivi, //
        String libelle, //
        String code, //
        String operateur, //
        String valeur, //
        QuestionDto question, //
        QuestionDto questionSuivante //
) {

    public static RegleSuiviDto asEntity(RegleSuivi entity) {
        return entity == null ? null
                : new RegleSuiviDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        entity.getOperateur(), //
                        entity.getValeur(), //
                        QuestionDto.asRef(entity.getQuestion()), //
                        QuestionDto.asRef(entity.getQuestionSuivante()) //
                );
    }

    public static RegleSuiviDto asRef(RegleSuivi entity) {
        return entity == null ? null
                : new RegleSuiviDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}