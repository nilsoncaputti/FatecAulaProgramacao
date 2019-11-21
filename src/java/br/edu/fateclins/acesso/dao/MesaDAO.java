package br.edu.fateclins.acesso.dao;

/* @author Nilson Caputti */

import br.edu.fateclins.cafe.modelo.Mesa;
import br.edu.fateclins.dao.GenericDAO;
import java.util.List;
import org.hibernate.Criteria;

public class MesaDAO extends GenericDAO<Mesa> {

    @Override
    public Mesa procurarPorId(Integer id) {
        try {
            return (Mesa) getSessao().get(Mesa.class, id);
        } 
        catch (Exception ex) {
            System.out.println("Erro procurarPoId - Mesa: ");
            //ex.printStackTrace();
            //ex.getMessage();
            return null;
        }
    }

    @Override
    public List<Mesa> listarTodos() {
        try {
            Criteria qry = getSessao().createCriteria(Mesa.class);
            //qry.addOrder(Order.asc("setor"));
            return qry.list();
        } 
        catch (Exception ex) {
            System.out.println("Erro ao listarTodos - Mesa");
            ex.getMessage();
            return null;
        }
    }

    public String salvar(Mesa mesa) {
        if (procurarPorId(mesa.getId()) == null) {
            return this.add(mesa);
        }
        else {
            return this.update(mesa);
        }
    }
}
