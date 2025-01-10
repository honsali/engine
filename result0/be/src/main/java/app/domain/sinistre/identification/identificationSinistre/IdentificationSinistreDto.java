package app.domain.sinistre.identification.identificationSinistre;


public record IdentificationSinistreDto(//
        Long id, //
        Long idIdentificationSinistre, //
        String libelle, //
        String numeroSinistre, //
        String dateSurvenance, //
        String dateOuverture //
) {

    public static IdentificationSinistreDto asEntity(IdentificationSinistre entity) {
        return entity == null ? null
                : new IdentificationSinistreDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroSinistre(), //
                        entity.getNumeroSinistre(), //
                        entity.getDateSurvenance(), //
                        entity.getDateOuverture() //
                );
    }

    public static IdentificationSinistreDto asRef(IdentificationSinistre entity) {
        return entity == null ? null
                : new IdentificationSinistreDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getNumeroSinistre(), //
                        entity.getNumeroSinistre(), //
                        null, //
                        null //
                );
    }
}