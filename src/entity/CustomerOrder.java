package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer_order database table.
 * 
 */
@Entity
@Table(name="customer_order")
@NamedQuery(name="CustomerOrder.findAll", query="SELECT c FROM CustomerOrder c")
public class CustomerOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_id", unique=true, nullable=false)
	private int orderId;

	@Column(length=255)
	private String address;

	@Column(precision=10, scale=5)
	private BigDecimal amount;

	@Column(name="city_region", length=255)
	private String cityRegion;

	@Column(name="confirmation_number")
	private int confirmationNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="date_created")
	private Date dateCreated;

	private int status;

	//bi-directional many-to-one association to Customer
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to OrderedProduct
	@OneToMany(mappedBy="customerOrder")
	private List<OrderedProduct> orderedProducts;

	public CustomerOrder() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCityRegion() {
		return this.cityRegion;
	}

	public void setCityRegion(String cityRegion) {
		this.cityRegion = cityRegion;
	}

	public int getConfirmationNumber() {
		return this.confirmationNumber;
	}

	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderedProduct> getOrderedProducts() {
		return this.orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public OrderedProduct addOrderedProduct(OrderedProduct orderedProduct) {
		getOrderedProducts().add(orderedProduct);
		orderedProduct.setCustomerOrder(this);

		return orderedProduct;
	}

	public OrderedProduct removeOrderedProduct(OrderedProduct orderedProduct) {
		getOrderedProducts().remove(orderedProduct);
		orderedProduct.setCustomerOrder(null);

		return orderedProduct;
	}

}