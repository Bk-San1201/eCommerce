package controller;

import java.io.IOException;

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
 * Servlet implementation class UserServlet
 */
@WebServlet(name="/UserServlet", urlPatterns = {"/editProfile"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CustomerSessionBean customerSB;
       
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
