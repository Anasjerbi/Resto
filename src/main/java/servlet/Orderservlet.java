package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.OrderDAO;
import models.Order;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the .
 * @email Ramesh Fadatare
 */

@WebServlet("/")
public class Orderservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO OrderDAO;
	
	public void init() {
		OrderDAO = new OrderDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertOrder(request, response);
				break;
			case "/delete":
				deleteOrder(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateOrder(request, response);
				break;
			default:
				listOrder(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Order> listOrder = OrderDAO.selectAllOrders();
		request.setAttribute("listOrder", listOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("liste.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Order existingOrder = OrderDAO.selectOrder(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		request.setAttribute("order", existingOrder);
		dispatcher.forward(request, response);

	}

	private void insertOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String description = request.getParameter("description");
		Order newOrder = new Order(name, email, description);
		OrderDAO.insertOrder(newOrder);
		response.sendRedirect("liste");
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String description = request.getParameter("description");

		Order book = new Order(id, name, email, description);
		OrderDAO.updateOrder(book);
		response.sendRedirect("list");
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		OrderDAO.deleteOrder(id);
		response.sendRedirect("list");

	}


}
