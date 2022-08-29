create table if not exists event
(
	id int auto_increment
		primary key,
	name varchar(45) not null,
	number_of_participants int not null,
	place varchar(45) not null,
	amount decimal(6,2) not null,
	datetime timestamp not null
);

create table if not exists reports
(
	event_id int not null,
	report varchar(45) not null,
	speaker varchar(45) null,
	status varchar(45) not null,
	primary key (event_id, report),
	constraint fk_reports_event1
		foreign key (event_id) references event (id)
);

create table if not exists user
(
	login varchar(45) not null
		primary key,
	password varchar(45) not null,
	name varchar(45) not null,
	email varchar(45) not null,
	account decimal(7,2) default 0.00 not null,
	role varchar(45) not null,
	constraint login_UNIQUE
		unique (login)
);

create table if not exists participants
(
	user_login varchar(45) not null,
	event_id int not null,
	status varchar(50) not null,
	primary key (user_login, event_id),
	constraint fk_user_has_event_event1
		foreign key (event_id) references event (id),
	constraint fk_user_has_event_user1
		foreign key (user_login) references user (login)
);

create index fk_user_has_event_event1_idx
	on participants (event_id);

create index fk_user_has_event_user1_idx
	on participants (user_login);


