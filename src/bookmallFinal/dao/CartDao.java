package bookmallFinal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmallFinal.vo.CartVo;

public class CartDao {
	public boolean insert(CartVo vo) {

		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " insert into cart values(?,?,?)"; // 고객번호,도서번호,수량

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getMember_no());
			pstmt.setLong(2, vo.getBook_no());
			pstmt.setLong(3, vo.getCount());

			int count = pstmt.executeUpdate();

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

	public List<CartVo> getList(Long memberNo) {

		List<CartVo> result = new ArrayList<CartVo>();

		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			String sql = "select c.member_no, c.book_no, c.count, b.title, b.price FROM cart c, book b WHERE c.book_no = b.no and c.member_no = ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long member_no = rs.getLong(1);
				Long book_no = rs.getLong(2);
				int count = rs.getInt(3);
				String title = rs.getString(4);
				int price = rs.getInt(5);

				CartVo vo = new CartVo();
				vo.setMember_no(member_no);
				vo.setBook_no(book_no);
				vo.setCount(count);
				vo.setBook_title(title);
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
