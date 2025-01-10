package app.domain.commun.utilisateur.utilisateur;


public record UtilisateurDto(//
        Long id, //
        Long idUtilisateur, //
        String libelle, //
        String login, //
        Boolean etat //
) {

    public static UtilisateurDto asEntity(Utilisateur entity) {
        return entity == null ? null
                : new UtilisateurDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLogin(), //
                        entity.getLogin(), //
                        entity.getEtat() //
                );
    }

    public static UtilisateurDto asRef(Utilisateur entity) {
        return entity == null ? null
                : new UtilisateurDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLogin(), //
                        entity.getLogin(), //
                        null //
                );
    }
}