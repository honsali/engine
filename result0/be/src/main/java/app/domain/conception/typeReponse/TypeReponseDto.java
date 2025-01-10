package app.domain.conception.typeReponse;


public record TypeReponseDto(//
        Long id, //
        Long idTypeReponse, //
        String code, //
        String libelle //
) {

    public static TypeReponseDto asRef(TypeReponse entity) {
        return entity == null ? null
                : new TypeReponseDto(//
                        entity.getId(), //
                        entity.getId(), //
                        null, //
                        entity.getLibelle() //
                );
    }
}