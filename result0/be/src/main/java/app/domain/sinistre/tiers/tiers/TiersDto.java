package app.domain.sinistre.tiers.tiers;

import app.domain.commun.reference.reference.ReferenceDto;

public record TiersDto(//
        Long id, //
        Long idTiers, //
        String libelle, //
        String nom, //
        String prenom, //
        String dateNaissance, //
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
        ReferenceDto entiteAdverse //
) {

    public static TiersDto asEntity(Tiers entity) {
        return entity == null ? null
                : new TiersDto(//
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
                        ReferenceDto.asRef(entity.getEntiteAdverse()) //
                );
    }

    public static TiersDto asRef(Tiers entity) {
        return entity == null ? null
                : new TiersDto(//
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
                        null //
                );
    }
}