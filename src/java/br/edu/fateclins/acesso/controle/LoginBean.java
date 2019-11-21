package br.edu.fateclins.acesso.controle;

import br.edu.fateclins.acesso.dao.UsuarioDAO;
import br.edu.fateclins.acesso.modelo.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class LoginBean {

    private Usuario usuario;
    private UsuarioDAO dao;

    public LoginBean() {
        dao = new UsuarioDAO();
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String logar() {
        Usuario encontrado = dao.buscarUsuario(usuario.getEmail());
        FacesContext fc = FacesContext.getCurrentInstance();
        if (usuario.logar(encontrado)) {
            ExternalContext ec = fc.getExternalContext();
            HttpSession sessao = (HttpSession) ec.getSession(false);
            sessao.setAttribute("userSessao", encontrado);
            return "inicial?faces-redirect=true";
        } else {
            String msg = "Usuário/Senha inválido!";
            usuario = new Usuario();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao logar", msg);
            fc.addMessage(null, fm);
            return null;
        }
    }

    public String logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession sessao = (HttpSession) ec.getSession(false);
        sessao.invalidate();
        return "index?faces-redirect=true";
    }

}
