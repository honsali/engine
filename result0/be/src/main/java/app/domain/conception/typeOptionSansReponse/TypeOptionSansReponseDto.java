package app.domain.conception.typeOptionSansReponse;


public record TypeOptionSansReponseDto(//
        Long id, //
        Long idTypeOptionSansReponse, //
        String code, //
        String libelle //
) {

    public static TypeOptionSansReponseDto asRef(TypeOptionSansReponse entity) {
        return entity == null ? null
                : new TypeOptionSansReponseDto(//
                        entity.getId(), //
                        entity.getId(), //
                        null, //
                        entity.getLibelle() //
                );
    }
}