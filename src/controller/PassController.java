package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import FileUtil.FileUtil;
import model.BoardDAO;
import model.BoardDTO;
import utils.JSFunction;

@WebServlet("/pass")
public class PassController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException{
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/?subpage=pass").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException{
		// 매개 변수 저장
		String no = req.getParameter("no");
		String mode = req.getParameter("mode");
		String clientPassword = req.getParameter("clientPassword");
		
		// 비밀번호 체크
		BoardDAO boardDAO = new BoardDAO();
		boolean confirmed = boardDAO.confirmPassword(clientPassword, no);
		boardDAO.close();
		
		if(confirmed) { // confirmed 값 true
			if(mode.equals("edit")) { // 수정 mode
//				HttpSession session = req.getSession();
//				session.setAttribute("clientPassword", clientPassword);
				resp.sendRedirect("/MVC_Board/edit?no="+no);
				
			}else if(mode.equals("delete")) { // 삭제 mode
				boardDAO = new BoardDAO();
				BoardDTO boardDTO = boardDAO.selectView(no);
				int result = boardDAO.deletePost(no); // 게시물 삭제 query
				boardDAO.close();
				
				if(result == 1) { // 게시물 삭제 성공 시 첨부파일도 삭제
					String saveFileName = boardDTO.getSfile();
					FileUtil.deleteFile(req, "/Uploads", saveFileName);
				}
				JSFunction.alertLocation(resp, "삭제 되었습니다!", "/MVC_Board/main?");
				
			}else { // 비밀번호 불일치
				JSFunction.alertBack(resp, "비밀번호를 다시 확인해 주세요!");
			}
		}
	}
}
