package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClientDAO;
import model.ClientDTO;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String clientId = request.getParameter("clientId");
		String clientPassword = request.getParameter("clientPassword");
		
		ClientDTO clientDTO = new ClientDTO(clientId, clientPassword, null, null);
		ClientDAO clientDAO = new ClientDAO();
		
		int result = clientDAO.loginCheck(clientDTO);
		if(result == 1) {			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원탈퇴에 성공하였습니다.')");
			script.println("location.href='/MVC_Board/main?'");
			script.println("</script>");
			session.invalidate();
			ClientDAO.getInstance().deleteClient(clientId);
		}else if(result == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 올바르지 않습니다!')");
			script.println("history.back()");
			script.println("</script>");
		}else if(result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류 발생')");
			script.println("history.back()");
			script.println("</script>");
		}
		
	}

}
