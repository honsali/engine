package app.domain.commun.email.email;

import app.domain.commun.documentGed.documentGed.DocumentGedDto;

public record EmailDto(//
        Long id, //
        Long idEmail, //
        String libelle, //
        String adresseMail, //
        String cc, //
        String cci, //
        String expediteurCode, //
        String objet, //
        String message, //
        DocumentGedDto documentGed, //
        String cible //
) {

    public static EmailDto asEntity(Email entity) {
        return entity == null ? null
                : new EmailDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getAdresseMail(), //
                        entity.getAdresseMail(), //
                        entity.getCc(), //
                        entity.getCci(), //
                        entity.getExpediteurCode(), //
                        entity.getObjet(), //
                        entity.getMessage(), //
                        DocumentGedDto.asRef(entity.getDocumentGed()), //
                        entity.getCible() //
                );
    }

    public static EmailDto asRef(Email entity) {
        return entity == null ? null
                : new EmailDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getAdresseMail(), //
                        entity.getAdresseMail(), //
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