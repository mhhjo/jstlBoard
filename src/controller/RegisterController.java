package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClientDAO;
import model.ClientDTO;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public RegisterController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 글자 깨짐 방지 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String clientId = request.getParameter("clientId");
		String clientName = request.getParameter("clientName");
		String clientEmail = request.getParameter("clientEmail");
		String clientPassword = request.getParameter("clientPassword");
		
		ClientDTO clientDTO = new ClientDTO(clientId, clientPassword, clientName, clientEmail);
		ClientDAO clientDAO = new ClientDAO();
		
		int result = clientDAO.register(clientDTO);
		clientDAO.close();
		
		if(result > 0) {
			System.out.println("회원가입 성공");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원 등록에 성공하였습니다!')");
			script.println("location.href='/MVC_Board/?subpage=login'");
			script.println("</script>");
		}else {
			System.out.println("회원가입 실패, result값 ="+result);
			response.sendRedirect("/MVC_Board/?subpage=register");
			
		}
	}
	
}
