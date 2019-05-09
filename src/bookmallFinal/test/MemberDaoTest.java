package bookmallFinal.test;

import java.util.List;

import bookmallFinal.dao.MemberDao;
import bookmallFinal.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		insert("박소원","010-7777-9551","thdnjs@naver.com","1234");
		insert("박건형","010-4832-9783","rjsgud@naver.com","1234");
		getListTest();

	}

	public static void insert(String name, String tel, String email, String passwd) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTel(tel);
		vo.setEmail(email);
		vo.setPasswd(passwd);

		new MemberDao().insert(vo);
	}

	public static void getListTest() {
		List<MemberVo> list = new MemberDao().getList();

		for (MemberVo vo : list) {
			System.out.println(vo);
		}

	}
}
