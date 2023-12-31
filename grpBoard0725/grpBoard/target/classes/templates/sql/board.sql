use grpware;

create table grp_board(
grp_board_id int not null auto_increment, -- 보기, 수정, 삭제
grp_board_notice char(1) default 'N', -- 공지, 일반
grp_board_subject varchar(300), -- 제목
grp_board_writer varchar(20), -- 작성자
grp_board_content text, -- 내용
grp_board_upload_name varchar(255), -- 파일 이름
grp_board_upload_url varchar(30), -- 파일 주소
grp_board_upload_size bigint, -- 파일용량
grp_board_upload_trans varchar(255), -- 파일 바뀐 이름
grp_board_visit int, -- 조회수
grp_board_regdate datetime,
primary key(grp_board_id)
);








