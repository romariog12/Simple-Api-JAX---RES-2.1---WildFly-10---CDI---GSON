package teste;

import org.junit.Test;

import br.com.romario.modelo.dao.DAOImp;
import br.com.romario.modelo.entity.Usuario;

import static org.junit.Assert.*;

public class APITeste {
	
	private DAOImp<Usuario> daoUsuario;

	@Test
	public void cadastrarUsuarioTeste(){
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@teste");
		usuario.setNome("Teste");
		usuario.setSenha("9154545");
		assertEquals(true,daoUsuario.salvar(usuario));	
	}
}
