package model;

public class productModel {
	private String productName;
	private String productDesc;
	private int mrp;
	private int discountPrice;
	private int stock;
	private String imageUrl;
	private String category;
	private int productId;

	public productModel(int productId, String productName, String productDesc, int mrp, int discountPrice, int stock,
			String imageUrl, String category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.mrp = mrp;
		this.discountPrice = discountPrice;
		this.stock = stock;
		this.imageUrl = imageUrl;
		this.category = category;
	}

	public productModel(String productName, String productDesc, int mrp, int discountPrice, int stock, String imageUrl,
			String category) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.mrp = mrp;
		this.discountPrice = discountPrice;
		this.stock = stock;
		this.imageUrl = imageUrl;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getMrp() {
		return mrp;
	}

	public void setMrp(int mrp) {
		this.mrp = mrp;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}