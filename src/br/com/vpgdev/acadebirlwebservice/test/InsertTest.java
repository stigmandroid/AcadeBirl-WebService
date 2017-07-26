package br.com.vpgdev.acadebirlwebservice.test;

import java.sql.SQLException;
import java.util.Calendar;

import br.com.vpgdev.acadebirlwebservice.dao.UserDao;
import br.com.vpgdev.acadebirlwebservice.domain.User;

public class InsertTest {
	public static void main(String[] args) {
		User u = new User();
		u.setFirstName("Teste");
		u.setLastName("Testando Insert");
		u.setEmail("email@teste.com");
		u.setSex("M");
		u.setUsername("user");
		u.setPass("1234");
		u.setDateBirth("07/08/2015");
		u.setLevelAccess("user");
		
		UserDao dao = new UserDao();
		
		try {
			dao.save(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
