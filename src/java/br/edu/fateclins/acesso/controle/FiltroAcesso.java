/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.laksdflajklsdjfljas
 */
package br.edu.fateclins.acesso.controle;

import br.edu.fateclins.util.HibernateUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;

/**
 *
 * @author aluno
 */
@WebFilter(filterName = "FiltroAcesso", urlPatterns = {"/*"})
public class FiltroAcesso implements Filter {
    
    private SessionFactory sf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req =  (HttpServletRequest) request;
        HttpSession sessao = req.getSession();
        
        this.sf.getCurrentSession().beginTransaction();
        
        String uri = req.getRequestURI();
        //System.out.println(uri);
        
        if(sessao.getAttribute("userSessao")!=null ||
                uri.contains("resource")){
            chain.doFilter(request, response);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("index.xhtml");
            rd.forward(request, response);
        }
        this.sf.getCurrentSession().getTransaction().commit();
        
    }

    @Override
    public void destroy() {

    }
    
  
}
