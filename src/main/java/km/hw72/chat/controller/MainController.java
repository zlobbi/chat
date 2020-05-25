package km.hw72.chat.controller;

import km.hw72.chat.DTO.MessageDTO;
import km.hw72.chat.DTO.UserDTO;
import km.hw72.chat.forms.MessageForm;
import km.hw72.chat.model.Message;
import km.hw72.chat.model.User;
import km.hw72.chat.repository.MessageRepository;
import km.hw72.chat.service.ChatService;
import km.hw72.chat.service.MessageService;
import km.hw72.chat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {
    private final UserService userService;
    private final MessageService messageService;
    private final ChatService chatService;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri, String content) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute(content, list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s", uri, page);
    }

    @GetMapping
    public String root(HttpSession session, Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        if(userService.isUserExitsts(session.getId())) {
            model.addAttribute("dto", UserDTO.from(userService.getUserBySession(session.getId())));
        }
        var uri = uriBuilder.getRequestURI();
        constructPageable(chatService.getAllChats(pageable), 10, model, uri, "chats");
        return "index";
    }

    @GetMapping("/chat/{id:\\d+?}")
    public String chat(@PathVariable("id") int chatId, Model model, Pageable pageable,
                       HttpServletRequest uriBuilder, HttpSession session) {
        var chat = chatService.getById(chatId);
        var messages = messageService.getChatMessages(pageable, chatId);
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new MessageForm());
        }
        if (userService.isUserExitsts(session.getId())) {
            model.addAttribute("dto", userService.getUserBySession(session.getId()));
        }
        var uri = uriBuilder.getRequestURI();
        model.addAttribute("chat", chat);
        constructPageable(messages, 1, model, uri, "messages");
        return "chat";
    }

    @RequestMapping("chat/{id:\\d+?}")
    public String addMessage(MessageForm form, HttpServletRequest uriBuilder,
                             HttpSession session) {
        if (!userService.isUserExitsts(session.getId())) {
            var u = User.builder()
                    .session(session.getId())
                    .name(form.getUserName())
                    .build();
            userService.saveUser(u);
        }
        var m = Message.builder()
                .text(form.getText())
                .time(LocalDateTime.now())
                .chat(chatService.getChatById(form.getChatId()))
                .user(userService.getUserBySession(session.getId()))
                .build();
        messageService.saveMessage(m);
        return "redirect:http://localhost:8080" + uriBuilder.getRequestURI();
    }

}
