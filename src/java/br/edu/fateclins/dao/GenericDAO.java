
package br.edu.fateclins.dao;

import br.edu.fateclins.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;


public abstract class GenericDAO<T> {
    
    private Session sessao;

    public GenericDAO() {
        sessao = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Session getSessao() {
        return sessao;
    }

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }
    
    public String add(T obj){
        try{
            this.sessao.save(obj);
            return obj.getClass().getSimpleName() + " inserido com sucesso!";
        } catch (Exception ex){
            System.out.println("ERRO ADD:");
            return ex.getMessage();
        }
    }
    
    public String delete(T obj){
        try{
            this.sessao.delete(obj);
            return obj.getClass().getSimpleName() + " excluído com sucesso!";
        } catch (Exception ex){
            System.out.println("ERRO DELETE:");
            return ex.getMessage();
        }
    }
    
    public String update(T obj){
        try{
            this.sessao.merge(obj);
            return obj.getClass().getSimpleName() + " atualizado com sucesso!";
        } catch (Exception ex){
            System.out.println("ERRO UPDATE:");
            return ex.getMessage();
        }
    }
    
    
    public abstract T procurarPorId(Integer id);
    public abstract List<T> listarTodos();
    public abstract String salvar(T obj);
}
