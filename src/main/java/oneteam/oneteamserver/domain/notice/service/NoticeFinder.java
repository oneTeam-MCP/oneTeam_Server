package oneteam.oneteamserver.domain.notice.service;

import oneteam.oneteamserver.domain.notice.dto.NoticeResponse;
import org.springframework.data.domain.Page;

public interface NoticeFinder {
    Page<NoticeResponse> getNotices(String keyword, int page, int size);
}
