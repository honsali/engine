package app.domain.conception.etatVersionModeleQuestionnaire;


public record EtatVersionModeleQuestionnaireDto(//
        Long id, //
        Long idEtatVersionModeleQuestionnaire, //
        String libelle //
) {

    public static EtatVersionModeleQuestionnaireDto asRef(EtatVersionModeleQuestionnaire entity) {
        return entity == null ? null
                : new EtatVersionModeleQuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelle() //
                );
    }
}