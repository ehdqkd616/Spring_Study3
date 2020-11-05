--------------------------------------------------
-----------------     SYSTEM	------------------	
--------------------------------------------------

CREATE USER MyBatis IDENTIFIED BY MyBatis;
GRANT RESOURCE, CONNECT TO MyBatis;
GRANT CREATE VIEW TO MyBatis;
ALTER USER MyBatis DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;

--------------------------------------------------



DROP TABLE REPLY;
DROP TABLE BOARD;
DROP TABLE MEMBER;

DROP SEQUENCE SEQ_BID;
DROP SEQUENCE SEQ_RID;

--------------------------------------------------
--------------     MEMBER 관련	------------------	
--------------------------------------------------

CREATE TABLE MEMBER (
  USER_ID VARCHAR2(30) PRIMARY KEY,
  USER_PWD VARCHAR2(100) NOT NULL,
  USER_NAME VARCHAR2(30) NOT NULL,
  NICKNAME VARCHAR2(30) NOT NULL,
  EMAIL VARCHAR2(100),
  BIRTHDAY DATE,
  GENDER VARCHAR2(1) CHECK (GENDER IN('M', 'F')),
  PHONE VARCHAR2(13),
  ADDRESS VARCHAR2(100),
  ENROLL_DATE DATE DEFAULT SYSDATE,
  UPDATE_DATE DATE DEFAULT SYSDATE,
  M_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(M_STATUS IN('Y', 'N'))
);

INSERT INTO MEMBER 
VALUES ('admin', '1234', '관리자', '운영자', 'admin@kh.or.kr', '19800918', 'F', '010-1111-2222', '서울시 강남구 역삼동', '20180101', '20180101', DEFAULT);

INSERT INTO MEMBER 
VALUES ('user01', 'pass01', '홍길동', '미스터홍', 'user01@kh.or.kr', '19900213', 'M','010-3333-4444', '서울시 양천구 목동', '20180201', '20180202', DEFAULT);

COMMIT;



----------------------------------------------------
-----------------     BOARD 관련        ------------------	
----------------------------------------------------

CREATE TABLE BOARD(
  BID NUMBER PRIMARY KEY,
  BTYPE NUMBER, 
  BTITLE VARCHAR2(100) NOT NULL,
  BCONTENT VARCHAR2(4000),
  BWRITER VARCHAR2(30),
  BCOUNT NUMBER DEFAULT 0,
  B_CREATE_DATE DATE,
  B_MODIFY_DATE DATE,
  B_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (B_STATUS IN('Y', 'N')),
  FOREIGN KEY (BWRITER) REFERENCES MEMBER
);

CREATE SEQUENCE SEQ_BID;

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '첫번째 게시판 서비스를 시작하겠습니다.', '안녕하세요. 첫 게시판입니다.', 'admin', DEFAULT, '20180210', '20180210', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '두번째 게시판 서비스를 시작하겠습니다.', '안녕하세요. 2 게시판입니다.', 'user01', DEFAULT, '20180211', '20180211', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '하이 에브리원 게시판 서비스를 시작하겠습니다.', '안녕하세요. 3 게시판입니다.', 'admin', DEFAULT, '20180212', '20180212', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '안녕.. 마이바티스는 처음이지?', '안녕하세요. 첫 게시판입니다.', 'user01', DEFAULT, '20180220', '20180220', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '어서와 ㅎㅎㅎㅎ', '반갑습니다.', 'admin', DEFAULT, '20180220', '20180220', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '페이징 처리때문에 샘플데이터 많이 넣어놓는다...', '안녕하십니까', 'admin', DEFAULT, '20180221', '20180221', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '게시판 서비스', '안녕하세요. 게시판입니다.', 'admin', DEFAULT, '20180221', '20180221', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '샘플데이터들 ', '안녕하세요.', 'user01', DEFAULT, '20180224', '20180225', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '모두들 화이팅!!', '화이팅 하세요!!', 'admin', DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '힘내세요!!!', ' 게시판입니다.', 'admin', DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '게시판 서비스', '프레임워크는 처음이지?', 'admin', DEFAULT, '20180301', '20180301', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '시작하겠습니다.', '지금부터 시작!!', 'user01', DEFAULT, '20181021', '20181021', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BID.NEXTVAL, 1, '마지막 게시판 시작하겠습니다.', '안녕하세요. 마지막 게시판입니다.', 'admin', DEFAULT, '20181021', '20181021', DEFAULT);



----------------------------------------------------
----------------    REPLY 관련         -------------------	
----------------------------------------------------

CREATE TABLE REPLY(
  RID NUMBER PRIMARY KEY,
  RCONTENT VARCHAR2(400),
  REF_BID NUMBER,
  RWRITER VARCHAR2(30),
  R_CREATE_DATE DATE,
  R_MODIFY_DATE DATE,
  R_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (R_STATUS IN ('Y', 'N')),
  FOREIGN KEY (REF_BID) REFERENCES BOARD,
  FOREIGN KEY (RWRITER) REFERENCES MEMBER
);

CREATE SEQUENCE SEQ_RID;

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '첫번째 댓글입니다.', 1, 'user01', '20180213', '20180213', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '첫번째 댓글입니다.', 13, 'user01', '20181030', '20181030', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '두번째 댓글입니다.', 13, 'user01', '20181030', '20181030', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RID.NEXTVAL, '마지막 댓글입니다.', 13, 'user01', '20181030', '20181030', DEFAULT);



COMMIT;


