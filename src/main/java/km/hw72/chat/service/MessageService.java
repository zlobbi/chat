package km.hw72.chat.service;

import km.hw72.chat.DTO.MessageDTO;
import km.hw72.chat.model.Chat;
import km.hw72.chat.model.Message;
import km.hw72.chat.repository.MessageRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageService {
    private final MessageRepository repository;

    public Page<MessageDTO> getAllMessages(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 1, Sort.by("time").ascending());
        return repository.findAll(pageable).map(MessageDTO::from);
    }

    public Page<MessageDTO> getChatMessages(Pageable pageable, int chatId) {
        pageable = PageRequest.of(pageable.getPageNumber(), 1, Sort.by("time").ascending());
        return repository.findAllByChat_Id(pageable, chatId).map(MessageDTO::from);
    }

    public void saveMessage(Message m) {
        repository.save(m);
    }

    public List<MessageDTO> getChatMessages(int chatId) {
        return repository.findAll().stream().map(MessageDTO::from).collect(Collectors.toList());
    }

    public void updateChat(Chat chat) {
        int size = repository.findAllByChat_Id(chat.getId()).size();
//        if (size > 20) {
            repository.deleteAll();
//        }
    }
}
