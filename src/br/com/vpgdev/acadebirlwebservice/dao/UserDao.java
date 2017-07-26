package br.com.vpgdev.acadebirlwebservice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.vpgdev.acadebirlwebservice.conn.ConnectionFactory;
import br.com.vpgdev.acadebirlwebservice.domain.User;
import br.com.vpgdev.acadebirlwebservice.utils.Utils;


public class UserDao extends ConnectionFactory {	
	/**
	 * getUsers - Método que retorna uma lista com todos os usuários cadastrados
	 * @return List<User>
	 * @throws SQLException
	 */
	public List<User> getUsers() throws SQLException {
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tbluser");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				User u = createUser(rs);
				userList.add(u);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
				
			}
		}
		return userList;
	}
	
	/**
	 * getUserById - Método que retorna um objeto usuário através do id
	 * @param id
	 * @return User
	 * @throws SQLException
	 */
	public User getUserById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tbluser where iduser=?");
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User u = createUser(rs);
				rs.close();
				return u;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	/**
	 * getByUser - Método que retorna uma lista de usuários, pesquisando através do campo user
	 * @param user
	 * @return List<User>
	 * @throws SQLException
	 */
	public User getByUser(String user) throws SQLException {
		System.out.println(user);
		User u = new User();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tbluser where BINARY username like ? ");
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				u = createUser(rs);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
				
			}
		}
		return u;	
	}
	
	/**
	 * createUser - Método que instancia e preenche um objeto User com os dados que resultarem da tabela
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public User createUser(ResultSet rs) throws SQLException {
		User u = new User();
		u.setIdUser(rs.getLong("iduser"));
		u.setFirstName(rs.getString("firstname"));
		u.setLastName(rs.getString("lastname"));
		u.setEmail(rs.getString("email"));
		u.setSex(rs.getString("sex"));
		u.setUsername(rs.getString("username"));
		u.setPass(rs.getString("pass"));
		
		// Montando as datas através da biblioteca Calendar
		Utils util = new Utils();
		
		Calendar dateBirth = Calendar.getInstance();
		dateBirth.setTime(rs.getDate("datebirth"));		
		u.setDateBirth(util.dateToString(dateBirth)); 
		
		Calendar dateRegister = Calendar.getInstance();
		dateRegister.setTime(rs.getDate("dateregister"));
		u.setDateRegister(util.dateToString(dateRegister));
		
		u.setLevelAccess(rs.getString("levelaccess"));
		
		return u;
	}
	
	/**
	 * save - Método que adiciona ou atualiza um registro no banco de dados. O controle
	 * é feito através do que é passado no objeto User, se houver um Id, então o método atualiza
	 * o registro, caso contrário, salva um novo registro.
	 * @param u
	 * @throws SQLException
	 */
	public void save(User u) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			if (u.getIdUser() == null) {
				stmt = conn.prepareStatement("insert into tbluser "
						+ "(firstname, lastname, email, sex, username, pass, datebirth, levelaccess) "
						+ "values (?,?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update tbluser "
						+ "set firstname=?, lastname=?, email=?, sex=?, username=?, "
						+ "pass=?, datebirth=?, levelaccess=? where iduser=?");
			}

			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3,  u.getEmail());
			stmt.setString(4, u.getSex());
			stmt.setString(5, u.getUsername());
			stmt.setString(6, u.getPass());
			
			Utils util = new Utils();
			stmt.setDate(7, new Date (util.stringToDate(u.getDateBirth()).getTimeInMillis()));
			
			stmt.setString(8, u.getLevelAccess());	
			
			if (u.getIdUser() != null) {
				// Update
				stmt.setLong(9, u.getIdUser());
			}
			
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o Usuario");
			} else {
				if (u.getIdUser() != null) {
					// Update
					System.out.println("Usuario atualizado com sucesso");
				} else {
				System.out.println("Usuario inserido com sucesso");
				}
			}
			
			// se inseriu ser o id auto incremento
			if (u.getIdUser() == null) {
				Long id = getGeneratedId(stmt);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();				
			}
		}
	}
	
	/**
	 * getGeneratedId - Retorna o próximo Id a ser gerado pela tabela
	 * @param stmt
	 * @return
	 * @throws SQLException
	 */
	private Long getGeneratedId(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long cod = rs.getLong(1);
			return cod;
		}
		
		return 0L;
	}
	
	/**
	 * delete - Método que exclui um registro da no banco de dados
	 * @param cod
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(Long cod) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from tbluser where iduser=?");
			stmt.setLong(1, cod);
			
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();				
			}
		}		
	}
	
}
