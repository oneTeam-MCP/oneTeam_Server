package oneteam.oneteamserver.domain.notice.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.notice.Notice;
import oneteam.oneteamserver.domain.notice.dto.NoticeResponse;
import oneteam.oneteamserver.domain.notice.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeQueryService implements NoticeFinder {

    private final NoticeRepository noticeRepository;

    @Override
    public Page<NoticeResponse> getNotices(String keyword, int page, int size) {
        String k = (keyword == null) ? null : keyword.strip();
        boolean hasKeyword = (k != null) && !k.isBlank();

        Pageable pageable = PageRequest.of(
                page, size, Sort.by(Sort.Order.desc("date"), Sort.Order.desc("id"))
        );

        Page<Notice> result = hasKeyword
                ? noticeRepository.findByTitleContainingIgnoreCase(k, pageable)
                : noticeRepository.findAll(pageable);

        return result.map(NoticeResponse::of);
    }
}
