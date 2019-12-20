package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Customer;
import session_bean.CustomerSessionBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "/LoginServlet", urlPatterns = { "/login", "/logout", "/register" })
public class LoginServlet extends HttpServlet {
	@EJB
	private CustomerSessionBean customerSB;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		if (userPath.equals("/logout")) {
			session.removeAttribute("login");
			session.removeAttribute("check");
			session.removeAttribute("customer");
			userPath = "index";
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
		if (userPath.equals("/login")) {
			String name = request.getParameter("username");
			String password = request.getParameter("pass");
			String check = new String("WP");
			if (name.equals("admin")) {
				if (password.equals("admin")) {
					session.setAttribute("login", name);
					userPath = "index";
				}
			} else if (isUser(name, password)) {
				session.setAttribute("login", name);
				Customer customer = customerSB.findByUsername(name);
				session.setAttribute("customer", customer);
				userPath = "index";
			} else {
				session.setAttribute("check", check);
				userPath = "login";
			}
		} else if (userPath.contentEquals("/register")) {
			String username = request.getParameter("username");
			if (isUserExist(username)) {
				session.setAttribute("username_exist", "OK");
				userPath = "register";
			} else {
				Customer customer = new Customer();
				
				customer.setCustomerId(customerSB.findAll().size() + 1);
				customer.setName(request.getParameter("fullName"));
				customer.setUsername(request.getParameter("username"));
				customer.setAddress(request.getParameter("address"));
				customer.setCityRegion(request.getParameter("cityregion"));
				customer.setEmail(request.getParameter("email"));
				customer.setPassword(request.getParameter("password"));
				customer.setCcNumber(request.getParameter("ccNumber"));
				customer.setPhone(request.getParameter("phone"));
				
				customerSB.create(customer);
				userPath = "login";
			}
		}
		String url = userPath.trim() + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private boolean isUser(String name, String password) {
		boolean res = false;
		List<Customer> cus = customerSB.findAll();
		for (Customer c : cus) {
			if (c.getUsername().contentEquals(name) && c.getPassword().contentEquals(password)) {
				res = true;
				break;
			}
		}
		return res;
	}
	private boolean isUserExist(String username) {
		boolean res = false;
		List<Customer> cus = customerSB.findAll();
		for (Customer c : cus) {
			if (c.getUsername().contentEquals(username)) {
				res = true;
				break;
			}
		}
		return res;
	}

}
