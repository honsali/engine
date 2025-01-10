package app.domain.commun.documentGed.documentGed;


public record DocumentGedDto(//
        Long id, //
        Long idDocumentGed, //
        String libelle, //
        Integer idDocument, //
        String codeDocument, //
        String referenceDocument, //
        String codeFamille, //
        String mimeType, //
        Integer numeroPage, //
        String instanceGed, //
        Integer nombreTotalPage, //
        String pageURL //
) {

    public static DocumentGedDto asEntity(DocumentGed entity) {
        return entity == null ? null
                : new DocumentGedDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCodeDocument(), //
                        entity.getIdDocument(), //
                        entity.getCodeDocument(), //
                        entity.getReferenceDocument(), //
                        entity.getCodeFamille(), //
                        entity.getMimeType(), //
                        entity.getNumeroPage(), //
                        entity.getInstanceGed(), //
                        entity.getNombreTotalPage(), //
                        entity.getPageURL() //
                );
    }

    public static DocumentGedDto asRef(DocumentGed entity) {
        return entity == null ? null
                : new DocumentGedDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCodeDocument(), //
                        null, //
                        entity.getCodeDocument(), //
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