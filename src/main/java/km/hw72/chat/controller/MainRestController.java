package km.hw72.chat.controller;

import km.hw72.chat.DTO.MessageDTO;
import km.hw72.chat.model.User;
import km.hw72.chat.service.MessageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainRestController {
    private final MessageService messageService;

    @GetMapping
    public List<MessageDTO> getMessages(Pageable pageable) {
        return messageService.getAllMessages(pageable).getContent();
    }

//    @GetMapping("/{id:\\d+?}")
//    public List<MessageDTO> getChatMessages(@PathVariable("id") int chatId) {
//        messageService.getChatMessages(chatId).forEach(m -> System.out.println(m));
//        return messageService.getChatMessages(chatId);
//    }

    @GetMapping("/{id:\\d+?}")
    public Page<MessageDTO> getChatMessages(@PathVariable("id") int chatId, Pageable pageable) {
        messageService.getChatMessages(pageable, chatId).forEach(m -> System.out.println(m));
        return messageService.getChatMessages(pageable, chatId);
    }

}
