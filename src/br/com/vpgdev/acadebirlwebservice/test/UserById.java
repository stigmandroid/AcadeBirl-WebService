package br.com.vpgdev.acadebirlwebservice.test;

import java.sql.SQLException;

import br.com.vpgdev.acadebirlwebservice.dao.UserDao;
import br.com.vpgdev.acadebirlwebservice.domain.User;

public class UserById {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		try {
			User u = dao.getUserById(6L);
			System.out.println(u.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
