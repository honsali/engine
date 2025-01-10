package app.domain.sinistre.identification.identificationDocument;

import app.domain.commun.expediteur.expediteur.ExpediteurDto;
import app.domain.commun.reference.reference.ReferenceDto;

public record IdentificationDocumentDto(//
        Long id, //
        Long idIdentificationDocument, //
        String libelle, //
        String referenceDocument, //
        String numeroDocument, //
        ReferenceDto familleDocument, //
        ReferenceDto typeDocument, //
        String dateCachet, //
        String dateReception, //
        ReferenceDto typeExpediteur, //
        ExpediteurDto prestataire //
) {

    public static IdentificationDocumentDto asEntity(IdentificationDocument entity) {
        return entity == null ? null
                : new IdentificationDocumentDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getReferenceDocument(), //
                        entity.getReferenceDocument(), //
                        entity.getNumeroDocument(), //
                        ReferenceDto.asRef(entity.getFamilleDocument()), //
                        ReferenceDto.asRef(entity.getTypeDocument()), //
                        entity.getDateCachet(), //
                        entity.getDateReception(), //
                        ReferenceDto.asRef(entity.getTypeExpediteur()), //
                        ExpediteurDto.asRef(entity.getPrestataire()) //
                );
    }

    public static IdentificationDocumentDto asRef(IdentificationDocument entity) {
        return entity == null ? null
                : new IdentificationDocumentDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getReferenceDocument(), //
                        entity.getReferenceDocument(), //
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