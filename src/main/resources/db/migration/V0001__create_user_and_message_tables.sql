use `chat`;

create table `users`
(
    `id`      INT auto_increment NOT NULL,
    `name`    varchar(128)       NOT NULL,
    `session` varchar(128)       NOT NULL,
    PRIMARY KEY (`id`)
);

create table `chats`
(
    `id`   int auto_increment not null primary key,
    `name` varchar(128)       not null,
    `time` datetime           not null
);

create table `messages`
(
    `id`      INT auto_increment not null primary key,
    `text`    varchar(128)       NOT NULL,
    `time`    datetime           NOT NULL,
    `user_id` int                NOT NULL,
    `chat_id` int                not null,
    CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    constraint `fk_chat` foreign key (`chat_id`) references `chats` (`id`)
);

insert into `users` (name, session)
values ('first_user', 'first_session');
insert into `users` (name, session)
values ('second_user', 'first_session');
insert into `users` (name, session)
values ('third_user', 'first_session');

insert into `chats` (name, time) value ('first_chat', CAST('2019-06-18 11:35:09.000' as DateTime));

insert into `messages` (text, time, user_id, chat_id)
values ('first_message', CAST('2019-08-18 13:35:09.000' as DateTime), 1, 1);
insert into `messages` (text, time, user_id, chat_id)
values ('message for first chat', CAST('2019-08-18 14:35:09.000' as DateTime), 2, 1);
insert into `messages` (text, time, user_id, chat_id)
values ('message for first chat', CAST('2019-08-18 15:35:09.000' as DateTime), 3, 1);
insert into `messages` (text, time, user_id, chat_id)
values ('second_message', CAST('2019-08-18 17:35:09.000' as DateTime), 2, 1);
insert into `messages` (text, time, user_id, chat_id)
values ('third_message', CAST('2019-08-18 16:35:09.000' as DateTime), 3, 1);