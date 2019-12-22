package controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import entity.Address;
import entity.Category;
import entity.Customer;
import entity.CustomerOrder;
import entity.Product;
import entity.ProductDetail;
import session_bean.AddressSessionBean;
import session_bean.CategorySessionBean;
import session_bean.CustomerOrderSessionBean;
import session_bean.CustomerSessionBean;
import session_bean.OrderManager;
import session_bean.OrderedProductSessionBean;
import session_bean.ProductDetailSessionBean;
import session_bean.ProductSessionBean;
import valid.Validator;

/**
 * 
 * @author sanji
 *
 */
@WebServlet(name = "ControllerServlet", loadOnStartup = 1, urlPatterns = { "/ControllerServlet", "/category",
		"/addToCart", "/viewCart", "/updateCart", "/product", "/checkout", "/purchase" })
public class ControllerServlet extends HttpServlet {

	@EJB
	private CategorySessionBean categorySB;
	@EJB
	private ProductSessionBean productSB;
	@EJB
	private ProductDetailSessionBean productDetailSB;
	@EJB
	private OrderManager orderManager;
	@EJB
	private CustomerSessionBean customerSB;
	@EJB
	private OrderedProductSessionBean orderedProductSB;
	@EJB
	private CustomerOrderSessionBean customerOrderSB;
	@EJB
	private AddressSessionBean addressSB;
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		getServletContext().setAttribute("newProducts", productSB.findTop5Sale());
		getServletContext().setAttribute("categories", categorySB.findAll());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userPath = request.getRequestURI().substring(request.getContextPath().length());
		if (userPath.equals("/category")) {
			String categoryId = request.getQueryString();

			if (categoryId != null) {
				Category selectedCategory;
				List<Product> categoryProducts;
				selectedCategory = categorySB.find(Integer.parseInt(categoryId));
				session.setAttribute("selectedCategory", selectedCategory);
				categoryProducts = (List<Product>) selectedCategory.getProducts();
				session.setAttribute("categoryProducts", categoryProducts);
			}
		} else if (userPath.equals("/product")) {
			Product selectedProduct;
			ProductDetail selectedProductDetail;
			String productId = request.getQueryString();
			if (productId != null) {
				selectedProduct = productSB.find(Integer.parseInt(productId));
				selectedProductDetail = productDetailSB.find(Integer.parseInt(productId));
				session.setAttribute("selectedProduct", selectedProduct);
				session.setAttribute("selectedProductDetail", selectedProductDetail);
			}
		} else if (userPath.equals("/viewCart")) {
			String clear = request.getParameter("clear");
			if ((clear != null) && clear.equals("true")) {
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				cart.clear();
			}
		} else if (userPath.equals("/addToCart")) {
			// if user is adding item to cart for first time
			// create cart object and attach it to user session
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("cart", cart);
			}
			// get user input from request
			String productId = request.getQueryString();
			int index = productId.indexOf("&");
			if (index != -1) {
				productId = productId.substring(0, index);
			}
			if (!productId.isEmpty()) {
				Product product = productSB.find(Integer.parseInt(productId));
				cart.addItem(product);
			}
			String userView = (String) session.getAttribute("view");
			String search = (String) request.getQueryString();
			if (search.indexOf("=") != -1) {
				userPath = "resultSearch";
			} else {
				userPath = String.valueOf(userView);
			}
			

		} else if (userPath.equals("/checkout")) {
			String login = (String) session.getAttribute("login");
			if (login == null) {
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		Validator validator = new Validator();
		String userPath = request.getRequestURI().substring(request.getContextPath().length());

		if (userPath.equals("/updateCart")) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			cart = (ShoppingCart) session.getAttribute("cart");
			cart.update(productSB.find(productId), request.getParameter("quantity"));
			userPath = "/viewCart";
		} else if (userPath.equals("/purchase")) {
			if (cart != null) {
				Customer customer = (Customer) session.getAttribute("customer");
				int addressId = Integer.parseInt(request.getParameter("addressId"));
				Address address = addressSB.find(addressId);	
				boolean validationErrorFlag = false;
				validationErrorFlag = validator.validateForm(address.getAddress(), address.getCityRegion());
				if (!validationErrorFlag) {
					request.setAttribute("validationErrorFlag", validationErrorFlag);
					userPath = "checkout";
				} else {
					int orderId = orderManager.placeOrder(address.getAddress(), address.getCityRegion(), customer.getCustomerId(), cart);
					if (orderId != 0) {
						Locale locale = (Locale) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
						String language = "";
						if (locale != null) {
							language = (String) locale.getLanguage();
						}
						// dissociate shopping cart from session
						cart = null;
						// end session
						session.setAttribute("cart", null);
						List<CustomerOrder> customerOrderList = customerOrderSB.findByCustomer(customer);
						session.setAttribute("customerOrderList", customerOrderList);
						if (!language.isEmpty()) { //

							request.setAttribute("language", language); //

						}
						Map orderMap = orderManager.getOrderDetails(orderId);
						// place order details in request scope
						request.setAttribute("customer", orderMap.get("customer"));
						request.setAttribute("products", orderMap.get("products"));
						request.setAttribute("orderRecord", orderMap.get("orderRecord"));
						request.setAttribute("orderedProducts", orderMap.get("orderedProducts"));
						userPath = "/confirmation";

					} else {
						userPath = "/checkout";
						request.setAttribute("orderFailureFlag", true);
					}
				}
			}
		}
		String url = userPath + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
