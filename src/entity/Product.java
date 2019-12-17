package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	private int productId;

	private String description;

	@Column(name="description_detail")
	private String descriptionDetail;

	private String image;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;

	private String name;

	private double price;

	@Column(name="thumb_image")
	private String thumbImage;

	//bi-directional many-to-one association to OrderedProduct
	@OneToMany(mappedBy="product")
	private List<OrderedProduct> orderedProducts;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	//bi-directional one-to-one association to ProductDetail
	@OneToOne(mappedBy="product", cascade={CascadeType.ALL})
	private ProductDetail productDetail;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionDetail() {
		return this.descriptionDetail;
	}

	public void setDescriptionDetail(String descriptionDetail) {
		this.descriptionDetail = descriptionDetail;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getThumbImage() {
		return this.thumbImage;
	}

	public void setThumbImage(String thumbImage) {
		this.thumbImage = thumbImage;
	}

	public List<OrderedProduct> getOrderedProducts() {
		return this.orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public OrderedProduct addOrderedProduct(OrderedProduct orderedProduct) {
		getOrderedProducts().add(orderedProduct);
		orderedProduct.setProduct(this);

		return orderedProduct;
	}

	public OrderedProduct removeOrderedProduct(OrderedProduct orderedProduct) {
		getOrderedProducts().remove(orderedProduct);
		orderedProduct.setProduct(null);

		return orderedProduct;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ProductDetail getProductDetail() {
		return this.productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

}