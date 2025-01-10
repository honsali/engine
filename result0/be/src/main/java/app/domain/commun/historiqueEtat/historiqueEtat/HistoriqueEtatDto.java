package app.domain.commun.historiqueEtat.historiqueEtat;

import java.time.LocalDate;
import app.domain.commun.historiqueEtat.etat.EtatDto;
import app.domain.commun.historiqueEtat.motif.MotifDto;

public record HistoriqueEtatDto(//
        Long id, //
        Long idHistoriqueEtat, //
        String libelle, //
        LocalDate dateEffet, //
        String commentaire, //
        EtatDto etat, //
        MotifDto motif, //
        String gestionnaire, //
        String nomEntite, //
        String idEntite //
) {

    public static HistoriqueEtatDto asEntity(HistoriqueEtat entity) {
        return entity == null ? null
                : new HistoriqueEtatDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNomEntite(), //
                        entity.getDateEffet(), //
                        entity.getCommentaire(), //
                        EtatDto.asRef(entity.getEtat()), //
                        MotifDto.asRef(entity.getMotif()), //
                        entity.getGestionnaire(), //
                        entity.getNomEntite(), //
                        entity.getIdEntite() //
                );
    }

    public static HistoriqueEtatDto asRef(HistoriqueEtat entity) {
        return entity == null ? null
                : new HistoriqueEtatDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNomEntite(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        entity.getNomEntite(), //
                        null //
                );
    }
}