package cm.enspd.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cm.enspd.dao.DiamondDao;
import cm.enspd.models.Diamond;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DiamondServlet
 */
@WebServlet("/")
public class DiamondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DiamondDao DiamondDao;
	
	public void init() {
		DiamondDao= new DiamondDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiamondServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
				
			case "/insert":
				insertDiamond(request, response);
				break;
				
			case "/delete":
				deleteDiamond(request, response);
				break;
				
			case "/edit":
				showEditForm(request, response);
				break;
				
			case "/update":
				showEditForm(request, response);
				break;
				
			default:
				listDiamond(request, response);
				break;
			}
		} catch (SQLException e) {

			throw new ServletException(e);
		}
	}

		private void listDiamond(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
			List<Diamond> listDiamond = DiamondDao.selectAllDiamonds();
			request.setAttribute("listDiamond", listDiamond);
			RequestDispatcher dispatcher = request.getRequestDispatcher("diamond-list.jsp");
			dispatcher.forward(request, response);
			
		}
		
		private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("diamond-form.jsp");
			dispatcher.forward(request, response);
			
		}
		
		private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
			int id = Integer.parseInt(request.getParameter("id"));
			Diamond existingDiamond = DiamondDao.selectDiamond(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("diamond-form.jsp");
			request.setAttribute("Diamond", existingDiamond);
			dispatcher.forward(request, response);
			
		}
		
	private void insertDiamond(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException{
			String designation = request.getParameter("designation");
			int weight = Integer.parseInt(request.getParameter("weight"));
			int price = Integer.parseInt(request.getParameter("price"));
			
			Diamond newDiamond = new Diamond(designation, weight, price);
			DiamondDao.insertDiamond(newDiamond);
			response.sendRedirect("list");
		}
	
	private void deleteDiamond(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		DiamondDao.deleteDiamond(id);
		response.sendRedirect("list");
	}
		
	private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		String designation = request.getParameter("designation");
		int weight = Integer.parseInt(request.getParameter("weight"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		Diamond updatedDiamond = new Diamond(id, designation, weight, price);
		DiamondDao.updateDiamond(updatedDiamond);
		response.sendRedirect("/list");
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
