package app.domain.conception.transition;

import app.domain.conception.option.OptionDto;
import app.domain.conception.question.QuestionDto;

public record TransitionDto(//
        Long id, //
        Long idTransition, //
        String libelle, //
        String raison, //
        OptionDto optionChoisie, //
        QuestionDto questionCible, //
        QuestionDto question //
) {

    public static TransitionDto asEntity(Transition entity) {
        return entity == null ? null
                : new TransitionDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getRaison(), //
                        entity.getRaison(), //
                        OptionDto.asRef(entity.getOptionChoisie()), //
                        QuestionDto.asRef(entity.getQuestionCible()), //
                        QuestionDto.asRef(entity.getQuestion()) //
                );
    }

    public static TransitionDto asRef(Transition entity) {
        return entity == null ? null
                : new TransitionDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getRaison(), //
                        entity.getRaison(), //
                        null, //
                        null, //
                        null //
                );
    }
}