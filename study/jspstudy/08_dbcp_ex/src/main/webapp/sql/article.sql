DROP TABLE ARTICLE_T;
CREATE TABLE ARTICLE_T (
    /* 기사번호   */ ARTICLE_NO   NUMBER              NOT NULL,
    /* 제목       */ TITLE        VARCHAR2(1000 BYTE) NOT NULL,
    /* 내용       */ CONTENT      CLOB,
    /* 작성자     */ EDITOR       VARCHAR2(1000 BYTE) NOT NULL,
    /* 조회수     */ HIT          NUMBER,
    /* 최종수정일 */ LASTMODIFIED DATE,
    /* 최초작성일 */ CREATED      DATE,
    CONSTRAINT PK_ARTICLE PRIMARY KEY(ARTICLE_NO)
);

DROP SEQUENCE ARTICLE_SEQ;
CREATE SEQUENCE ARTICLE_SEQ NOCACHE;

/*
웰컴 화면으로 이동하기    /index.do
기사 등록화면으로 가기    /writeArticle.do
기사 등록하기             /addArticle.do
기사 수정화면으로 가기    /editArticle.do
기사 수정하기             /modifyArticle.do
기사 삭제하기             /deleteArticle.do
전체 기사 목록 가져오기   /getArticleList.do
상세 기사 내용 가져오기   /getArticleDetail.do
기사 조회수 늘리기        /plusHit.do
-----------------------------------------------
1. 특정 기사를 열면 해당 기사의 조회수를 증가시킨다.
   update article_t set hit = hit + 1 where article_no = 3;
2. 목록 보기에서 기사를 선택한 뒤 삭제한다.
   delete from article_t where article_no in(1, 2, 3);
*/

  /* 
  * 목록  update를 돌리는애  플러스 히트 내에서 작업이 끝나면 디테일로 넘겨주기 // 두번 쩜프
  *  1. 조회수 증가 2. 끝나면 다시 요청해서 상세보기를 하겠다. 요청 자체를 2개로 나눔
  *     /plusHit.do 
  *     redirect : /getAticleDetail.do
  * 편집
  *     /modifyA          ticle.do
  *       redirect :  /getAticleDetail.do
  */