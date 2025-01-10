package app.domain.sinistre.identification.identificationTribunal;

import app.domain.commun.reference.reference.ReferenceDto;

public record IdentificationTribunalDto(//
        Long id, //
        Long idIdentificationTribunal, //
        String libelle, //
        ReferenceDto villeTribunal, //
        ReferenceDto tribunal, //
        String numeroDossier, //
        ReferenceDto numeroChambre, //
        ReferenceDto phase, //
        String exercice, //
        String dateAudience, //
        String dateJugement //
) {

    public static IdentificationTribunalDto asEntity(IdentificationTribunal entity) {
        return entity == null ? null
                : new IdentificationTribunalDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroDossier(), //
                        ReferenceDto.asRef(entity.getVilleTribunal()), //
                        ReferenceDto.asRef(entity.getTribunal()), //
                        entity.getNumeroDossier(), //
                        ReferenceDto.asRef(entity.getNumeroChambre()), //
                        ReferenceDto.asRef(entity.getPhase()), //
                        entity.getExercice(), //
                        entity.getDateAudience(), //
                        entity.getDateJugement() //
                );
    }

    public static IdentificationTribunalDto asRef(IdentificationTribunal entity) {
        return entity == null ? null
                : new IdentificationTribunalDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroDossier(), //
                        null, //
                        null, //
                        entity.getNumeroDossier(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}