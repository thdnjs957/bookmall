package bookmallFinal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookmallFinal.vo.OrderBookVo;
import bookmallFinal.vo.OrdersVo;

public class OrdersDao {
	
	public boolean insert(OrdersVo vo) {

		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			//String sql = " insert into orders values(null,?,?,?,?)"; //번호,주문번호,결제금액,배송지,고객번호
			String sql = "insert into orders values(null,concat(date(now()),'-',LPAD((select count(*) from order_book),5,0)),?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getPrice());
			pstmt.setString(2, vo.getReceive_addr());
			pstmt.setLong(3, vo.getMember_no());
			
			int count = pstmt.executeUpdate();

			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select last_insert_id()"); //이 전에 쿼리의 pk값 받아오기
			
			if (rs.next()) {
				vo.setNo(rs.getLong(1));
			}
			
			Long orders_pk = vo.getNo();
			
			result = (count == 1);
			
			List <OrderBookVo> list = new ArrayList<OrderBookVo>();
			
			insertOrderBook()

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
	
	
	public boolean insertOrderBook(List <OrderBookVo> list) {

		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = " insert into order_book values(null,?,?,?)"; //번호, 수량, 주문번호,도서번호

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getMember_no());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getReceive_addr());
			pstmt.setLong(4, vo.getMember_no());
			
			int count = pstmt.executeUpdate();

			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select last_insert_id()"); //이 전에 쿼리의 pk값 받아오기

			if (rs.next()) {
				vo.setNo(rs.getLong(1));
			}

			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
	

	public List<OrdersVo> getOrderList(Long no) {

		List<OrdersVo> result = new ArrayList<OrdersVo>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			//select 할때 vo.get
			
			String sql = "select b.no, b.price, b.title, c.no from book b, category c WHERE b.category_no = c.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long no = rs.getLong(1);
				Long price = rs.getLong(2);
				String title = rs.getString(3);
				Long category_no = rs.getLong(4);

				OrdersVo vo = new OrdersVo();
				vo.setNo(no);
				vo.setPrice(price);
				vo.setTitle(title);
				vo.setCategory_no(category_no);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//호출할때 getOrderBookList()
	
	public List<OrderBookVo> getOrderBookList(Long order_no) { //order_book

		List<OrderBookVo> result = new ArrayList<OrderBookVo>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			String sql = "select b.no, b.price, b.title, c.no from book b, category c WHERE b.category_no = c.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long no = rs.getLong(1);
				Long price = rs.getLong(2);
				String title = rs.getString(3);
				Long category_no = rs.getLong(4);

				OrderBookVo vo = new OrderBookVo();
				vo.setNo(no);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.2:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		return conn;
	}

}
