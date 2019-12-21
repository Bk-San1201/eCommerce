package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ordered_product database table.
 * 
 */
@Entity
@Table(name="ordered_product")
@NamedQueries({
    @NamedQuery(name = "OrderedProduct.findAll", query = "SELECT o FROM OrderedProduct o"),
    @NamedQuery(name = "OrderedProduct.findByOrderId", query = "SELECT o FROM OrderedProduct o WHERE o.id.orderId = :orderId"),
    @NamedQuery(name = "OrderedProduct.findByProductId", query = "SELECT o FROM OrderedProduct o WHERE o.id.productId = :productId"),
    @NamedQuery(name = "OrderedProduct.findByQuantity", query = "SELECT o FROM OrderedProduct o WHERE o.quantity = :quantity")})
public class OrderedProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderedProductPK id;

	private int quantity;

	//bi-directional many-to-one association to CustomerOrder
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="order_id")
	private CustomerOrder customerOrder;

	//bi-directional many-to-one association to Product
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="product_id")
	private Product product;

	public OrderedProduct() {
	}

	public OrderedProduct(OrderedProductPK orderedProductPK) {
		this.id = orderedProductPK;
		
	}
	
	public OrderedProductPK getId() {
		return this.id;
	}

	public void setId(OrderedProductPK id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}