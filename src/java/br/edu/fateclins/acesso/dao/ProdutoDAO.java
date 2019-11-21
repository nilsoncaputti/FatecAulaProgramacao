package br.edu.fateclins.acesso.dao;

/* @author Nilson Caputti */

import br.edu.fateclins.cafe.modelo.Produto;
import br.edu.fateclins.dao.GenericDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public class ProdutoDAO extends GenericDAO<Produto> {

    @Override
    public Produto procurarPorId(Integer id) {
        try {
            return (Produto) getSessao().get(Produto.class, id);
        } 
        catch (Exception ex) {
            System.out.println("Erro procurarPoId - Produto: ");
            //ex.printStackTrace();
            //ex.getMessage();
            return null;
        }
    }

    @Override
    public List<Produto> listarTodos() {
        try {
            Criteria qry = getSessao().createCriteria(Produto.class);
            //qry.addOrder(Order.asc("prod"));
            return qry.list();
        } 
        catch (Exception ex) {
            System.out.println("Erro ao listarTodos - Produto");
            ex.getMessage();
            return null;
        }
    }

    public String salvar(Produto pd) {
        if (procurarPorId(pd.getId()) == null) {
            return this.add(pd);
        } 
        else {
            return this.update(pd);
        }
    }
}
