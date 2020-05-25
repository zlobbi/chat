package km.hw72.chat.forms;

import lombok.*;


@Getter
@Setter
@ToString
public class MessageForm {
    private int chatId = 0;
    private String text = "";
    private String userName = "";
}
