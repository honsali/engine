package app.domain.commun.notification.notification;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@Transactional
public class NotificationResource {

    private final NotificationRepository notificationRepository;

    public NotificationResource(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}