package app.domain.conception.question;

import app.domain.conception.rubrique.RubriqueDto;
import app.domain.conception.typeReponse.TypeReponseDto;

public record QuestionDto(//
        Long id, //
        Long idQuestion, //
        String libelle, //
        String code, //
        String titre, //
        String description, //
        Integer position, //
        Boolean autreOption, //
        String autreLibelle, //
        Double min, //
        Double max, //
        Integer precision, //
        TypeReponseDto typeReponse, //
        RubriqueDto rubrique //
) {

    public static QuestionDto asEntity(Question entity) {
        return entity == null ? null
                : new QuestionDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getTitre(), //
                        entity.getCode(), //
                        entity.getTitre(), //
                        entity.getDescription(), //
                        entity.getPosition(), //
                        entity.getAutreOption(), //
                        entity.getAutreLibelle(), //
                        entity.getMin(), //
                        entity.getMax(), //
                        entity.getPrecision(), //
                        TypeReponseDto.asRef(entity.getTypeReponse()), //
                        RubriqueDto.asRef(entity.getRubrique()) //
                );
    }

    public static QuestionDto asRef(Question entity) {
        return entity == null ? null
                : new QuestionDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getTitre(), //
                        null, //
                        entity.getTitre(), //
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