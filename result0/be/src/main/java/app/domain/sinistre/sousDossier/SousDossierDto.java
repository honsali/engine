package app.domain.sinistre.sousDossier;

import app.domain.sinistre.tiers.tiers.TiersDto;

public record SousDossierDto(//
        Long id, //
        Long idSousDossier, //
        String libelle, //
        String dateSurvenance, //
        String heureSurvenance, //
        String etat, //
        String dateOuverture, //
        String dateDeclaration, //
        Boolean estMaster, //
        String idUtilisateur, //
        String idSinistre, //
        String profession, //
        Integer revenu, //
        String typeRevenu, //
        Boolean decede, //
        Boolean degatMat, //
        Boolean degatCorpo, //
        TiersDto tiers //
) {

    public static SousDossierDto asEntity(SousDossier entity) {
        return entity == null ? null
                : new SousDossierDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getDateSurvenance(), //
                        entity.getDateSurvenance(), //
                        entity.getHeureSurvenance(), //
                        entity.getEtat(), //
                        entity.getDateOuverture(), //
                        entity.getDateDeclaration(), //
                        entity.getEstMaster(), //
                        entity.getIdUtilisateur(), //
                        entity.getIdSinistre(), //
                        entity.getProfession(), //
                        entity.getRevenu(), //
                        entity.getTypeRevenu(), //
                        entity.getDecede(), //
                        entity.getDegatMat(), //
                        entity.getDegatCorpo(), //
                        TiersDto.asRef(entity.getTiers()) //
                );
    }

    public static SousDossierDto asRef(SousDossier entity) {
        return entity == null ? null
                : new SousDossierDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getDateSurvenance(), //
                        entity.getDateSurvenance(), //
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