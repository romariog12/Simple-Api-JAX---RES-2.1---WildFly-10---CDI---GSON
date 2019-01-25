package br.com.romario.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import br.com.romario.modelo.dao.DAOImp;
import br.com.romario.modelo.entity.Usuario;

/**
 * @author romariomacedo18@gmail.com
 */
@Path("/app")
public class ControleAPI{
	

	@Inject
	 private DAOImp<Usuario> daoUsuario;
	
	@GET
	@Path("/teste")
	@Produces(MediaType.APPLICATION_JSON)
	public String teste(){
		Gson g = new Gson();
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@teste");
		usuario.setNome("Teste");
		usuario.setSenha("9154545");
		daoUsuario.salvar(usuario);
		return g.toJson(usuario);
	}
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String salvar(String e){
		Gson g = new Gson();
		Usuario usuario  = g.fromJson(e,Usuario.class);
		daoUsuario.salvar(usuario);
		return e;
	}
}
