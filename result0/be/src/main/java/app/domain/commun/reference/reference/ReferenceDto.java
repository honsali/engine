package app.domain.commun.reference.reference;


public record ReferenceDto(//
        Long id, //
        Long idReference, //
        String libelle, //
        String code, //
        String libelle, //
        String description, //
        String taux, //
        String nomOuRaisonSociale //
) {

    public static ReferenceDto asEntity(Reference entity) {
        return entity == null ? null
                : new ReferenceDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        entity.getLibelle(), //
                        entity.getDescription(), //
                        entity.getTaux(), //
                        entity.getNomOuRaisonSociale() //
                );
    }

    public static ReferenceDto asRef(Reference entity) {
        return entity == null ? null
                : new ReferenceDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCode(), //
                        entity.getCode(), //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}