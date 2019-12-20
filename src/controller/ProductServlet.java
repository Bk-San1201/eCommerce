package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Category;
import entity.Product;
import entity.ProductDetail;
import session_bean.CategorySessionBean;
import session_bean.ProductDetailSessionBean;
import session_bean.ProductSessionBean;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(name = "/ProductServlet", urlPatterns = { "/deleteProduct", "/addProduct", "/editProduct" , "/search"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CategorySessionBean categorySB;
	@EJB
	private ProductSessionBean productSB;
	@EJB
	private ProductDetailSessionBean productDetailSB;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
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
		if (userPath.equals("/deleteProduct")) {
//			HttpSession session = request.getSession();
			Product selectedProduct = (Product) session.getAttribute("selectedProduct");
			ProductDetail selectedProductDetail = (ProductDetail) session.getAttribute("selectedProductDetail");
			session.removeAttribute("selectedProduct");
			session.removeAttribute("selectedProductDetail");
			productDetailSB.remove(productDetailSB.find(selectedProduct.getProductId()));
			productSB.remove(productSB.find(selectedProduct.getProductId()));
//			productSB.deleteProduct(selectedProduct.getProductId());

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
		if (userPath.equals("/addProduct")) {
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			HttpSession session = request.getSession();
			Product selectedProduct = (Product) session.getAttribute("selectedProduct");
			ProductDetail selectedProductDetail = (ProductDetail) session.getAttribute("selectedProductDetail");

			// get infor from request
			String imageProduct = (String) request.getParameter("imageProduct");
			String[] imageProductDetail = new String[5];
			for (int i = 0; i < 5; i++) {
				imageProductDetail[i] = (String) request.getParameter("imageProductDetail_" + (i + 1));
			}
			int productId;
			String name = (String) request.getParameter("name");
			String description = (String) request.getParameter("description");
			String description_detail = (String) request.getParameter("description_detail");
			int category_id = Integer.parseInt(request.getParameter("category_id"));
			int day = Integer.parseInt(request.getParameter("day"));
			int month = Integer.parseInt(request.getParameter("month"));
			int year = Integer.parseInt(request.getParameter("year"));
			@SuppressWarnings("deprecation")
			Date lastUpdate = new Date(year - 1900, month - 1, day);
			double price = Double.parseDouble(request.getParameter("price"));
			String techniqueDetail = (String) request.getParameter("techniqueDetail");
			String accessories = (String) request.getParameter("accessories");
			String guaranty = (String) request.getParameter("guaranty");

			if (selectedProduct != null) {
				productId = selectedProduct.getProductId();
				session.removeAttribute("selectedProduct");
				session.removeAttribute("selectedProductDetail");
				Product p = productSB.find(productId);
				ProductDetail pd = productDetailSB.find(productId);
				productDetailSB.remove(pd);
				productSB.remove(p);
			} else {
				List<Product> product = productSB.findAll();
				productId = product.size() + 1;
			}

			Product p = new Product();
			ProductDetail pd = new ProductDetail();
			Category category = categorySB.find(category_id);
			// set for product
			p.setProductId(productId);
			p.setName(name);
			p.setImage(imageProduct);
			p.setCategory(category);
			p.setDescription(description);
			p.setDescriptionDetail(description_detail);
			p.setLastUpdate(lastUpdate);
			p.setPrice(price);
			productSB.create(p);
			// set for product detail

			pd.setProductId(productId);
			pd.setImage1(imageProductDetail[0]);
			pd.setImage2(imageProductDetail[1]);
			pd.setImage3(imageProductDetail[2]);
			pd.setImage4(imageProductDetail[3]);
			pd.setImage5(imageProductDetail[4]);
			pd.setAccessories(accessories);
			pd.setGuaranty(guaranty);
			pd.setInformation(description_detail);
			
			productDetailSB.create(pd);
			session.setAttribute("selectedProduct", p);
			session.setAttribute("selectedProductDetail", pd);
//			out.print("<script type=\"text/javascript\">\r\n" + 
//					"		alert('Add product successfully!');\r\n" + 
//					"	</script>");
//			categorySB.getEntityManager().refresh(p.getCategory());
//			request.getRequestDispatcher("product.jsp").forward(request, response);

			userPath = "product";
		} else if (userPath.equals("/editProduct")) {
			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			HttpSession session = request.getSession();
			Product selectedProduct = (Product) session.getAttribute("selectedProduct");
			ProductDetail selectedProductDetail = (ProductDetail) session.getAttribute("selectedProductDetail");

			// get infor from request
			String imageProduct = (String) request.getParameter("imageProduct");
			if (imageProduct == null) {
				imageProduct = new String(String.valueOf(selectedProduct.getImage()));
			}
			String[] imageProductDetail = new String[5];
			for (int i = 0; i < 5; i++) {
				imageProductDetail[i] = (String) request.getParameter("imageProductDetail_" + (i + 1));
				if (imageProductDetail[i] == null) {
					imageProductDetail[i] = new String(String.valueOf(selectedProductDetail.getAllImages().get(i)));
				}
			}
			int productId;
			String name = (String) request.getParameter("name");
			String description = (String) request.getParameter("description");
			String description_detail = (String) request.getParameter("description_detail");
			int category_id = Integer.parseInt(request.getParameter("category_id"));
			int day = Integer.parseInt(request.getParameter("day"));
			int month = Integer.parseInt(request.getParameter("month"));
			int year = Integer.parseInt(request.getParameter("year"));
			@SuppressWarnings("deprecation")
			Date lastUpdate = new Date(year - 1900, month - 1, day);
			double price = Double.parseDouble(request.getParameter("price"));
			String techniqueDetail = (String) request.getParameter("techniqueDetail");
			String accessories = (String) request.getParameter("accessories");
			String guaranty = (String) request.getParameter("guaranty");

			productId = selectedProduct.getProductId();

			Product p = new Product();
			ProductDetail pd = new ProductDetail();
			Category category = categorySB.find(category_id);
			// set for product
			p.setProductId(productId);
			p.setName(name);
			p.setImage(imageProduct);
			p.setCategory(category);
			p.setDescription(description);
			p.setDescriptionDetail(description_detail);
			p.setLastUpdate(lastUpdate);
			p.setPrice(price);

			// set for product detail

			pd.setProductId(productId);
			pd.setImage1(imageProductDetail[0]);
			pd.setImage2(imageProductDetail[1]);
			pd.setImage3(imageProductDetail[2]);
			pd.setImage4(imageProductDetail[3]);
			pd.setImage5(imageProductDetail[4]);
			pd.setAccessories(accessories);
			pd.setGuaranty(guaranty);
			pd.setInformation(description_detail);
			pd.setInformation(techniqueDetail);
			productDetailSB.remove(selectedProductDetail);
			productSB.remove(selectedProduct);
			productSB.create(p);
			productDetailSB.create(pd);
			session.setAttribute("selectedProduct", p);
			session.setAttribute("selectedProductDetail", pd);
			userPath = "product";
		}
		String url = userPath.trim() + ".jsp";
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		HttpSession session = request.getSession();

	}

}
