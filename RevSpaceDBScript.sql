-----------------------------------------------
--DROPPERS--

	drop table if exists credentials;

drop table if exists post_likes;
	drop table if exists posts;
		drop table if exists users;

-----------------------------------------------
--CREATORS--

create table if not exists users (
	user_id serial primary key,
	email varchar(100) unique not null,
	name varchar(100) not null,
	birthday bigint default null,
	revature_join_date bigint default null,
	github_username varchar(50) not null default '',
	title varchar(100) not null default '',
	location varchar(500) not null default '',
	aboutme varchar(1000) not null default ''
);

create table if not exists credentials (
	user_id int primary key references users,
	password char(64) not null
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
