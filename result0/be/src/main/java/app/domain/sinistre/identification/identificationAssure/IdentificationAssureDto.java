package app.domain.sinistre.identification.identificationAssure;


public record IdentificationAssureDto(//
        Long id, //
        Long idIdentificationAssure, //
        String libelle, //
        String libelleAssure, //
        String numeroIdentification, //
        String numeroVehicule, //
        String numeroImmatriculation //
) {

    public static IdentificationAssureDto asEntity(IdentificationAssure entity) {
        return entity == null ? null
                : new IdentificationAssureDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelleAssure(), //
                        entity.getLibelleAssure(), //
                        entity.getNumeroIdentification(), //
                        entity.getNumeroVehicule(), //
                        entity.getNumeroImmatriculation() //
                );
    }

    public static IdentificationAssureDto asRef(IdentificationAssure entity) {
        return entity == null ? null
                : new IdentificationAssureDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelleAssure(), //
                        entity.getLibelleAssure(), //
                        null, //
                        null, //
                        null //
                );
    }
}