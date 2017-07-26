package br.com.vpgdev.acadebirlwebservice.rest;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.vpgdev.acadebirlwebservice.domain.Response;
import br.com.vpgdev.acadebirlwebservice.domain.User;
import br.com.vpgdev.acadebirlwebservice.service.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON +";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON +";charset=utf-8")
public class UsersResource {

	private UserService userService = new UserService();
	
	@GET
	public List<User> get() {
		List<User> userList = userService.getUsuarios();
		return userList;
	}
	
	@GET
	@Path("{id}")
	public User get(@PathParam("id") long id) {
		User u = userService.getUsuario(id);
		return u;
	}

	@GET
	@Path("/username/{username}")
	public User getByUser(@PathParam("username") String user) {
		System.out.println(user);
		return userService.findByUser(user);
	}
		
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		userService.delete(id);
		return Response.Ok("Usuario deletado com sucesso");		
	}
	
	@POST
	public Response post(User u) {
		userService.save(u);
		return Response.Ok("Usuario salvo com sucesso");
	}
	
	@PUT
	public Response put(User u) {
		userService.save(u);
		return Response.Ok("Usuario atualizado com sucesso");
	}
}
