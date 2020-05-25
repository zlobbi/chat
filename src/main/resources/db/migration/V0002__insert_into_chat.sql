use `chat`;


insert into `messages` (text, time, user_id, chat_id)
values ('this', CAST('2019-08-18 13:35:09.000' as DateTime), 1, 2);
insert into `messages` (text, time, user_id, chat_id)
values ('this for second chat', CAST('2019-08-18 14:35:09.000' as DateTime), 2, 2);
insert into `messages` (text, time, user_id, chat_id)
values ('this for second chat', CAST('2019-08-18 15:35:09.000' as DateTime), 3, 2);