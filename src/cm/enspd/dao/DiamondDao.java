package cm.enspd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cm.enspd.models.Diamond;

public class DiamondDao {
	//Mysql connecion parameter
	private String jdbcURL = "jdbc:mysql://localhost:3306/debrouillard?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	//Query string for prepared statement
	private static final String INSERT_DIAMOND_SQL="insert into diamond" + "(designation, weight, price) values" + "(?, ?, ?);";
	private static final String SELECT_ALL_DIAMOND="select * from diamond;";
	private static final String SELECT_DIAMOND_BY_ID="select * from diamond where id=?;";
	private static final String DELETE_DIAMOND_SQL="delete from diamond where id=?;";
	private static final String UPDATE_DIAMOND_SQL="update diamond set designation=?, weight=?, price=? where id=?;";
	
	//Default constructor
	public DiamondDao() {
		super();
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
		}catch(SQLException e) 
		{
			
			e.printStackTrace();
			
		}catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return connection;
	
	}
	
	public void insertDiamond(Diamond diamond) throws SQLException {
		System.out.println(INSERT_DIAMOND_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIAMOND_SQL)) {
				preparedStatement.setString(1, diamond.getDesignation());
				preparedStatement.setInt(2, diamond.getWeight());
				preparedStatement.setInt(3, diamond.getPrice());
				preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			printSQLException(e);
		}
	}
	
	
	public Diamond selectDiamond(int id) {
		Diamond Diamond = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIAMOND_BY_ID);) {
		
				preparedStatement.setInt(1, id);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String designation = rs.getString("designation");
					int weight = rs.getInt("weight");
					int price = rs.getInt("price");
					Diamond = new Diamond(id, designation, weight, price);
				}
			
		} catch (SQLException e) {
			// TODO: handle exception
			printSQLException(e);
		}
		return Diamond;
	}
	
	public List<Diamond> selectAllDiamonds() {
		
		List<Diamond> Diamonds = new ArrayList<>();
		
		// Step 1: Etablir la connection
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DIAMOND);) {
			
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String designation = rs.getString("designation");
				int weight = rs.getInt("weight");
				int price = rs.getInt("price");
				Diamonds.add(new Diamond(id, designation, weight, price));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Diamonds;
	}
		
	
	public boolean deleteDiamond(int id) throws SQLException{
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DIAMOND_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() >0;
		}
		return rowDeleted;
	}
	
	public void updateDiamond(Diamond d) throws SQLException {
		System.out.println(INSERT_DIAMOND_SQL);
		
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DIAMOND_SQL)) 
		{
			preparedStatement.setInt(1, d.getId());
			preparedStatement.setString(2, d.getDesignation());
			preparedStatement.setInt(2, d.getWeight());
			preparedStatement.setInt(3, d.getPrice());
			preparedStatement.executeUpdate();
						
		}catch(SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
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
