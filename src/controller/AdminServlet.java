package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Address;
import entity.Category;
import entity.Customer;
import entity.CustomerOrder;
import session_bean.AddressSessionBean;
import session_bean.CategorySessionBean;
import session_bean.CustomerOrderSessionBean;
import session_bean.CustomerSessionBean;
import session_bean.OrderManager;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "/AdminServlet", urlPatterns = { "/orderHistory", "/updateStatusOrder", "/userProfile", "/customer","/categoryAdmin",
		"/addCategory", "/deleteCategory"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CustomerOrderSessionBean customerOrderSB;
	@EJB
	private OrderManager orderManager;
	@EJB
	private CustomerSessionBean customerSB;
	@EJB
	private AddressSessionBean addressSB;
	@EJB
	private CategorySessionBean categorySB;

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
		} else if (userPath.contentEquals("/customer")) {
			String customerId = request.getQueryString();
			Customer customer = customerSB.find(Integer.parseInt(customerId));
			List<CustomerOrder> customerOrderList = customerOrderSB.findByCustomer(customer);
			List<Address> addressbook = addressSB.findByCustomer(customer);
			
			request.setAttribute("customer", customer);
			request.setAttribute("customerOrderList", customerOrderList);
			request.setAttribute("addressbook", addressbook);
			userPath = "profileAdmin";
		} else if (userPath.contentEquals("/categoryAdmin")) {
			requestCategory(request);
			userPath = "categoryAdmin";
		} else if (userPath.contentEquals("/deleteCategory")) {
			String categoryId = request.getParameter("categoryId");
			Category category = categorySB.find(Integer.parseInt(categoryId));
			if (category.getProducts().size() == 0) {
				categorySB.remove(category);
			} else {
				request.setAttribute("notDelete", "Ok");
			}
			requestCategory(request);
			userPath = "categoryAdmin";
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

		} else if (userPath.contentEquals("/addCategory")) {
			String name = request.getParameter("category");
			String image = request.getParameter("image");
			int categoryId = categorySB.findAll().size() + 1;
			Category category = new Category(categoryId, image, name);
			categorySB.create(category);
			requestCategory(request);
			userPath = "categoryAdmin";
		}

		String url = userPath.trim() + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void requestCategory(HttpServletRequest request) {
		List<Category> categories = categorySB.findAll();
		List<Integer> quantity = new ArrayList<Integer>();
		List<Integer> numtype = new ArrayList<Integer>();
		for (Category category : categories) {
			quantity.add((Integer) categorySB.calQuantity(category));
			numtype.add((Integer) category.getProducts().size());
		}
		request.getServletContext().setAttribute("categories", categories);
		request.setAttribute("quantity", quantity);
		request.setAttribute("numtype", numtype);
		
	}

}
