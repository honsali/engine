package app.domain.commun.tache.nombreTacheParCategorie;


public record NombreTacheParCategorieDto(//
        Long id, //
        Long idNombreTacheParCategorie, //
        String libelle, //
        String categorieTache, //
        Integer nombre //
) {

    public static NombreTacheParCategorieDto asEntity(NombreTacheParCategorie entity) {
        return entity == null ? null
                : new NombreTacheParCategorieDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCategorieTache(), //
                        entity.getCategorieTache(), //
                        entity.getNombre() //
                );
    }

    public static NombreTacheParCategorieDto asRef(NombreTacheParCategorie entity) {
        return entity == null ? null
                : new NombreTacheParCategorieDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getCategorieTache(), //
                        entity.getCategorieTache(), //
                        null //
                );
    }
}