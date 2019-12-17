package cart;

import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ShoppingCart {
	List<ShoppingCartItem> items;
	int numberOfItems;
	double total;

	public ShoppingCart() {
		items = new ArrayList<ShoppingCartItem>();
		numberOfItems = 0;
		total = 0;
	}

	public synchronized void addItem(Product product) {
		boolean newItem = true;
		for (ShoppingCartItem scItem : items) {
			if (scItem.getProduct().getProductId() == product.getProductId()) {
				newItem = false;
				scItem.incrementQuantity();
			}
		}
		if (newItem) {
			ShoppingCartItem scItem = new ShoppingCartItem(product);
			items.add(scItem);
		}
	}

	public synchronized void update(Product product, String quantity) {
		short qty = -1;
		// cast quantity as short
		qty = Short.parseShort(quantity);
		if (qty >= 0) {
			ShoppingCartItem item = null;
			for (ShoppingCartItem scItem : items) {
				if (scItem.getProduct().getProductId() == product.getProductId()) {
					if (qty != 0) {
						// set item quantity to new value
						scItem.setQuantity(qty);
					} else {
						// if quantity equals 0, save item and break
						item = scItem;
						break;
					}
				}
			}
			if (item != null) {
				// remove from cart
				items.remove(item);
			}
		}
	}

	public synchronized List<ShoppingCartItem> getItems() {
		return items;
	}

	public synchronized int getNumberOfItems() {
		numberOfItems = 0;
		for (ShoppingCartItem scItem : items) {
			numberOfItems += scItem.getQuantity();
		}
		return numberOfItems;
	}

	public synchronized double getSubtotal() {
		double amount = 0;
		for (ShoppingCartItem scItem : items) {
			Product product = (Product) scItem.getProduct();
			amount += (scItem.getQuantity() * product.getPrice());
		}
		return amount;
	}

	public synchronized void calculateTotal(String surcharge) {
		double amount = 0;
		// cast surcharge as double
		double s = Double.parseDouble(surcharge);
		amount = this.getSubtotal();
		amount += s;
		total = amount;
	}

	public synchronized double getTotal() {
		return total;
	}

	public synchronized void clear() {
		items.clear();
		numberOfItems = 0;
		total = 0;
	}

}
