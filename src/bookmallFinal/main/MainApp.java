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
		getListCart(1);
		getListOrder(1);
		getListOrderBook(1);
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
			System.out.println("책 장르 : "+vo.getName());
		}
	}
	// 책 리스트
	public static void getListBook() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		System.out.println("*******책 리스트*******");
		for(BookVo vo : list) {
			System.out.println("제목 : "+vo.getTitle()+", 가격 : "+ vo.getPrice() + ", 카테고리 : "+ vo.getCategoryName());
		}
	}
	
	// 카트 리스트
	public static void getListCart(Long memberNo) {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList(memberNo);
		System.out.println("*******카트 리스트*******");
		for(CartVo vo : list) {
			System.out.println("제목 : "+vo.getTitle()+", 가격 : "+ vo.getPrice() + ", 수량 : "+ vo.getCount());
		}
	}
	
	//주문리스트
	public static void getListOrder(Long memberNo) {
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getOrderList(memberNo);
		System.out.println("*******주문 리스트*******");
		for(OrdersVo vo : list) {
			System.out.println("이름 : "+vo.getName()+", 가격 : "+ vo.getPrice() + ", 이메일 : "+ vo.getEmail()+", 주소 : "+vo.getReceive_addr());
		}
	}
	// 주문 도서리스트
	public static void getListOrderBook(Long ordersNo) {
		OrdersDao dao = new OrdersDao();
		List<OrderBookVo> list = dao.getOrderBookList(ordersNo);
		System.out.println("*******주문 도서 리스트*******");
		for(OrderBookVo vo : list) {
			System.out.println("책 제목 : "+vo.getTitle()+", 수량 : "+ vo.getCount() + ", 가격 : "+ vo.getPrice());
		}
	}

}
