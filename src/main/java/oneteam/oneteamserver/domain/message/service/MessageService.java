package oneteam.oneteamserver.domain.message.service;

import lombok.RequiredArgsConstructor;
import oneteam.oneteamserver.domain.message.Message;
import oneteam.oneteamserver.domain.message.dto.MessageResponse;
import oneteam.oneteamserver.domain.message.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;

    public Page<MessageResponse> getMessages(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(
                        Sort.Order.desc("createdAt"),
                        Sort.Order.desc("id")
                )
        );

        Page<Message> result = messageRepository.findAll(pageable);

        return result.map(MessageResponse::of);
    }
}
