package oneteam.oneteamserver.domain.notification.repository;

import oneteam.oneteamserver.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
