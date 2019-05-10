package bookmallFinal.test;

import java.util.List;

import bookmallFinal.dao.OrdersDao;
import bookmallFinal.vo.OrderBookVo;
import bookmallFinal.vo.OrdersVo;

public class OrdersDaoTest {
	
	public static void main(String[] args) {
		
		// orderInsert return 값 ordersNo
		Long ordersNo = ordersInsert(25000,"비트아카데미",1L);//주문금액, 배송지, 고객번호
		
		
		if(ordersNo > -1) {
			orderBookInsert(1L, ordersNo, 1); //도서번호,pk 값, 수량
			orderBookInsert(2L, ordersNo, 1);
		}

		getOrdersList(1L); //고객번호에 대한 orderlist 출력
		getOrderBookList(ordersNo);

	}
	
	public static Long ordersInsert(int price, String receive_addr, Long memberNo) {
		
		OrdersVo vo = new OrdersVo();
		vo.setPrice(price);
		vo.setReceive_addr(receive_addr);
		vo.setMember_no(memberNo);
		
		Long ordersNo = new OrdersDao().insert(vo); 
		
		return ordersNo;
	}
	
	
	public static void getOrdersList(Long memberNo) {
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getOrderList(memberNo);
		System.out.println("*******주문 리스트*******");
		for(OrdersVo vo : list) {
			System.out.println("주문 번호 : "+vo.getOrder_no()+", 이름 : "+vo.getName()+", 결제금액 : "+ vo.getPrice() + ", 이메일 : "+ vo.getEmail()+", 배송지 : "+vo.getReceive_addr());
		}
	}
	
	public static void orderBookInsert(Long bookNo, Long orderNo, int count) {
		OrderBookVo vo = new OrderBookVo();
		vo.setBook_no(bookNo);
		vo.setOrder_no(orderNo);
		vo.setCount(count);
		new OrdersDao().insertOrderBook(vo);
	}
	

	public static void getOrderBookList(Long ordersNo) {
		OrdersDao dao = new OrdersDao();
		List<OrderBookVo> list = dao.getOrderBookList(ordersNo);
		System.out.println("*******주문 도서 리스트*******");
		for(OrderBookVo vo : list) {
			System.out.println("도서번호 : "+vo.getBook_no()+", 도서 제목 : "+vo.getTitle()+", 수량 : "+ vo.getCount() + ", 가격 : "+ vo.getPrice());
		}
	}
	
	
	

}
