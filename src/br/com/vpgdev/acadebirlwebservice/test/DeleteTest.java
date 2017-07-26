package br.com.vpgdev.acadebirlwebservice.test;

import java.sql.SQLException;

import br.com.vpgdev.acadebirlwebservice.dao.UserDao;

/**
 * DeleteTest - Classe que executa um teste de exclusão de dados no banco de dados
 * @author Fernando
 *
 */
public class DeleteTest {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		try {
			// passa o id do tipo long para excluir o registro
			dao.delete(5L);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
