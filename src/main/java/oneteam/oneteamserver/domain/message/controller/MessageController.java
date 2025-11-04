package oneteam.oneteamserver.domain.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.member.Member;
import oneteam.oneteamserver.domain.message.dto.MessageResponse;
import oneteam.oneteamserver.domain.message.service.MessageService;
import oneteam.oneteamserver.global.annotation.CurrentMember;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "메시지 API", description = "메시지 관련 API 입니다.")
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    @Operation(summary = "내 이캠 메시지 조회", description = "내 이캠 메시지를 조회합니다. 페이징이 적용됩니다.")
    public Page<MessageResponse> getMessages(
            @CurrentMember Member member,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return messageService.getMyMessages(member.getStudentId(), page - 1, size);
    }
}
