package app.domain.conception.option;

import app.domain.conception.question.QuestionDto;

public record OptionDto(//
        Long id, //
        Long idOption, //
        String code, //
        String valeur, //
        String libelle, //
        Integer position, //
        QuestionDto question //
) {

    public static OptionDto asEntity(Option entity) {
        return entity == null ? null
                : new OptionDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getValeur(), //
                        entity.getLibelle(), //
                        entity.getPosition(), //
                        QuestionDto.asRef(entity.getQuestion()) //
                );
    }

    public static OptionDto asRef(Option entity) {
        return entity == null ? null
                : new OptionDto(//
                        entity.getId(), //
                        entity.getId(), //
                        null, //
                        null, //
                        entity.getLibelle(), //
                        null, //
                        null //
                );
    }
}