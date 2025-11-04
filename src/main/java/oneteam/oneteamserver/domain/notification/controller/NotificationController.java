package oneteam.oneteamserver.domain.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.domain.notification.dto.NotificationResponse;
import oneteam.oneteamserver.domain.notification.service.NotificationService;
import oneteam.oneteamserver.global.annotation.CurrentMember;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "알림 API", description = "알림 관련 API 입니다.")
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @Operation(summary = "내 이캠 알림 조회", description = "내 이캠 알림을 조회합니다. 페이징이 적용됩니다.")
    public Page<NotificationResponse> getNotifications(
            @CurrentMember Member member,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return notificationService.getNotifications(member.getStudentId(), page-1, size);
    }
}
