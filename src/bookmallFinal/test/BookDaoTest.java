package bookmallFinal.test;

import java.util.List;

import bookmallFinal.dao.BookDao;
import bookmallFinal.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {

//		insert(10000L, "어린왕자", 1L);
//		insert(15000L, "수필 강의 노트 ", 2L);
//		insert(20000L, "자바스크립트 완벽 가이드", 3L);
		
		getListTest();

	}

	public static void insert(int price, String title,Long category_no) {
		BookVo vo = new BookVo();
		vo.setPrice(price);
		vo.setTitle(title);
		vo.setCategory_no(category_no);

		new BookDao().insert(vo);
	}

	public static void getListTest() {
		List<BookVo> list = new BookDao().getList();

		for (BookVo vo : list) {
			System.out.println(vo);
		}

	}
}
