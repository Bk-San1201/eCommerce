package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Customer;
import entity.CustomerOrder;
import session_bean.CustomerOrderSessionBean;
import session_bean.CustomerSessionBean;
import session_bean.OrderManager;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "/AdminServlet", urlPatterns = { "/orderHistory", "/updateStatusOrder", "/userProfile" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CustomerOrderSessionBean customerOrderSB;
	@EJB
	private OrderManager orderManager;
	@EJB
	private CustomerSessionBean customerSB;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userPath = request.getRequestURI().substring(request.getContextPath().length());
		if (userPath.equals("/orderHistory")) {
			List<CustomerOrder> customerOrderHistories = customerOrderSB.findAll();
			Collections.reverse(customerOrderHistories);
			session.setAttribute("customerOrderHistories", customerOrderHistories);
			userPath = "orderHistory";
		} else if (userPath.equals("/userProfile")) {
			List<Customer> customers = customerSB.findAll();
			session.setAttribute("customers", customers);
			userPath = "userList";
		}
		String url = userPath.trim() + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userPath = request.getRequestURI().substring(request.getContextPath().length());
		if (userPath.equals("/updateStatusOrder")) {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			CustomerOrder customerOrder = customerOrderSB.find(orderId);
			int updateValue = Integer.parseInt(request.getParameter("status"));
			customerOrder.setStatus(updateValue);
			customerOrderSB.edit(customerOrder);
			List<CustomerOrder> customerOrderHistories = customerOrderSB.findAll();
			Collections.reverse(customerOrderHistories);
			session.setAttribute("customerOrderHistories", customerOrderHistories);
			userPath = "orderHistory";

			// Cập nhật số lượng sản phẩm
			if (updateValue == 2) {
				orderManager.updateQuantity(orderId);
			}

		}

		String url = userPath.trim() + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
