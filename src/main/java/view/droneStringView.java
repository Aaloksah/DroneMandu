package view;

public class droneStringView {
	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/dronedb";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	
	// End: DB Connection
	//Start SQL Queries
	public static final String INSERT_USER = "INSERT INTO useraccount"+"(fullName, email,password,gender,phone,province,city,userName,street )" +"VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
	public static final String UPDATE_USER = "UPDATE useraccount SET fullName = ?, email = ?, gender = ?, province = ?, phone = ?, city = ?, street = ? WHERE userName = ?";
	public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM useraccount WHERE userName = ? LIMIT 1";
	
	public static final String GET_LOGIN_USER_INFO = "SELECT * FROM register WHERE email=? and password =?";
	
	public static final String QUERY_LOGIN_USER_INFO= "SELECT * FROM useraccount WHERE userName = ? AND password = ?";
	public static final String GET_ALL_USER_INFO = "SELECT * FROM useraccount";  
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM  useraccount WHERE userName= ? ";
	public static final String GET_PHONE = "SELECT COUNT(*) FROM useraccount WHERE phone_number = ?";
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM useraccount WHERE email =?";	
	public static final String QUERY_GET_ALL_USER = "SELECT * FROM useraccount";
	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM useraccount WHERE userName = ? and password =?";

	// End: Queries
	
	
	//Product Table Queries
	public static final String INSERT_PRODUCT_SQL = "INSERT INTO product (productName, productDesc, mrp, discountPrice,stock, imageUrl, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_PRODUCT_SQL = "DELETE FROM product WHERE productId = ?";
	public static final String UPDATE_PRODUCT_SQL = "UPDATE product SET productName = ?, productDesc = ?, category = ?, mrp = ?, discountPrice = ?, stock = ?, imageUrl = ? WHERE productId = ?";
		
	
	public static final String CREATE_CART_TABLE = "INSERT INTO cart (userId, totalCartAmount, totalCartQuantity) VALUES (?,?,?)";
	public static final String GET_USER_ID_FROM_USERNAME = "SELECT userID FROM useraccount WHERE userName = ?";
	
	// Start: Parameter names
	
	public static final String userName = "userName";
	public static final String FUllName = "FullName";
	
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String GENDER = "gender";
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String RETYPE_PASSWORD = "retypePassword";
	
	// End: Parameter names

	// Start: Validation Messages
	
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";

	// Other Messages
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_ADMIN = "/pages/admin.jsp";
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_HOME = "/pages/home.jsp";
	public static final String PAGE_URL_ORDERSUCESS = "/pages/orderConfirmed.jsp";
	//public static final String URL_LOGIN = "/login.jsp";
	//public static final String URL_HOME = "/home.jsp";
	// End: JSP Route
	// Start: Servlet Route
		public static final String SERVLET_URL_LOGIN = "/login";
		public static final String SERVLET_URL_REGISTER = "/register";
		public static final String SERVLET_URL_LOGOUT = "/logout";
		public static final String SERVLET_URL_HOME = "/home";
		public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
		

	// Start: Servlet Route
	// Start: Normal Text
			public static final String USER = "user";
			public static final String SUCCESS = "success";
			public static final String TRUE = "true";
			public static final String JSESSIONID = "JSESSIONID";
			public static final String LOGIN = "Login";
			public static final String LOGOUT = "Logout";
			public static final String LOGIN_MODEL = "loginModel";
			public static final String USER_LISTS = "userLists";
			public static final String SLASH= "/";
			public static final String DELETE_ID= "deleteId";
			public static final String UPDATE_ID= "updateId";
			
			
			// End: Normal Text
		
		
		//Admin Credentials
		public static final Object MESSAGE_ERROR_INCORRECT_DATA = "data is incorrect";
		
	}

	