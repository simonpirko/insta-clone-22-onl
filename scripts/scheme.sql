create table if not exists "Country" (
    id serial unique not null primary key,
    name varchar(50) not null unique
);

create table if not exists "Human" (
    id serial unique not null primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    username varchar(20) not null unique,
    photo bytea,
    email varchar(50) not null unique,
    password varchar(50) not null,
    countryId int not null,
    foreign key (countryId) references "Country"(id)
);

create table if not exists "City" (
    id serial unique not null primary key,
    name varchar(50) not null unique,
    countryId int unique,
    foreign key (countryId) references "Country"(id)
);

create table if not exists "Post" (
    id serial unique not null primary key,
    author int unique,
    photo bytea,
    description varchar(250),
    foreign key (author) references "Human"(id)
);

create table if not exists "PostLike" (
    id serial unique not null primary key,
    author int unique,
    postId int unique,
    foreign key (author) references "Human"(id),
    foreign key (postId) references "Post"(id)
);

create table if not exists Comment (
    id serial unique not null primary key,
    author int unique,
    postId int unique,
    foreign key (author) references "Human"(id),
    foreign key (postId) references "Post"(id)
)

