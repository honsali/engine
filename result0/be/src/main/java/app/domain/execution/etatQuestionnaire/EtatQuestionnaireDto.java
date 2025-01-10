package app.domain.execution.etatQuestionnaire;


public record EtatQuestionnaireDto(//
        Long id, //
        Long idEtatQuestionnaire, //
        String libelle //
) {

    public static EtatQuestionnaireDto asRef(EtatQuestionnaire entity) {
        return entity == null ? null
                : new EtatQuestionnaireDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getLibelle() //
                );
    }
}