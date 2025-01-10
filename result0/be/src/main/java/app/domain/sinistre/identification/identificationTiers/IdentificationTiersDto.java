package app.domain.sinistre.identification.identificationTiers;

import app.domain.commun.reference.reference.ReferenceDto;

public record IdentificationTiersDto(//
        Long id, //
        Long idIdentificationTiers, //
        String libelle, //
        String numeroIdentification, //
        String libelleTiers, //
        ReferenceDto compagnieAdverse //
) {

    public static IdentificationTiersDto asEntity(IdentificationTiers entity) {
        return entity == null ? null
                : new IdentificationTiersDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroIdentification(), //
                        entity.getNumeroIdentification(), //
                        entity.getLibelleTiers(), //
                        ReferenceDto.asRef(entity.getCompagnieAdverse()) //
                );
    }

    public static IdentificationTiersDto asRef(IdentificationTiers entity) {
        return entity == null ? null
                : new IdentificationTiersDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroIdentification(), //
                        entity.getNumeroIdentification(), //
                        null, //
                        null //
                );
    }
}