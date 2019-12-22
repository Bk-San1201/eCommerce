package entity;

import java.io.Serializable;
import javax.persistence.*;

import entity.Customer;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQueries({
	@NamedQuery(name="Address.findByCustomer", query="SELECT c FROM Address c WHERE c.customer = :customer"),
	@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
})

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="address_id", unique=true, nullable=false)
	private int addressId;

	@Column(length=255)
	private String address;

	@Column(name="city_region", length=255)
	private String cityRegion;

	@Column(length=225)
	private String phone;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="user_id")
	private Customer customer;

	public Address() {
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityRegion() {
		return this.cityRegion;
	}

	public void setCityRegion(String cityRegion) {
		this.cityRegion = cityRegion;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}