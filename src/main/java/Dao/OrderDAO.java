package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Order;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class OrderDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/anas?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "admin";

	private static final String INSERT_ORDERS_SQL = "INSERT INTO orders" + "  (name, email, description) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_ORDER_BY_ID = "select id,name,email,description from orders where id =?";
	private static final String SELECT_ALL_ORDERS = "select * from orders";
	private static final String DELETE_ORDERS_SQL = "delete from orders where id = ?;";
	private static final String UPDATE_ORDERS_SQL = "update orders set name = ?,email= ?, description =? where id = ?;";

	public OrderDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertOrder(Order order) throws SQLException {
		System.out.println(INSERT_ORDERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERS_SQL)) {
			preparedStatement.setString(1, order.getName());
			preparedStatement.setString(2, order.getEmail());
			preparedStatement.setString(3, order.getDescription());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Order selectOrder(int id) {
		Order order = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String description = rs.getString("description");
				order = new Order(id, name, email, description);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return order;
	}

	public List<Order> selectAllOrders() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Order> orders = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String description = rs.getString("description");
				orders.add(new Order(id, name, email, description));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return orders;
	}

	public boolean deleteOrder(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ORDERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateOrder(Order order) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ORDERS_SQL);) {
			statement.setString(1, order.getName());
			statement.setString(2, order.getEmail());
			statement.setString(3, order.getDescription());
			statement.setInt(4, order.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}