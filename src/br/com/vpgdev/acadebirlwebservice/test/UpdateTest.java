package br.com.vpgdev.acadebirlwebservice.test;

import java.sql.SQLException;
import java.util.Calendar;

import br.com.vpgdev.acadebirlwebservice.dao.UserDao;
import br.com.vpgdev.acadebirlwebservice.domain.User;
import br.com.vpgdev.acadebirlwebservice.utils.Utils;

public class UpdateTest {
	public static void main(String[] args) {
		User u = new User();
		u.setIdUser(5L);
		u.setFirstName("Teste2");
		u.setLastName("Testando Update");
		u.setEmail("email2@teste.com");
		u.setSex("F");
		u.setUsername("user");
		u.setPass("1234");
		u.setDateBirth("30/03/1999");
		u.setLevelAccess("admin");
		
		UserDao dao = new UserDao();
		
		try {
			dao.save(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
