package app.domain.commun.notification.notification;

import app.domain.commun.reference.reference.ReferenceDto;

public record NotificationDto(//
        Long id, //
        Long idNotification, //
        String libelle, //
        String message, //
        String utilisateur, //
        Boolean active, //
        ReferenceDto typeNotification, //
        String dateCreation, //
        String createur //
) {

    public static NotificationDto asEntity(Notification entity) {
        return entity == null ? null
                : new NotificationDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getMessage(), //
                        entity.getMessage(), //
                        entity.getUtilisateur(), //
                        entity.getActive(), //
                        ReferenceDto.asRef(entity.getTypeNotification()), //
                        entity.getDateCreation(), //
                        entity.getCreateur() //
                );
    }

    public static NotificationDto asRef(Notification entity) {
        return entity == null ? null
                : new NotificationDto(//
                        entity.getId(), //
                        entity.getId(), //
                        entity.getMessage(), //
                        entity.getMessage(), //
                        null, //
                        null, //
                        null, //
                        null, //
                        null //
                );
    }
}