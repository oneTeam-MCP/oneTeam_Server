package oneteam.oneteamserver.domain.notification.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.notification.Notification;
import oneteam.oneteamserver.domain.notification.dto.NotificationResponse;
import oneteam.oneteamserver.domain.notification.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Page<NotificationResponse> getNotifications(int page, int size) {

        Pageable pageable = PageRequest.of(
                page, size, Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("id"))
        );

        Page<Notification> result = notificationRepository.findAll(pageable);

        return result.map(NotificationResponse::of);
    }
}
