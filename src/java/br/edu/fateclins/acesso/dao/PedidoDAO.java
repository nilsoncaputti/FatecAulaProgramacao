package br.edu.fateclins.acesso.dao;

/*@author Nilson Caputti */

import br.edu.fateclins.cafe.modelo.Pedido;
import br.edu.fateclins.dao.GenericDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public abstract class PedidoDAO extends GenericDAO<Pedido>{
    
    public Pedido buscarPedido (String dataHora){
       try{
           Criteria qry = getSessao().createCriteria(Pedido.class);
           Criterion filtro = Restrictions.eq("dataHora", dataHora);
           qry.add(filtro);
           return (Pedido) qry.uniqueResult();
       } catch (Exception ex){
           System.out.println("Erro buscarPedido - Pedido");
           return null;
       }
    }

    @Override
    public Pedido procurarPorId(Integer id) {
        try{
             return (Pedido) getSessao().get(Pedido.class, id);
        } catch (Exception ex){
            System.out.println("Erro procurarPoId - Pedido: ");
            //ex.printStackTrace();
            //ex.getMessage();
            return null;
        }
    }

    @Override
    public List<Pedido> listarTodos() {
        try{
            Criteria qry = getSessao().createCriteria(Pedido.class);
            qry.addOrder(Order.asc("dataHora"));
            return qry.list();
        } catch (Exception ex) {
            System.out.println("Erro ao listarTodos - Pedidos");
            return null;
        }
    }
    
    public String salvar (Pedido pedido){
        if(procurarPorId(pedido.getId())==null){
            return this.add(pedido);
        } else {
            return this.update(pedido);
        }
    }        
}

