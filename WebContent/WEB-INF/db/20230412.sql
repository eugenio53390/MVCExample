create or replace table logs (
	uid int(11) not null,
	ts TIMESTAMP default current_timestamp,
	actions varchar(1024) not null,
	note varchar(1024),
	foreign key (uid) references user(Id), 
	primary key(uid, ts)
);
