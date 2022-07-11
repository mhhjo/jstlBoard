package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import FileUtil.FileUtil;
import model.BoardDAO;
import model.BoardDTO;
import utils.JSFunction;

/**
 * Servlet implementation class WriteController
 */
@WebServlet("/write")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/write.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 글자 깨짐 방지 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		/* 파일업로드 먼저 실행*/
		String saveDirectory=request.getServletContext().getRealPath("/Uploads");
		ServletContext application=getServletContext();
		//책에서는 web.xml에서 가져왔으나 여기서는 직접 설정.
		int maxPostSize=1024*1024*10;//10MB
		MultipartRequest mr=FileUtil.uploadFile(request,saveDirectory,maxPostSize);
		if(mr==null) {
			JSFunction.alertLocation(response,"파일업로드 실패","MVC_Board/?subpage=mainPage");
			return; //파일업로드실패시 중지
		}
		/* DB에 insert */
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setClientId(mr.getParameter("clientid"));
		boardDTO.setTitle(mr.getParameter("title"));
		boardDTO.setContent(mr.getParameter("content"));
		
		
		String fileName=mr.getFilesystemName("ofile");
		
		if(fileName!=null) {
			//현재시각구하기. 밀리세컨드까지
			String now=new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext=fileName.substring(fileName.lastIndexOf("."));//확장자구하기.예) .doc
			String newFileName=now+ext;//저장할 파일명생성. 중복방지를 위해서.
			File oldFile=new File(saveDirectory+File.separator+fileName);//원본파일경로
			//System.out.println(saveDirectory+File.separator+fileName);	
			File newFile=new File(saveDirectory+File.separator+newFileName);//새저장파일경로
			//System.out.println(saveDirectory+File.separator+fileName);
			oldFile.renameTo(newFile);//파일명변경
			
			boardDTO.setOfile(fileName);
			boardDTO.setSfile(newFileName);
		}
		
		BoardDAO boardDAO = new BoardDAO();
		int result=boardDAO.insertWrite(boardDTO);
		boardDAO.close();
		if(result==1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('글 작성이 성공하였습니다!')");
			script.println("location.href='/MVC_Board/main?'");
			script.println("</script>");
		}else {
			response.sendRedirect(request.getContextPath()+"/?subpage=write");
		}
		
	}

}
