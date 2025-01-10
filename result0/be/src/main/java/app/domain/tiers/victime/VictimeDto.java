package app.domain.tiers.victime;

import java.time.LocalDate;
import app.domain.commun.reference.reference.ReferenceDto;
import app.domain.sinistre.sinistre.SinistreDto;
import app.domain.tiers.entiteAdverse.EntiteAdverseDto;

public record VictimeDto(//
        Long id, //
        Long idVictime, //
        String libelle, //
        String nom, //
        String prenom, //
        LocalDate dateNaissance, //
        String nomPrenomArabe, //
        ReferenceDto sexe, //
        ReferenceDto typeIdentification, //
        String numeroIdentification, //
        ReferenceDto ville, //
        ReferenceDto qualiteVictime, //
        Boolean mineur, //
        Boolean ayantDroit, //
        String idClient, //
        Integer idTiersAssocie, //
        ReferenceDto situationFamiliale, //
        String tel, //
        String email, //
        String categorie, //
        String adresse, //
        String numeroPoliceExterne, //
        ReferenceDto profession, //
        ReferenceDto assureur, //
        String idSousDossierVictime, //
        ReferenceDto lienParente, //
        Boolean sansMateriel, //
        EntiteAdverseDto entiteAdverse, //
        SinistreDto sinistre //
) {

    public static VictimeDto asEntity(Victime entity) {
        return entity == null ? null
                : new VictimeDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        entity.getNom(), //
                        entity.getPrenom(), //
                        entity.getDateNaissance(), //
                        entity.getNomPrenomArabe(), //
                        ReferenceDto.asRef(entity.getSexe()), //
                        ReferenceDto.asRef(entity.getTypeIdentification()), //
                        entity.getNumeroIdentification(), //
                        ReferenceDto.asRef(entity.getVille()), //
                        ReferenceDto.asRef(entity.getQualiteVictime()), //
                        entity.getMineur(), //
                        entity.getAyantDroit(), //
                        entity.getIdClient(), //
                        entity.getIdTiersAssocie(), //
                        ReferenceDto.asRef(entity.getSituationFamiliale()), //
                        entity.getTel(), //
                        entity.getEmail(), //
                        entity.getCategorie(), //
                        entity.getAdresse(), //
                        entity.getNumeroPoliceExterne(), //
                        ReferenceDto.asRef(entity.getProfession()), //
                        ReferenceDto.asRef(entity.getAssureur()), //
                        entity.getIdSousDossierVictime(), //
                        ReferenceDto.asRef(entity.getLienParente()), //
                        entity.getSansMateriel(), //
                        EntiteAdverseDto.asRef(entity.getEntiteAdverse()), //
                        SinistreDto.asRef(entity.getSinistre()) //
                );
    }

    public static VictimeDto asRef(Victime entity) {
        return entity == null ? null
                : new VictimeDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
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
                        null, //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}