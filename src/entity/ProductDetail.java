package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * The persistent class for the product_detail database table.
 * 
 */
@Entity
@Table(name="product_detail")
@NamedQuery(name="ProductDetail.findAll", query="SELECT p FROM ProductDetail p")
public class ProductDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	private int productId;

	private String accessories;

	private String guaranty;

	private String image1;

	private String image2;

	private String image3;

	private String image4;

	private String image5;

	private String information;

	private int quantity;

	//bi-directional one-to-one association to Product
	@OneToOne(cascade={CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private Product product;

	public ProductDetail() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getAccessories() {
		return this.accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getGuaranty() {
		return this.guaranty;
	}

	public void setGuaranty(String guaranty) {
		this.guaranty = guaranty;
	}

	public String getImage1() {
		return this.image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return this.image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return this.image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return this.image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getImage5() {
		return this.image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<String> getAllImages() {
		List<String> images = new ArrayList<String>();
		if (image1 != null)
			images.add(image1);
		if (image2 != null)
			images.add(image2);
		if (image3 != null)
			images.add(image3);
		if (image4 != null)
			images.add(image4);
		if (image5 != null)
			images.add(image5);
		return images;
	}

}