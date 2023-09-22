package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.ArticleDto;



public class ArticleDao {

  // 모든 메소드가 공동으로 사용할 객체 선언
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  // Connection Pool 관리 DataSource 객체 선언
  private DataSource dataSource;
  
  // Singleton Pattern으로 ArticleDao 객체 생성
  private static ArticleDao dao = new ArticleDao();
  private ArticleDao() {
    // META-INF/context.xml에 있는 <Resource name="jdbc/oraclexe" /> 태그 내용을 읽어서 DataSource 객체 생성하기
    try {
      Context context = new InitialContext();
      Context env = (Context)context.lookup("java:comp/env");
      dataSource = (DataSource)env.lookup("jdbc/oraclexe");
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static ArticleDao getDao() {
    return dao;
  }
  
  // 자원 반납 메소드
  public void close() {
    try {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(con != null) con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  // 게시글 등록 메소드
  public int register(ArticleDto dto) {
    
    // 등록 결과 선언 (insert 실행 결과는 삽입된 행의 개수이다.)
    int insertResult = 0;
    
    try {
      
      // Connection Pool에서 Connection을 하나 받아온다.
      // Connection Pool 관리는 DataSource 객체가 수행한다.
      
      con = dataSource.getConnection();
      
      // 쿼리문 작성 
      String sql = "INSERT INTO ARTICLE_T(ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED) VALUES (ARTICLE_SEQ.NEXTVAL, ?, ?, ?, 0, SYSDATE, SYSDATE)";
      
      // ps 객체 생성 (쿼리문 실행을 담당하는 객체)
      ps = con.prepareStatement(sql);
      
      // 쿼리문의 변수(?로 처리된 부분)에 값을 전달
      ps.setString(1, dto.getTitle());   // 1번째 물음표(?)에 dto.getTitle() 전달하기
      ps.setString(2, dto.getContent()); // 2번째 물음표(?)에 dto.getContent() 전달하기
      ps.setString(3, dto.getEditor());
      
      // 쿼리문의 실행
      insertResult = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 등록 결과 반환
    return insertResult;
    
  }
  
  // 게시글 개수 반환 메소드
  public int getArticleCount() {
    
    // 게시글 개수
    int count = 0;
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM ARTICLE_T";  //  COUNT(*)
                                                    //  --------
                                                    //     120
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) {
        count = rs.getInt(1);  // count = rs.getInt("COUNT(*)")도 가능함
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 게시글 개수 반환
    return count;
    
  }
  
  // 게시글 목록 반환 메소드
  public List<ArticleDto> getArticleList(Map<String, Object> map){
    
    // 게시글 목록 저장 List
    List<ArticleDto> list = new ArrayList<ArticleDto>();
    
    try {
      
      con = dataSource.getConnection();
      String sql = "SELECT A.ARTICLE_NO, A.TITLE, A.CONTENT, A.EDITOR, A.HIT, A.LASTMODIFIED, A.CREATED"
          + " FROM (SELECT ROW_NUMBER() OVER (ORDER BY ARTICLE_NO DESC) AS RN, ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED"
          + "       FROM ARTICLE_T) A" 
          + " WHERE A.RN BETWEEN ? AND ?";

      ps = con.prepareStatement(sql);
      ps.setInt(1, (int)map.get("begin"));
      ps.setInt(2, (int)map.get("end"));
      rs = ps.executeQuery();
      while(rs.next()) {
        // rs -> ArticleDto
        ArticleDto dto = ArticleDto.builder()
                        .article_no(rs.getInt(1))
                        .title(rs.getString(2))
                        .content(rs.getString(3))
                        .editor(rs.getString(4))
                        .hit(rs.getInt(5))
                        .last_modified(rs.getDate(6))
                        .created(rs.getDate(7))
                        .build();
        // ArticleDto -> list 추가
        list.add(dto);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    // 게시글 목록 반환
    return list;
    
  }
  
  // 게시글 반환 메소드
  public ArticleDto getArticleByNo(int article_no) {
    
    // 게시글
    ArticleDto dto = null;
    
    try {
      
      con= dataSource.getConnection();
      String sql = "SELECT ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED"
                 + "   FROM ARTICLE_T"
                 + "   WHERE ARTICLE_NO = ?";   // 이게 pk라 조회 결과가 1개 아니면 없는 것 next 한 번 만 호출
       ps = con.prepareStatement(sql);
       ps.setInt(1, article_no);
       rs = ps.executeQuery();
       if(rs.next()) {   // 검색 결과가 있으면 dto를 만들고, 없으면 안만들 어서 ->  ArticleDto dto = null;
         dto = ArticleDto.builder()
             .article_no(rs.getInt(1))
             .title(rs.getString(2))
             .content(rs.getString(3))
             .editor(rs.getString(4))
             .hit(rs.getInt(5))
             .last_modified(rs.getDate(6))
             .created(rs.getDate(7))
             .build();
       }
       
       
    } catch (Exception e) {
     e.printStackTrace();
    } finally {
      close();    // 이거 완성하고 서비스로 가서 작성
    }
    
    // 게시글 반환
    return dto;
    
    
  }
   
  // 게시글 수정 메소드
  public int modify(ArticleDto dto) {
    
  // 수정결과
  int modifyResult = 0;
  
  try {
    
    con = dataSource.getConnection();
    String sql ="UPDATE ARTICLE_T"
        + "  SET TITLE = ?, CONTENT = ?, LASTMODIFIED = SYSDATE"
        + "  WHERE ARTICLE_NO = ?";
    ps = con.prepareStatement(sql);
    ps.setString(1, dto.getTitle());
    ps.setString(2, dto.getContent());
    ps.setInt(3, dto.getArticle_no());
    modifyResult = ps.executeUpdate();
        
        
  } catch (Exception e) {
    e.printStackTrace();
  } finally {
    close();
  }
    // 수정결과 반환
   return modifyResult;
    
    
  }
  
  // 게시글 삭제 메소드
  public int delete(int article_no) {
    
    // 삭제 결과
    int deleteResult = 0;
    
    try {
      con = dataSource.getConnection();
      String sql= "DELETE FROM ARTICLE_T WHERE ARTICLE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, article_no);
      deleteResult = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    
    // 삭제 결과 반환
    
    return deleteResult;
    
  }
  
  
  
}