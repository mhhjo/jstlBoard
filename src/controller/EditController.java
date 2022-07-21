package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import FileUtil.FileUtil;
import model.BoardDAO;
import model.BoardDTO;
import utils.JSFunction;

@WebServlet("/edit")
public class EditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String no = req.getParameter("no");
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = boardDAO.selectView(no);
		
		req.setAttribute("boardDTO", boardDTO);
		req.getRequestDispatcher("/?subpage=edit").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		// 1. 파일 업로드 처리
		//// 업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		
		//// 초기화 매개 변수로 설정한 첨부 파일 최대 용량 확인
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		//// 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		
		if(mr == null) {
			// 파일 업로드 실패
			JSFunction.alertBack(resp, "첨부 파일이 제한 용량을 초과합니다.");
			return;
		}
		
		// 2. 파일 업로드 외 처리
		//// 수정 내용을 매개 변수에서 얻어온다
		String no = mr.getParameter("no");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		
		String clientId = mr.getParameter("clientId");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		//// 비밀번호는 session에서 가져온다
		HttpSession session = req.getSession();
//		String clientPassword = (String)session.getAttribute("clientPassword");
		
		//// DTO 저장
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNo(no);
		boardDTO.setClientId(clientId);
		boardDTO.setTitle(title);
		boardDTO.setContent(content);
//		boardDTO.setClientPassword(clientPassword);
		
		//// 원본 파일명과 저장된 파일 이름 설정
		String fileName = mr.getFilesystemName("ofile");
		if(fileName != null) {
			// 첨부파일이 있을 경우 파일명 변경
			// 새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			// 파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + fileName);
			
			// DTO 저장
			boardDTO.setOfile(fileName); // 원 파일 명
			boardDTO.setSfile(newFileName); // 서버에 저장되는 파일 명
			
			// 기존 파일 삭제 처리
			FileUtil.deleteFile(req, "/Uploads", prevSfile);
		}else {
			// 첨부 파일 없을 시엔 기존 파일 명 유지
			boardDTO.setOfile(prevOfile);
			boardDTO.setSfile(prevSfile);
		}
		
		//// DB에 수정 내용 반영
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.updatePost(boardDTO);
		boardDAO.close();
		
		//// 성공 or 실패
		if(result == 1) { // 수정 성공
			session.removeAttribute("clientPassword");
			resp.sendRedirect("");
		}else { // 수정 실패
			JSFunction.alertLocation(resp, "비밀번호를 다시 확인해 주세요", "MVC_Board/view?no="+no);
		}
	}
}
