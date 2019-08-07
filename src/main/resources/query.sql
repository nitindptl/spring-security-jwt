drop table if exists role;
drop table if exists user;
drop table if exists user_roles;
create table role (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=MyISAM;
create table user (id bigint not null auto_increment, age integer, password varchar(255), salary bigint, username varchar(255), primary key (id)) engine=MyISAM;
create table user_roles (id bigint not null auto_increment, user_id bigint not null, role_id bigint not null, primary key (id)) engine=MyISAM;
alter table user_roles add constraint FKrhfovtciq1l558cw6udg0h0d3 foreign key (role_id) references role (id);
alter table user_roles add constraint FK55itppkw3i07do3h7qoclqd4k foreign key (user_id) references user (id);
INSERT INTO user (id, username, password, salary, age) VALUES (1, 'nitin', '$2a$10$zkHHk2KVyEJlQJ/YKaS33u/Y1MmXzYYvwJ5psF1CPV7QZ4xZYrsM2', 3456, 33);
INSERT INTO user (id, username, password, salary, age) VALUES (2, 'admin', '$2a$10$vHmZVeVdDsmv1uYw4Gk4cuRT0FhOVahRjs3hqEoKWZ8W6lSMQOBOa', 7823, 23);

INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);