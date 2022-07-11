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
 * Servlet implementation class UpdateController
 */
@WebServlet("/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String clientId = request.getParameter("clientId");
		String clientPassword = request.getParameter("clientPassword");
		String clientName = request.getParameter("clientName");
		String clientEmail = request.getParameter("clientEmail");
		
		ClientDTO clientDTO = new ClientDTO(clientId, clientPassword, clientName, clientEmail);
		ClientDAO clientDAO = new ClientDAO();
		int result = clientDAO.updateClientInfo(clientDTO);
		if(result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원정보가 변경되었습니다!')");
			script.println("history.back()");
			script.println("</script>");
			HttpSession session = request.getSession();
			session.setAttribute("clientDTO", clientDTO);
		}else if(result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류 발생')");
			script.println("history.back()");
			script.println("</script>");
		}
	}

}
