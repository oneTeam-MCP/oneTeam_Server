package oneteam.oneteamserver.domain.message.repository;

import oneteam.oneteamserver.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
