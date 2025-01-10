package app.domain.commun.historiqueEtat.etat;


public record EtatDto(//
        Long id, //
        Long idEtat, //
        String libelle //
) {

    public static EtatDto asRef(Etat entity) {
        return entity == null ? null
                : new EtatDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelle() //
                );
    }
}