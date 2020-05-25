package km.hw72.chat.DTO;

import km.hw72.chat.model.Chat;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ChatDTO {

    public static ChatDTO from(Chat chat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return builder()
                .id(chat.getId())
                .name(chat.getName())
                .time(chat.getTime().format(formatter))
                .messages(chat.getMessages().size())
                .build();
    }

    private int id;
    private String name;
    private String time;
    private int messages;
}
