package app.domain.commun.expediteur.expediteur;

import app.domain.commun.reference.reference.ReferenceDto;

public record ExpediteurDto(//
        Long id, //
        Long idExpediteur, //
        String libelle, //
        String code, //
        String libelle, //
        String description, //
        String actif, //
        ReferenceDto typeExpediteur, //
        ReferenceDto ville, //
        String telephone, //
        String email, //
        String adresse, //
        String region //
) {

    public static ExpediteurDto asEntity(Expediteur entity) {
        return entity == null ? null
                : new ExpediteurDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        entity.getLibelle(), //
                        entity.getDescription(), //
                        entity.getActif(), //
                        ReferenceDto.asRef(entity.getTypeExpediteur()), //
                        ReferenceDto.asRef(entity.getVille()), //
                        entity.getTelephone(), //
                        entity.getEmail(), //
                        entity.getAdresse(), //
                        entity.getRegion() //
                );
    }

    public static ExpediteurDto asRef(Expediteur entity) {
        return entity == null ? null
                : new ExpediteurDto(//
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
                        null //
                );
    }
}