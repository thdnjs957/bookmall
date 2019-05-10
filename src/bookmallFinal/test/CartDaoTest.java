package bookmallFinal.test;

import java.util.List;

import bookmallFinal.dao.CartDao;
import bookmallFinal.vo.CartVo;

public class CartDaoTest {
	
	public static void main(String[] args) {

		insert(1L,1L,1); //고객 번호, 책 번호, 수량
		insert(1L,2L,1);
		getListTest(1L);

	}

	public static void insert(Long member_no,Long book_no, int count) {
		
		CartVo vo = new CartVo();
		vo.setMember_no(member_no);
		vo.setBook_no(book_no);
		vo.setCount(count);

		new CartDao().insert(vo);
	}

	public static void getListTest(Long memberNo) {
		
		List<CartVo> list = new CartDao().getList(memberNo);

		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}
	
}
