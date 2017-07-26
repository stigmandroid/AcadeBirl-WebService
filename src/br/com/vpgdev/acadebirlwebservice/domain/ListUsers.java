package br.com.vpgdev.acadebirlwebservice.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="users")
public class ListUsers implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<User> users;
	
	@XmlElement(name="user")
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> Users) {
		this.users = Users;
	}
	
	@Override
	public String toString() {
		return "Users list [Users= " + users + "]";
	}

}