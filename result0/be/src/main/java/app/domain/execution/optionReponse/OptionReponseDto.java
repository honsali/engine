package app.domain.execution.optionReponse;

import app.domain.conception.option.OptionDto;
import app.domain.execution.reponse.ReponseDto;

public record OptionReponseDto(//
        Long id, //
        Long idOptionReponse, //
        String libelle, //
        String code, //
        OptionDto option, //
        ReponseDto reponse //
) {

    public static OptionReponseDto asEntity(OptionReponse entity) {
        return entity == null ? null
                : new OptionReponseDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        OptionDto.asRef(entity.getOption()), //
                        ReponseDto.asRef(entity.getReponse()) //
                );
    }

    public static OptionReponseDto asRef(OptionReponse entity) {
        return entity == null ? null
                : new OptionReponseDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        null, //
                        null //
                );
    }
}