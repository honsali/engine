package app.domain.commun.historiqueEtat.motif;


public record MotifDto(//
        Long id, //
        Long idMotif, //
        String libelle //
) {

    public static MotifDto asRef(Motif entity) {
        return entity == null ? null
                : new MotifDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelle() //
                );
    }
}