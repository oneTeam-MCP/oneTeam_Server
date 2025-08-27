package oneteam.oneteamserver.domain.notice.repository;

import oneteam.oneteamserver.domain.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
