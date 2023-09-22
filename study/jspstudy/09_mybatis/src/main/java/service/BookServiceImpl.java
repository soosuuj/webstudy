package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.BookDto;
import repository.BookDao;
import util.PageVo;



public class BookServiceImpl implements BookService {

  // 모든 서비스가 공동으로 사용하는 BoardDao, PageVo 객체 가져오기
  private BookDao dao = BookDao.getDao();
  private PageVo pageVo = new PageVo();

  @Override
  public ActionForward register(HttpServletRequest request) {
    
    // 등록할 제목과 내용
    String title = request.getParameter("title");
    String author = request.getParameter("author");
    int price = Integer.parseInt(request.getParameter("price"));

    
    // 제목 + 내용 -> BoardDto 객체
    BookDto dto = BookDto.builder()
                      .title(title)
                      .author(author)
                      .price(price)
                      .build();
    
    // BookDao의 register 메소드 호출
    int registerResult = dao.bookAdd(dto);
    
    // 등록 성공(registerResult == 1), 등록 실패(registerResult == 0)
    String path = null;
    if(registerResult == 1) {
      path = request.getContextPath() + "/book/list.do";
    } else if(registerResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    // 어디로 어떻게 이동하는지 반환 (insert 수행 후에는 반드시 redirect 이동한다.)
    return new ActionForward(path, true);
    
  }

  @Override
  public ActionForward getBookList(HttpServletRequest request) {
    
    /* page, total, display 정보가 있어야 목록을 가져올 수 있다. */
    
    // 전달된 페이지 번호 (페이지 번호의 전달이 없으면 1 페이지를 연다.)
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    /// dao.get 이 아니고 그냥 bookCount???
    int total = dao.bookCount();
    
    int display = 5;  // 고정 값 사용(원하면 파라미터로 받아 오는 것으로 변경도 가능함)
    
    // PageVo의 모든 정보 계산하기
    pageVo.setPaging(page, total, display);
    
    // 게시글 목록을 가져올 때 사용할 변수들을 Map으로 만듬
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageVo.getBegin());
    map.put("end", pageVo.getEnd());
    
    // DB로부터 게시글 목록 가져오기
    List<BookDto> bookList = dao.bookList(map);
    
    // 게시글 목록과 paging을 /board/list.jsp로 전달하기 위하여 request에 저장한 뒤 forward한다.
    request.setAttribute("bookList", bookList);
    request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/book/list.do"));
    return new ActionForward("/book/list.jsp", false);
    
  }
  
  @Override
  public ActionForward getBookByNo(HttpServletRequest request) {
    
    // 상세조회할 게시글 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("book_no"));
    int book_no = Integer.parseInt(opt.orElse("0"));
    
    // DB로부터 게시글 가져오기
    BookDto book = dao.bookDetail(book_no);
    
    // 게시글을 /board/detail.jsp에 전달하기 위해서 forward 처리
    request.setAttribute("book", book);
    return new ActionForward("/book/detail.jsp", false);
    
  }

  
  
  @Override
  public ActionForward edit(HttpServletRequest request) {
      
      // 편집할 게시글 번호
      Optional<String> opt = Optional.ofNullable(request.getParameter("book_no"));
      int book_no = Integer.parseInt(opt.orElse("0"));
      
      // DB로부터 게시글 가져오기
      BookDto book = dao.bookDetail(book_no);
      
      // 게시글을 /book/edit.jsp에 전달하기 위해서 forward 처리
      request.setAttribute("book", book);
      return new ActionForward("/book/edit.jsp", false);
      
  }


  
  @Override
  public ActionForward modify(HttpServletRequest request) {
    
    // 수정할 게시글 정보
    String title = request.getParameter("title");
    String author = request.getParameter("author");
    int price = Integer.parseInt(request.getParameter("price"));
    
    // 수정할 게시글 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("book_no"));
    int book_no = Integer.parseInt(opt.orElse("0"));
    
    // 수정할 게시글 정보를 BoardDto 객체로 생성
    BookDto dto = BookDto.builder()
                      .title(title)
                      .author(author)
                      .price(price)
                      .build();
                  
    // 수정하기
    int modifyResult = dao.bookModify(dto);
    
    // 수정 성공(modifyResult == 1), 수정 실패(modifyResult == 0)
    String path = null;
    if(modifyResult == 1) {
      path = request.getContextPath() + "/book/detail.do?book_no=" + book_no;
    } else {
      path = request.getContextPath() + "/index.do";
    }
    
    // update 이후에는 redirect 한다.
    return new ActionForward(path, true);
    
  }
  
  @Override
  public ActionForward delete(HttpServletRequest request) {
    
    // 삭제할 게시글 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("book_no"));
    int book_no = Integer.parseInt(opt.orElse("0"));
    
    // 삭제하기
    int deleteResult = dao.bookDelete(book_no);
    
    // 삭제 성공(deleteResult == 1), 삭제 실패(deleteResult == 0)
    String path = null;
    if(deleteResult == 1) {
      path = request.getContextPath() + "/book/list.do";
    } else if(deleteResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    // delete 이후에는 redirect 한다.
    return new ActionForward(path, true);
    
  }

  
}