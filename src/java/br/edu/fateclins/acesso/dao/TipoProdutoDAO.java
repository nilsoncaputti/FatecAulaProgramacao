package br.edu.fateclins.acesso.dao;

/* @author Nilson Caputti */

import br.edu.fateclins.cafe.modelo.TipoProduto;
import br.edu.fateclins.dao.GenericDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public class TipoProdutoDAO extends GenericDAO<TipoProduto> {

    @Override
    public TipoProduto procurarPorId(Integer id) {
        try {
            return (TipoProduto) getSessao().get(TipoProduto.class, id);
        } catch (Exception ex) {
            System.out.println("Erro procurarPoId - Tipo: ");
            //ex.printStackTrace();
            //ex.getMessage();
            return null;
        }
    }

    @Override
    public List<TipoProduto> listarTodos() {
        try {
            Criteria qry = getSessao().createCriteria(TipoProduto.class);
            //qry.addOrder(Order.asc("descricao"));
            return qry.list();
        } 
        catch (Exception ex) {
            System.out.println("Erro ao listarTodos - Tipo");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public String salvar(TipoProduto tp) {
        if (procurarPorId(tp.getId()) == null) {
            return this.add(tp);
        } else {
            return this.update(tp);
        }
    }
}
