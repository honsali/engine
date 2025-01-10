package app.domain.sinistre.sinistre;

import app.domain.commun.expediteur.expediteur.ExpediteurDto;
import app.domain.commun.reference.reference.ReferenceDto;

public record SinistreDto(//
        Long id, //
        Long idSinistre, //
        String libelle, //
        String numeroSinistre, //
        String dateOuverture, //
        String dateSurvenance, //
        String heureSurvenance, //
        String dateHeureSurvenance, //
        String etat, //
        Integer montantReserveGlobal, //
        String segmentDep, //
        String numeroDeclaration, //
        String dateDeclaration, //
        String numeroSinistreIntermediaire, //
        String dateEffetContrat, //
        String dateFinContrat, //
        String validite, //
        ReferenceDto etatPolice, //
        Integer numeroPolice, //
        String categorie, //
        String libelleProduitTechnique, //
        ExpediteurDto intermediaire, //
        String nomOuRaisonSocialeClient, //
        ReferenceDto produitCommercial //
) {

    public static SinistreDto asEntity(Sinistre entity) {
        return entity == null ? null
                : new SinistreDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroSinistre(), //
                        entity.getNumeroSinistre(), //
                        entity.getDateOuverture(), //
                        entity.getDateSurvenance(), //
                        entity.getHeureSurvenance(), //
                        entity.getDateHeureSurvenance(), //
                        entity.getEtat(), //
                        entity.getMontantReserveGlobal(), //
                        entity.getSegmentDep(), //
                        entity.getNumeroDeclaration(), //
                        entity.getDateDeclaration(), //
                        entity.getNumeroSinistreIntermediaire(), //
                        entity.getDateEffetContrat(), //
                        entity.getDateFinContrat(), //
                        entity.getValidite(), //
                        ReferenceDto.asRef(entity.getEtatPolice()), //
                        entity.getNumeroPolice(), //
                        entity.getCategorie(), //
                        entity.getLibelleProduitTechnique(), //
                        ExpediteurDto.asRef(entity.getIntermediaire()), //
                        entity.getNomOuRaisonSocialeClient(), //
                        ReferenceDto.asRef(entity.getProduitCommercial()) //
                );
    }

    public static SinistreDto asRef(Sinistre entity) {
        return entity == null ? null
                : new SinistreDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroSinistre(), //
                        entity.getNumeroSinistre(), //
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