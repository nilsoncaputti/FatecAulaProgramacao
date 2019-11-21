package br.edu.fateclins.acesso.daoTemp;

import br.edu.fateclins.acesso.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class UsuarioDAO {
    
    private List<Usuario> tabUsuario;
    
    public UsuarioDAO(){
        tabUsuario = new ArrayList<>();
        povoarTabUsuario();
    }

    private void povoarTabUsuario() {
        Usuario u1 = new Usuario("Anderson", "123456", "pazin@email.com");
        Usuario u2 = new Usuario("João", "789654", "joao@email.com");
        Usuario u3 = new Usuario("Felipe", "159753", "felipe@email.com");
        Usuario u4 = new Usuario("Rafael", "456258", "rafael@email.com");
        tabUsuario.add(u1);
        tabUsuario.add(u2);
        tabUsuario.add(u3);
        tabUsuario.add(u4);
    }
    
    public Usuario buscarUsuario(String email){
        for (Usuario obj :tabUsuario){
            if(obj.getEmail().equals(email)){
                return obj;
            }
        }
        return null;
    }
}
