package app.domain.tiers.entiteAdverse;

import java.time.LocalDate;
import app.domain.commun.reference.reference.ReferenceDto;
import app.domain.sinistre.sinistre.SinistreDto;

public record EntiteAdverseDto(//
        Long id, //
        Long idEntiteAdverse, //
        String libelle, //
        String nom, //
        String prenom, //
        ReferenceDto typeIdentification, //
        String numeroIdentification, //
        String adresse, //
        ReferenceDto assureur, //
        String codeIntermediaire, //
        String nomIntermediaire, //
        String immatriculationVehicule, //
        String numeroPolice, //
        LocalDate dateDebutContrat, //
        LocalDate dateFinContrat, //
        Boolean degatsMateriel, //
        Boolean degatsCorporel, //
        Boolean personnePhysique, //
        Boolean personneMorale, //
        LocalDate dateNaissance, //
        ReferenceDto sexe, //
        ReferenceDto ville, //
        ReferenceDto qualiteVictime, //
        ReferenceDto situationFamiliale, //
        String tel, //
        String email, //
        ReferenceDto profession, //
        SinistreDto sinistre //
) {

    public static EntiteAdverseDto asEntity(EntiteAdverse entity) {
        return entity == null ? null
                : new EntiteAdverseDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNom(), //
                        entity.getNom(), //
                        entity.getPrenom(), //
                        ReferenceDto.asRef(entity.getTypeIdentification()), //
                        entity.getNumeroIdentification(), //
                        entity.getAdresse(), //
                        ReferenceDto.asRef(entity.getAssureur()), //
                        entity.getCodeIntermediaire(), //
                        entity.getNomIntermediaire(), //
                        entity.getImmatriculationVehicule(), //
                        entity.getNumeroPolice(), //
                        entity.getDateDebutContrat(), //
                        entity.getDateFinContrat(), //
                        entity.getDegatsMateriel(), //
                        entity.getDegatsCorporel(), //
                        entity.getPersonnePhysique(), //
                        entity.getPersonneMorale(), //
                        entity.getDateNaissance(), //
                        ReferenceDto.asRef(entity.getSexe()), //
                        ReferenceDto.asRef(entity.getVille()), //
                        ReferenceDto.asRef(entity.getQualiteVictime()), //
                        ReferenceDto.asRef(entity.getSituationFamiliale()), //
                        entity.getTel(), //
                        entity.getEmail(), //
                        ReferenceDto.asRef(entity.getProfession()), //
                        SinistreDto.asRef(entity.getSinistre()) //
                );
    }

    public static EntiteAdverseDto asRef(EntiteAdverse entity) {
        return entity == null ? null
                : new EntiteAdverseDto(//
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