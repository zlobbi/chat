package km.hw72.chat.service;

import km.hw72.chat.DTO.ChatDTO;
import km.hw72.chat.model.Chat;
import km.hw72.chat.repository.ChatRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatService {
    private ChatRepository repository;

    public Page<ChatDTO> getAllChats(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 10, Sort.by("time").ascending());
        return repository.findAll(pageable).map(ChatDTO::from);
    }

    public ChatDTO getById(int chatId) {
        return ChatDTO.from(repository.findById(chatId).get());
    }
    public Chat getChatById(int chatId) {
        return repository.findById(chatId).get();
    }
}
