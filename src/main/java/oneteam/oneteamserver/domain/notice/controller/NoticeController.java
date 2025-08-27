package oneteam.oneteamserver.domain.notice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.notice.dto.NoticeResponse;
import oneteam.oneteamserver.domain.notice.service.NoticeFinder;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "공지 API", description = "공지 관련 API 입니다.")
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeFinder noticeFinder;

    @GetMapping
    @Operation(summary = "공지 조회", description = "키워드로 공지를 검색하거나 전체 공지를 조회합니다. 페이징이 적용됩니다.")
    public Page<NoticeResponse> getNotices(
            @RequestParam(required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return noticeFinder.getNotices(keyword, page-1, size);
    }
}
