package br.com.romario.api;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import br.com.romario.modelo.dao.DAOImp;
import br.com.romario.modelo.entity.Usuario;

/**
 * @author romariomacedo18@gmail.com
 */
@Path("/app")
@Transactional
public class ControleAPI{

	@Inject
	 private DAOImp<Usuario> daoUsuario;
	 private Gson gson = new Gson();
	
	/**
	 * 
	 * @param e
	 * @return
	 * @category Usuario
	 * 
	 */
	@POST
	@Path("/usuario/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario salvar(String u){;
		Usuario usuario  = gson.fromJson(u,Usuario.class);
		daoUsuario.salvar(usuario);
		return usuario;
	}
	@PUT
	@Path("/usuario/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario atualizarUsuario(String u){
		Usuario usuario  = gson.fromJson(u,Usuario.class);
		daoUsuario.alterar(usuario);
		return usuario;
	}
	@DELETE
	@Path("/usuario/apagar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario apagarUsuario(@PathParam("id") Long id){
		Usuario usuario  = daoUsuario.procurarPeloId(id);
		daoUsuario.remover(usuario);
		return usuario;
	}
	@GET
	@Path("/usuario/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario usuario(@PathParam("id") Long id){
 		return daoUsuario.procurarPeloId(id);
	}
	@GET
	@Path("/usuarios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> usuarios(){
		return daoUsuario.listarTudo();
	}
}
