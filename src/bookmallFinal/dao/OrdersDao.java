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
	
	public Long insert(OrdersVo vo) {

		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		Long ordersNo = -1L;
		
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
				//vo.setNo(rs.getLong(1));
				ordersNo = rs.getLong(1);
			}
			 
			//result = (count == 1);
			

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
		return ordersNo;

	}
	
	//주문도서 insert 
	public boolean insertOrderBook(OrderBookVo vo) {

		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = " insert into order_book values(null,?,?,?)"; //번호, 수량, 주문번호,도서번호

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getCount());
			pstmt.setLong(2, vo.getOrder_no());
			pstmt.setLong(3, vo.getBook_no());
			
			
			int count = pstmt.executeUpdate();

			stmt = conn.createStatement();
			

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
	

	public List<OrdersVo> getOrderList(Long paramNo) {

		List<OrdersVo> result = new ArrayList<OrdersVo>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			//select 할때 vo.get
			
			String sql = "select m.no, m.name, m.email, o.price, o.receive_addr, o.no from orders o and member m where o.member_no = m.no and member_no = ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, paramNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long memberNo = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int price = rs.getInt(4);
				String receiveAddr = rs.getString(5);
				Long no = rs.getLong(6);
				
				OrdersVo vo = new OrdersVo();
				
				vo.setMember_no(memberNo);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPrice(price);
				vo.setReceive_addr(receiveAddr);
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

	//호출할때 getOrderBookList()
	
	public List<OrderBookVo> getOrderBookList(Long ordersNo) { //order_book

		List<OrderBookVo> result = new ArrayList<OrderBookVo>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			String sql = "SELECT b.title, a.count, b.price FROM order_book a, book b WHERE a.book_no = b.no and a.order_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, ordersNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String title = rs.getString(1);
				int count = rs.getInt(2);
				int price = rs.getInt(3);
				
				OrderBookVo vo = new OrderBookVo();
				vo.setTitle(title);
				vo.setCount(count);
				vo.setPrice(price);
				
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
