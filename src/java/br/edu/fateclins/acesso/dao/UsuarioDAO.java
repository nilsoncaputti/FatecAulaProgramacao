package br.edu.fateclins.acesso.dao;

import br.edu.fateclins.acesso.modelo.Usuario;
import br.edu.fateclins.dao.GenericDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO extends GenericDAO<Usuario> {

    @Override
    public Usuario procurarPorId(Integer id) {
        try {
            return (Usuario) getSessao().get(Usuario.class, id);
        } catch (Exception ex) {
            System.out.println("Erro procurarId - UsuarioDAO");
            ex.getMessage();
            return null;
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        try {
            Criteria qry = getSessao().createCriteria(Usuario.class);
            qry.addOrder(Order.asc("nome"));
            return qry.list();
        } catch (Exception ex) {
            System.out.println("Erro listarTodos - UsuarioDAO");
            ex.getMessage();
            return null;
        }
    }

    @Override
    public String salvar(Usuario obj) {
        if(procurarPorId(obj.getId())==null){
            return this.add(obj);
        } else {
            return this.update(obj);
        }
    }

    public Usuario buscarUsuario(String email) {
        try{
            Criteria qry = getSessao().createCriteria(Usuario.class);
            Criterion filtro = Restrictions.eq("email", email);
            qry.add(filtro);
            return (Usuario) qry.uniqueResult();
        } catch (Exception ex) {
            System.out.println("Erro buscarUsuario - UsuarioDAO");
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
