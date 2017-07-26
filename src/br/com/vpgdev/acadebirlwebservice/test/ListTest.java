package br.com.vpgdev.acadebirlwebservice.test;

import java.sql.SQLException;
import java.util.List;

import br.com.vpgdev.acadebirlwebservice.dao.UserDao;
import br.com.vpgdev.acadebirlwebservice.domain.User;

public class ListTest {
	public static void main(String[] args) {
		List<User> users;
		UserDao dao = new UserDao();
		try {
			users = dao.getUsers();
			for (User user : users) {
				System.out.println(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
