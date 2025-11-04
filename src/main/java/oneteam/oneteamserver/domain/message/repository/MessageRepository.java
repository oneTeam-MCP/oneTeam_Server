package oneteam.oneteamserver.domain.message.repository;

import oneteam.oneteamserver.domain.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByUserId(String userId, Pageable pageable);
}
