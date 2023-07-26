use weberp;

create table kor_board(
kor_board_id int not null auto_increment, -- 보기, 수정, 삭제
kor_board_notice char(1) default 'N',
kor_board_writer varchar(20),
kor_board_subject varchar(300),
kor_board_content text,
kor_board_upload_name varchar(255),
kor_board_upload_url varchar(30),
kor_board_upload_size bigint,
kor_board_upload_trans varchar(255),
kor_board_visit int,
kor_board_reply_grp int, -- 원본 + 답글을 하나의 그룹으로 묶기 위한 번호
kor_board_reply_grp_seq int, -- 답글을 정렬하기 위한 순서값
kor_board_reply_grp_seq_indent int, -- 답글(1), 답글 답글(2), ...
kor_board_regdate datetime,
primary key(kor_board_id)
);

-- 게시판 정렬
create table test_board(
subject varchar(255),
grp int,
grp_seq int,
grp_seq_indent int
);

-- 게시물 등록 알고리즘 구현
insert into test_board values("1번 원본 게시물 입니다.", 1, 1, 1);
insert into test_board values("2번 원본 게시물 입니다.", 2, 1, 1);
insert into test_board values("3번 원본 게시물 입니다.", 2, 1, 1);

insert into test_board values("2번 답글입니다.", 2, 1, 1);
insert into test_board values("1번 답글입니다.", 1, 1, 1);

insert into test_board values("2번 답글 답글입니다.", 2, 2, 2);

insert into test_board values("1번 답글 답글입니다.", 1, 2, 2);

select * from test_board order by grp desc, grp_seq asc;
