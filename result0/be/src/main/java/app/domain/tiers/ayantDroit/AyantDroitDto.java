package app.domain.tiers.ayantDroit;

import java.time.LocalDate;
import app.domain.commun.reference.reference.ReferenceDto;
import app.domain.sinistre.sousDossier.SousDossierDto;

public record AyantDroitDto(//
        Long id, //
        Long idAyantDroit, //
        String libelle, //
        String idTiers, //
        String nom, //
        String prenom, //
        String nomPrenomArabe, //
        ReferenceDto typeIdentification, //
        String numeroIdentification, //
        ReferenceDto sexe, //
        LocalDate dateNaissance, //
        ReferenceDto situationFamiliale, //
        ReferenceDto lienParente, //
        String adresse, //
        ReferenceDto ville, //
        String tel, //
        String email, //
        Boolean infirme, //
        String idSinistre, //
        String idSousDossier, //
        String idSousDossierVictimeDecede, //
        Boolean obligationAlimentaire, //
        Boolean perteRessource, //
        SousDossierDto sousDossier, //
        SinistreDto sinistre //
) {

    public static AyantDroitDto asEntity(AyantDroit entity) {
        return entity == null ? null
                : new AyantDroitDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        entity.getIdTiers(), //
                        entity.getNom(), //
                        entity.getPrenom(), //
                        entity.getNomPrenomArabe(), //
                        ReferenceDto.asRef(entity.getTypeIdentification()), //
                        entity.getNumeroIdentification(), //
                        ReferenceDto.asRef(entity.getSexe()), //
                        entity.getDateNaissance(), //
                        ReferenceDto.asRef(entity.getSituationFamiliale()), //
                        ReferenceDto.asRef(entity.getLienParente()), //
                        entity.getAdresse(), //
                        ReferenceDto.asRef(entity.getVille()), //
                        entity.getTel(), //
                        entity.getEmail(), //
                        entity.getInfirme(), //
                        entity.getIdSinistre(), //
                        entity.getIdSousDossier(), //
                        entity.getIdSousDossierVictimeDecede(), //
                        entity.getObligationAlimentaire(), //
                        entity.getPerteRessource(), //
                        SousDossierDto.asRef(entity.getSousDossier()), //
                        SinistreDto.asRef(entity.getSinistre()) //
                );
    }

    public static AyantDroitDto asRef(AyantDroit entity) {
        return entity == null ? null
                : new AyantDroitDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        null, //
                        entity.getNom(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}