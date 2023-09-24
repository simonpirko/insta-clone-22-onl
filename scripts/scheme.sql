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
                                      foreign key (author_id) references "human"(id)
);

CREATE TABLE "post_like"(
                            author_id int REFERENCES "human"(id),
                            post_id int REFERENCES "post"(id),
                            CONSTRAINT post_like_pk PRIMARY KEY(author_id, post_id)
);

create table if not exists "comment" (
                                         id serial not null primary key,
                                         author_id int references "human"(id),
                                         post_id int references "post"(id),
                                         text varchar(255) not null
);