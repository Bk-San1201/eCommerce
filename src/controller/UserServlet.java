package controller;

import java.io.IOException;
import java.util.Map;

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
import session_bean.OrderedProductSessionBean;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name="/UserServlet", urlPatterns = {"/editProfile", "/orderDetail"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CustomerSessionBean customerSB;
    @EJB 
    private CustomerOrderSessionBean customerOrderSB;
    @EJB
    private OrderedProductSessionBean orderedProductSB;
    @EJB
    private OrderManager orderManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userPath = request.getRequestURI().substring(request.getContextPath().length());
		if (userPath.contentEquals("/orderDetail")) {
			String orderId = request.getQueryString();
			CustomerOrder customerOrder = customerOrderSB.find(Integer.parseInt(orderId));
			Map orderMap = orderManager.getOrderDetails(Integer.parseInt(orderId));
			// place order details in request scope
			request.setAttribute("customer", orderMap.get("customer"));
			request.setAttribute("products", orderMap.get("products"));
			request.setAttribute("orderRecord", orderMap.get("orderRecord"));
			request.setAttribute("orderedProducts", orderMap.get("orderedProducts"));
			userPath = "orderDetail";
		}
		String url = userPath.trim() + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userPath = request.getRequestURI().substring(request.getContextPath().length());
		if (userPath.contentEquals("/editProfile")) {
			Customer customer = (Customer) session.getAttribute("customer");
			
			customer.setName(request.getParameter("name"));
			customer.setEmail(request.getParameter("email"));
			customer.setPhone(request.getParameter("phone"));
			customer.setAddress(request.getParameter("address"));
			customer.setCityRegion(request.getParameter("cityregion"));
			customer.setCcNumber(request.getParameter("ccNumber"));
			
			customerSB.edit(customer);
			userPath = "profile";
		}
		String url = userPath.trim() + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
