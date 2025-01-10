package app.domain.commun.reference.circonstance;


public record CirconstanceDto(//
        Long id, //
        Long idCirconstance, //
        String libelle, //
        String partResponsabiliteCode, //
        String partResponsabiliteLibelle //
) {

    public static CirconstanceDto asEntity(Circonstance entity) {
        return entity == null ? null
                : new CirconstanceDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getPartResponsabiliteCode(), //
                        entity.getPartResponsabiliteCode(), //
                        entity.getPartResponsabiliteLibelle() //
                );
    }

    public static CirconstanceDto asRef(Circonstance entity) {
        return entity == null ? null
                : new CirconstanceDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getPartResponsabiliteCode(), //
                        entity.getPartResponsabiliteCode(), //
                        null //
                );
    }
}