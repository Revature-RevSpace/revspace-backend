-----------------------------------------------
--DROPPERS--

	drop table if exists credentials;

drop table if exists likes;
	drop table if exists posts;
		drop table if exists users;

-----------------------------------------------
--CREATORS--

create table if not exists users (
	user_id serial primary key,
	email varchar(100) unique not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	birthday bigint default null,
	revature_join_date bigint default null,
	github_username varchar(50) not null default '',
	title varchar(100) not null default '',
	location varchar(500) not null default '',
	aboutme varchar(1000) not null default ''
);

create table if not exists credentials (
	credentials_id serial primary KEY,
	user_id int references users(user_id) UNIQUE NOT NULL,
	password varchar(64) not null
);


create table posts (
	post_id serial primary key,
	creator_id int references users(user_id),
	body varchar,
	image varchar,
	date bigint,
	comment boolean,
	parent_post int references posts(post_id)
);

create table likes (
	like_id serial primary key,
	user_id int references users(user_id),
	post_id int references posts(post_id)
);


-----------------------------------------------
--POPULATORS--

	--REFERENCE TABLES--

	--MEAT TABLES--

	--TEST DATA--
insert into users values
	(default, 'username1@email.com', 'Charles', 'Mann', 0, 0, 'userGit1', 'someTitle', 'someTown', 'someThing'),
	(default, 'username2@email.com', 'Lacey', 'Irwin', 0, 0, 'userGit2', 'someTitle', 'someTown', 'someThing');

insert into credentials values
	(default, 1, 'Password1'),
	(default, 2, 'Password1');

insert into posts values 
	(default, 1, 'post1', 'https://i.imgur.com/s1L1qpB.jpeg', 1637446178, false, null),
	(default, 2, 'post2', '', 1637446178, false, null);

insert into posts values 
	(default, 1, 'post2 level1 comment1', '', 1637456178, true, 2),
	(default, 1, 'post2 level1 comment2', '', 1637466178, true, 2),
	(default, 1, 'post2 level1 comment3', '', 1637476178, true, 2),
	
	(default, 2, 'post1 level1 comment1', '', 1637456178, true, 1),
	(default, 2, 'post1 level1 comment2', '', 1637466178, true, 1),
	(default, 2, 'post1 level1 comment3', '', 1637476178, true, 1);

insert into posts values 
	(default, 1, 'post1 level2 comment1', '', 1637456178, true, 3),
	(default, 1, 'post1 level2 comment2', '', 1637456178, true, 3),
	
	(default, 2, 'post2 level2 comment1', '', 1637456178, true, 6),
	(default, 2, 'post2 level2 comment2', '', 1637456178, true, 6);
	
	
