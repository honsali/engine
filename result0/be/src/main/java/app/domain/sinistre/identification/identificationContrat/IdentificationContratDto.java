package app.domain.sinistre.identification.identificationContrat;


public record IdentificationContratDto(//
        Long id, //
        Long idIdentificationContrat, //
        String libelle, //
        String categoriePolice, //
        String numeroPolice, //
        String numeroSerie, //
        String dateSouscription, //
        String codeIntermediaire, //
        String libelleIntermediaire //
) {

    public static IdentificationContratDto asEntity(IdentificationContrat entity) {
        return entity == null ? null
                : new IdentificationContratDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCategoriePolice(), //
                        entity.getCategoriePolice(), //
                        entity.getNumeroPolice(), //
                        entity.getNumeroSerie(), //
                        entity.getDateSouscription(), //
                        entity.getCodeIntermediaire(), //
                        entity.getLibelleIntermediaire() //
                );
    }

    public static IdentificationContratDto asRef(IdentificationContrat entity) {
        return entity == null ? null
                : new IdentificationContratDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCategoriePolice(), //
                        entity.getCategoriePolice(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}