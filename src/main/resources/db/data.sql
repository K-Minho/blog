INSERT INTO user_tb(username, password, email, created_at) values('ssar', '1234', 'ssar@nate.com', now());
INSERT INTO user_tb(username, password, email, created_at) values('cos', '1234', 'cos@nate.com', now());
INSERT INTO user_tb(username, password, email, created_at) values('love', '1234', 'love@nate.com', now());

INSERT INTO board_tb(title, content, thumbnail, user_id, created_at) values('제목1', '내용1', null, 1, now());
INSERT INTO board_tb(title, content, thumbnail, user_id, created_at) values('제목2', '내용2', null, 1, now());
INSERT INTO board_tb(title, content, thumbnail, user_id, created_at) values('제목3', '내용3', null, 2, now());
INSERT INTO board_tb(title, content, thumbnail, user_id, created_at) values('제목4', '내용4', null, 2, now());
INSERT INTO board_tb(title, content, thumbnail, user_id, created_at) values('제목5', '내용5', null, 3, now());
INSERT INTO board_tb(title, content, thumbnail, user_id, created_at) values('제목6', '내용6', null, 3, now());

commit;