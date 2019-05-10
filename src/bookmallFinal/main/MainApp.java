package bookmallFinal.main;

import java.util.List;

import bookmallFinal.dao.BookDao;
import bookmallFinal.dao.CartDao;
import bookmallFinal.dao.CategoryDao;
import bookmallFinal.dao.MemberDao;
import bookmallFinal.dao.OrdersDao;
import bookmallFinal.vo.BookVo;
import bookmallFinal.vo.CartVo;
import bookmallFinal.vo.CategoryVo;
import bookmallFinal.vo.MemberVo;
import bookmallFinal.vo.OrderBookVo;
import bookmallFinal.vo.OrdersVo;

public class MainApp {
	
	public static void main(String[] args) {
		
		getListMember();
		getListCategory();
		getListBook();
		getListCart(1L);
		getListOrder(1L);
		getListOrderBook(1L);
	
	}

	//멤버 리스트
	public static void getListMember() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		System.out.println("*******회원리스트*******");
		for(MemberVo vo : list) {
			System.out.println("이름 : "+vo.getName()+", 전화번호 : "+ vo.getTel() + ", 이메일 : "+ vo.getEmail());
		}
	}
	
	//카테고리리스트
	public static void getListCategory() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		System.out.println("*******카테고리 리스트*******");
		for(CategoryVo vo : list) {
			System.out.println("도서 장르 : "+vo.getName());
		}
	}
	// 책 리스트
	public static void getListBook() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		System.out.println("*******도서 리스트*******");
		for(BookVo vo : list) {
			System.out.println("도서 제목 : "+vo.getTitle()+", 가격 : "+ vo.getPrice() + ", 카테고리넘버 : "+ vo.getCategory_no());
		}
	}
	
	// 카트 리스트
	public static void getListCart(Long memberNo) {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList(memberNo);
		System.out.println("*******카트 리스트*******");
		for(CartVo vo : list) {
			System.out.println("도서 제목 : "+vo.getBook_title()+", 가격 : "+ vo.getPrice() + ", 수량 : "+ vo.getCount());
		}
	}
	
	//주문리스트
	public static void getListOrder(Long memberNo) {
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getOrderList(memberNo);
		System.out.println("*******주문 리스트*******");
		for(OrdersVo vo : list) {
			System.out.println("주문 번호 : "+vo.getOrder_no()+", 이름 : "+vo.getName()+", 결제금액 : "+ vo.getPrice() + ", 이메일 : "+ vo.getEmail()+", 배송지 : "+vo.getReceive_addr());
		}
	}
	// 주문 도서리스트
	public static void getListOrderBook(Long ordersNo) {
		OrdersDao dao = new OrdersDao();
		List<OrderBookVo> list = dao.getOrderBookList(ordersNo);
		System.out.println("*******주문 도서 리스트*******");
		for(OrderBookVo vo : list) {
			System.out.println("도서번호 : "+vo.getBook_no()+", 도서 제목 : "+vo.getTitle()+", 수량 : "+ vo.getCount() + ", 가격 : "+ vo.getPrice());
		}
	}

}
