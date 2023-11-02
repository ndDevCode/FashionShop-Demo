package model;

public class Orders {
	private String orderId;
	private String customerId;
	private String size;
	private double amount;
	private int orderQty;
	private int orderStatus;

	// ----- Constructors -------------------
	public Orders() {
	} // default constructor

	public Orders(String orderId, String customerId, String size, int orderQty, double amount) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.size = size;
		this.amount = amount;
		this.orderQty = orderQty;
		this.orderStatus = 1;
	}

	// ----Override the default toString method
	public String toString() {
		String orderDetails;
		String stat = "";
		switch (orderStatus) {
			case 1:
				stat = "Processing";
				break;
			case 2:
				stat = "Delivering";
				break;
			case 3:
				stat = "Delivered";
				break;
		}
		orderDetails = "\n\tPhone Number : " + customerId + "\n" +
				"\tSize         : " + size + "\n" +
				"\tQTY          : " + orderQty + "\n" +
				"\tAmount       : " + String.format("%.2f", amount) + "\n" +
				"\tStatus       : " + stat;

		return orderDetails;
	}

	private boolean isValidSize(String txt) {// check the entered size is valid
		String[] sizes = { "XS", "S", "M", "L", "XL", "XXL" };
		for (int i = 0; i < sizes.length; i++) {
			if (txt.equals(sizes[i])) {
				return true;
			}
		}
		return false;
	}

	private void setPrice() { // calculate the amount according to the size and quantity
		if (size.equals("XS")) {
			amount = orderQty * 600.0;
		} else if (size.equals("S")) {
			amount = orderQty * 800.0;
		} else if (size.equals("M")) {
			amount = orderQty * 900.0;
		} else if (size.equals("L")) {
			amount = orderQty * 1000.0;
		} else if (size.equals("XL")) {
			amount = orderQty * 1100.0;
		} else if (size.equals("XXL")) {
			amount = orderQty * 1200.0;
		}
	}

	// ----- Setters-------------------------
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean setCustomerId(String customerId) {
		if (customerId.charAt(0) == '0' && customerId.length() == 10) {
			this.customerId = customerId;
			return true;
		}
		return false;
	}

	public boolean setSize(String size) {
		if (isValidSize(size)) {
			this.size = size;
			return true;
		}
		return false;
	}

	public void setAmount() {
		setPrice();
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean setOrderQty(int orderQty) {
		if (orderQty > 0) {
			this.orderQty = orderQty;
			return true;
		}
		return false;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	// ----- Getters-------------------------

	public String getOrderId() {
		return orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getSize() {
		return size;
	}

	public double getAmount() {
		return amount;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

}
