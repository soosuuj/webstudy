package ex09_up_down;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class Up_down
 */
@WebServlet("/upload")
public class Up_down extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Up_down() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   // 인코딩 -> 파일 첨부와 어울리지 않는 코드!!!
     // request.setCharacterEncoding("UTF-8");
    
     // String uploader = request.getParameter("uploader");
     // System.out.println(uploader);

	  /*
	   * 파일 첨부 선행 작업
	   * 1. http://servlets.com/ 접속
	   * 2. COS File Upload Library - cos-22.05.zip 다운로드
	   * 3. zip 압축 해제 후 lib/cos.jar 라이브러리 -> 프로젝트 webapp/WEB-INF/lib/cos.jar
	   */
	  
	  /*
	   * cos.jar
	   * 1. 업로드 전용 라이브러리이다.
	   * 2. 파일 첨부 폼 양식은 enctype="multipart/form-data"로 설정하는데 이렇게되면 HttpServletRequest를 사용할 수 없다.
	   * 3. HttpServletRequest 대신 사용할 MultipartRequest 클래스를 지원하는 라이브러리이다.
	   * 
	   */
	  
	  // 첨부 파일의 경로
	  String path="C:/storage";
	  
	  // 첨부 파일이 저장될 디렉터리가 없으면 새로 생성
	  File dir = new File(path);
	  if(!dir.exists()) {
	    dir.mkdirs();
	  }
	  
	  // MultipartRequest 객체 생성 (실제 업로드가 진행되는 코드)
	  MultipartRequest mr = new MultipartRequest(
	                                   request                            // 원래 사용하던 request
	                                 , path                               // 파일 첨부 경로
	                                 , 1024 * 1024 * 50                   // 첨부 파일의 최대 크기(50MB) 1024 
	                                 , "UTF-8"                            // 인코딩
	                                 , new DefaultFileRenamePolicy());    // 첨부 파일명이 중복되는 경우 첨부 파일의 이름을 바꾸는 기본 정책(파일명 뒤에 번호를 붙인다.)
	                             // 기본 파일 이름 바꾸기 정책을 쓰겠다. 
	                             // 기존 업로드 된 파일의 이름이 같은 경우 덮어쓰기 되어 사라질 수 있음.
	  
	  // 요청 파라미터(HttpServletRequest 대신 MultipartRequest mr을 이용한다.)
	  String uploader = mr.getParameter("uploader");
	  
	  // 첨부 파일
	  String originName = mr.getOriginalFileName("attach");    // 첨부 파일명
	  String filesystemName = mr.getFilesystemName("attach"); // 저장된 첨부 파일명, 올릴 때와 저장될 때 이름이 다름 -> 나중에 매우 다르게 할 것임

	  File attach = mr.getFile("attach");
	  String name = attach.getName();      // 첨부 파일명
	  String parent = attach.getParent();  // 첨부 파일 저장 디렉터리

	  // Uncomment these lines if needed
	  // String path = attach.getPath();      // 첨부 파일 저장 디렉터리 + 첨부 파일명
	  // long lastModified = attach.lastModified();  // 마지막 수정 시간
	  String lastModified = new SimpleDateFormat("yyyy-MM-dd").format(attach.lastModified());
	  String size = new DecimalFormat("#,##0").format(attach.length() / 1024 + (attach.length() % 1024 == 0 ? 0 : 1));   // attach.length() 바이트 단위 /1024 키로 바이트
	  
	  // 응답 -> git 확인하기
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.println("<ul>");
	  out.println("<li>업로더:" + uploader +"</li>");
	  out.println("<li>첨부파일명: " + name + "</li>");
	  out.println("<li>저장된 첨부 파일명 : " + filesystemName + "</li>");
	  out.println("<li>저장된 첨부 파일명 : " + filesystemName + "</li>");
	  out.println("<li>저장된 디렉터리: " + parent + "</li>");
	  out.println("<li>최종수정일: " + lastModified + "</li>");
	  out.println("<li>파일크기: " + size + "KB</li>");
	  out.println("</ui>");
	  out.println("<hr>");
	  
	  // 이곳에 첨부된 모든 파일의 목록을 보여주세요!  
	  File[] attaches = dir.listFiles();
	  for(int i = 0; i < attaches.length; i++ ) {
	    out.println("<div><a href=\"/servlet/download?path="+ URLEncoder.encode(attaches[i].getPath(), "UTF-8") +"\">" + attaches[i].getName() + "</a></div>");
	  }
	  
	  out.flush();
	  out.close();

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
