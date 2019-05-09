package bookmallFinal.test;

import java.util.List;

import bookmallFinal.dao.OrdersDao;
import bookmallFinal.vo.OrderBookVo;
import bookmallFinal.vo.OrdersVo;

public class OrdersDaoTest {
	
	public static void main(String[] args) {
		
		// insert
		Long ordersNo = ordersInsert("박소원","djawlths4@naver.com",30000, "비트아카데미", 1);
		
		
		// Long ordersNo = 1;
		if(ordersNo > -1) {
			orderBookInsert(1L, ordersNo, 1);
			orderBookInsert(2L, ordersNo, 1);
		}

		getOrdersList(1L);
		getOrderBookList(ordersNo);

	}
	
	public static void getOrdersList(Long memberNo) {
		OrdersDao dao = new OrdersDao();
		
		List<OrdersVo> list = dao.getOrderList(memberNo);
		
		System.out.println("******주문 리스트******");
		for(OrdersVo vo : list) {
			System.out.println("이름 : "+vo.getName()+", 가격 : "+ vo.getPrice() + ", 이메일 : "+ vo.getEmail()+", 주소 : "+vo.getReceive_addr());
		}
	}
	
	public static Long ordersInsert(String name, String email, int price, String receive_addr, Long memberNo) {
		OrdersVo vo = new OrdersVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPrice(price);
		vo.setReceive_addr(receive_addr);
		vo.setMember_no(memberNo);
		
		Long ordersNo = new OrdersDao().insert(vo); 
		return ordersNo;
	}
	
	public static void getOrderBookList(Long ordersNo) {
		OrdersDao dao = new OrdersDao();
		List<OrderBookVo> list = dao.getOrderBookList(ordersNo);
		System.out.println("******주문 도서 리스트******");
		for(OrderBookVo vo : list) {
			System.out.println("책 제목 : "+vo.getTitle()+", 수량 : "+ vo.getCount() + ", 가격 : "+ vo.getPrice());
		}
	}
	
	public static void orderBookInsert(Long bookNo, Long orderNo, int count) {
		OrderBookVo vo = new OrderBookVo();
		vo.setBook_no(bookNo);
		vo.setOrder_no(orderNo);
		vo.setCount(count);
		new OrdersDao().insertOrderBook(vo);
	}
	
	

}
