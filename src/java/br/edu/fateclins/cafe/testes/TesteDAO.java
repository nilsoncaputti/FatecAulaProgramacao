package br.edu.fateclins.cafe.testes;

import br.edu.fateclins.acesso.modelo.TipoUsuario;
import br.edu.fateclins.acesso.modelo.Usuario;
import br.edu.fateclins.util.HibernateUtil;
import org.hibernate.Session;


public class TesteDAO {
    
    public static void main(String[] args) {
        TipoUsuario tp = new TipoUsuario("Gerente", "Gerente do Café FATEC");
        Usuario user = new Usuario();
        user.setNome("Anderson Pazin");
        user.setEmail("pazin@email.com");
        user.setSenha("123@456");
        user.setDataNascimento("23/01/1979");
        user.setId(0);
        user.setTipo(tp);
        Session sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            sessao.beginTransaction();
            sessao.save(tp);
            sessao.save(user);
            sessao.getTransaction().commit();
        } catch (Exception ex){
            sessao.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
