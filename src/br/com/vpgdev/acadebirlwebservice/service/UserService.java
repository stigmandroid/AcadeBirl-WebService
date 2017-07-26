package br.com.vpgdev.acadebirlwebservice.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.vpgdev.acadebirlwebservice.dao.UserDao;
import br.com.vpgdev.acadebirlwebservice.domain.User;

public class UserService {
private UserDao db = new UserDao();
	
	// Lista todos os usuario do banco de dados
	public List<User> getUsuarios() {
		
		try {
			List<User> userList = db.getUsers();
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}
	
	// Busca um usuario pelo id
	public User getUsuario(Long id) {
		try {
			return db.getUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Deleta o usuario pelo id
	public boolean delete(Long id) {
		try {
			return db.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;			
		}
	}
	
	// Salva ou atualiza o usuario
	public boolean save(User usuario) {
		try {
			db.save(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// Busca produto pelo usuario
	public User findByUser(String user) {
		System.out.println(user);
		try {
			return  db.getByUser(user);
		} catch (SQLException e) {
			return null;
		}
	}
}
