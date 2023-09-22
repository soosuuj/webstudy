package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BookDto;

public class BookDao {

  // mybatis의 SqlSession을 만들 수 있는 SqlSessionFactory 선언
  private SqlSessionFactory factory;
  
  // Singleton Pattern
  private static BookDao dao = new BookDao();
  private BookDao() {
    // SqlSessionFactory 생성
    try {
      String resource = "mybatis/config/mybatis-config.xml";
      InputStream in = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(in);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static BookDao getDao() {
    return dao;
  }

    // 매퍼의 namespace
    private final String NS = "mybatis.mapper.book.";
  
  
  
  // 전체 개수 반환 메소드
  // 모든 메소드는 factory 에서 sqlSession빼는 작업 먼저, 한개 
  // 어떤 메퍼의 무슨 쿼리 연결은 . 로 
  // SqlSession 는 con, 어쩌 구 다 쓰기 떄문에 close
    public int bookCount() {
      SqlSession ss = factory.openSession();
      int count = ss.selectOne(NS + "bookCount");
      ss.close();
      return count;
    }
  
  
  // 목록 반환 메소드
  // 파라미터 붙이려면  아이디 뒤에 , 이름 이렇게 쓰기 서비스 에서 받아온 값 map 으로 전달
    public List<BookDto> bookList(Map<String, Object> map) {
      SqlSession ss = factory.openSession();
      List<BookDto> list = ss.selectList(NS + "bookList", map);
      ss.close();
      return list;
    }
  
  // 상세 반환 메소드
  // factory.openSession(); 커밋 대상이 아님
    public BookDto bookDetail(int bookNo) {
      SqlSession ss = factory.openSession();
      BookDto dto = ss.selectOne(NS + "bookDetail", bookNo);
      ss.close();
      return dto;
    }
  
  
  // 등록 메소드
  // openSession - 2- auto commit -> 자동으로 할지 insert, delete, update 때문에
  // 2개이상의 3개가 조합되는 경우에 얘내를 하나로 묶어서 트랜젝션 처리
    public int bookAdd(BookDto dto) {
      SqlSession ss = factory.openSession(false);  // false : 내가 커밋하겠다.
      int addResult = ss.insert(NS + "bookAdd", dto);
      if(addResult == 1) {
        ss.commit();
      }
      ss.close();
      return addResult;
    }
  
  
  // 수정 메소드
    public int bookModify(BookDto dto) {
      SqlSession ss = factory.openSession(false);
      int modifyResult = ss.update(NS + "bookModify", dto);
      if(modifyResult == 1) {
        ss.commit();
      }
      ss.close();
      return modifyResult;
    }
  
  
  // 삭제 메소드
    public int bookDelete(int bookNo) {
      SqlSession ss = factory.openSession(false);
      int deleteResult = ss.delete(NS + "bookDelete", bookNo);
      if(deleteResult == 1) {
        ss.commit();
      }
      ss.close();
      return deleteResult;
    }
    
  }
