package km.hw72.chat.repository;

import km.hw72.chat.model.Chat;
import km.hw72.chat.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Page<Message> findAllByChat_Id(Pageable pageable, int chatId);
    List<Message> findAllByChat_Id(int chatId);
    void deleteByChat(Chat chat);
}
