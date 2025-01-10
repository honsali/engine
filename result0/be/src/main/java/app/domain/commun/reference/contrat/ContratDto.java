package app.domain.commun.reference.contrat;


public record ContratDto(//
        Long id, //
        Long idContrat, //
        String libelle, //
        String categoriePolice, //
        String numeroPolice //
) {

    public static ContratDto asEntity(Contrat entity) {
        return entity == null ? null
                : new ContratDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCategoriePolice(), //
                        entity.getCategoriePolice(), //
                        entity.getNumeroPolice() //
                );
    }

    public static ContratDto asRef(Contrat entity) {
        return entity == null ? null
                : new ContratDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCategoriePolice(), //
                        entity.getCategoriePolice(), //
                        null //
                );
    }
}