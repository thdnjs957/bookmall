package bookmallFinal.test;

import java.util.List;

import bookmallFinal.dao.CategoryDao;
import bookmallFinal.vo.CategoryVo;

public class CategoryDaoTest {
	
	public static void main(String[] args) {

		insert("소설");
		insert("수필");
		insert("컴퓨터/IT");
		getListTest();

	}
	
	public static void insert(String name) {
		CategoryVo vo = new CategoryVo();
		vo.setName(name);
		
		new CategoryDao().insert(vo);
	}

	public static void getListTest() {
		
		List<CategoryVo> list = new CategoryDao().getList();

		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
		
	}

}
