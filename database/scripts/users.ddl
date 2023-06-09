create table users
(
    username text    not null primary key,
    password text    not null,
    enabled  boolean not null
);
create table authorities
(
    username  text not null,
    authority text not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

create table customer4auth
(
    id    serial not null primary key,
    email text   not null,
    pwd   text   not null,
    role  text   not null
);

create unique index ix_customer4auth_email on customer4auth (email);

--

insert into users
values ('admin', 'welcome1', true);
insert into authorities
values ('admin', 'admin');
insert into customer4auth(email, pwd, role)
values ('johndb@mail.com', '12345', 'admin');

--
select *
from users;
select *
from authorities;
--
select * from customer4auth;