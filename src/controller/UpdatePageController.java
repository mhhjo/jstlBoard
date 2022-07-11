package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClientDAO;
import model.ClientDTO;

/**
 * Servlet implementation class UpdatePageController
 */
@WebServlet("/updatePage")
public class UpdatePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePageController() {
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
		HttpSession session = request.getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("clientDTO");
		
		ClientDAO clientDAO = ClientDAO.getInstance();
		ClientDTO clientDTOinfo = clientDAO.getClientInfo(clientDTO.getClientId());
		
		request.setAttribute("clientDTOinfo", clientDTOinfo);
		request.getRequestDispatcher("/?subpage=update").forward(request, response);
	}

}
