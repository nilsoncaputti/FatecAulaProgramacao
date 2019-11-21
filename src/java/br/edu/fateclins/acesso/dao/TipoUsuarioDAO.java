package br.edu.fateclins.acesso.dao;

import br.edu.fateclins.acesso.modelo.TipoUsuario;
import br.edu.fateclins.dao.GenericDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/* @author Nilson Caputti */

public class TipoUsuarioDAO extends GenericDAO<TipoUsuario>{

    @Override
    public TipoUsuario procurarPorId(Integer id) {
        try {
            return (TipoUsuario) getSessao().get(TipoUsuario.class, id);
        } catch (Exception ex) {
            System.out.println("Erro procurarId - TipoUsuarioDAO");
            ex.getMessage();
            return null;
        }
    }

    @Override
    public List<TipoUsuario> listarTodos() {
         try {
            Criteria qry = getSessao().createCriteria(TipoUsuario.class);
            qry.addOrder(Order.asc("tipo"));
            return qry.list();
        } catch (Exception ex) {
            System.out.println("Erro listarTodos - TipoUsuarioDAO");
            ex.getMessage();
            return null;
        }
    }

    @Override
    public String salvar(TipoUsuario obj) {
        if(procurarPorId(obj.getId())==null){
            return this.add(obj);
        } else {
            return this.update(obj);
        }
    }
    
}
