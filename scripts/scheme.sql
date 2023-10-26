create table if not exists "country" (
                                         id serial not null primary key,
                                         name varchar(50) not null unique
);

create table if not exists "human" (
                                       id serial not null primary key,
                                       name varchar(50) not null,
                                       surname varchar(50) not null,
                                       username varchar(20) not null unique,
                                       photo bytea,
                                       email varchar(50) not null unique,
                                       password varchar(50) not null,
                                       country_id int not null,
                                       foreign key (country_id) references "country"(id)
);

create table if not exists "city" (
                                      id serial not null primary key,
                                      name varchar(50) not null unique,
                                      country_id int,
                                      foreign key (country_id) references "country"(id)
);

create table if not exists "post" (
                                      id serial not null primary key,
                                      author_id int,
                                      photo bytea,
                                      description varchar(250),
                                      created_at timestamp,
                                      foreign key (author_id) references "human"(id)
);

CREATE TABLE "post_like"(
                            author_id int REFERENCES "human"(id),
                            post_id int REFERENCES "post"(id) ON DELETE CASCADE,
                            created_at timestamp,
                            CONSTRAINT post_like_pk PRIMARY KEY(author_id, post_id)
);

create table if not exists "comment" (
    id serial not null primary key,
                                         author_id int references "human"(id),
                                         post_id int REFERENCES "post"(id) ON DELETE CASCADE,
                                         text varchar(255) not null
);

-- /* Таблица со списком чатов */
-- create table if not exists "chat" (
--     id serial not null unique, /* id чата */
--     user_id int references "human"(id), /* id пользователя создавшего чат */
--     name varchar(50) /* название чата */
-- );
--
-- /* Таблица со списком участников чата */
-- create table if not exists "chat_party" (
--     chat_id int references "chat"(id), /* id чата */
--     user_id int references "human"(id) /* id пользователя, который учавствует в переписке */
-- );
--
-- /* Таблица со списком сообщений */
-- create table if not exists "message" (
--     id serial not null unique, /* id сообщения */
--     chat_id int references "chat"(id), /* id чата, которому принадлежит сообщение  */
--     sender_id int references "human"(id), /* id пользователя, который отправил сообщение(автор) */
--     text varchar /* текст сообщения */
-- );

create table if not exists "favorite" (
    id serial not null unique,
    post_id int references "post"(id) on delete cascade,
    user_id int references "human"(id) on delete cascade
);

create table if not exists "hashtag" (
                                         id serial not null unique,
                                         name varchar(50) not null unique
);

create table if not exists "post_hashtag" (
    hashtag_id int references "hashtag"(id),
    post_id int references "post"(id) on delete cascade
);