# javawebproject
b站比尔盖涛
就剩统计消费还没有做完
数据库：
drop table if exists account;

create table account(
	id int primary key auto_increment,
	ct datetime,
	cm float,
	cp varchar(255)
)CHARSET=utf8;

insert into account(ct,cm,cp) values('2022-1-1 19:10:20',204.5,'张三');
commit;
select * from account;

DROP TABLE IF EXISTS user1;
CREATE TABLE user1(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255)
)CHARSET=utf8
INSERT INTO user1(`name`) VALUES('张三');
commit;
SELECT * FROM user1；

CREATE TABLE user2 (
 id INT PRIMARY KEY AUTO_INCREMENT,
 username  VARCHAR(255),
`password`  VARCHAR(255)
	)
INSERT INTO user2(username,`password`)  VALUES('zhangsan','123456');
