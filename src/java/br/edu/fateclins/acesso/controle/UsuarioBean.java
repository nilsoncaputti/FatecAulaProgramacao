package br.edu.fateclins.acesso.controle;

import br.edu.fateclins.acesso.dao.TipoUsuarioDAO;
import br.edu.fateclins.acesso.dao.UsuarioDAO;
import br.edu.fateclins.acesso.modelo.TipoUsuario;
import br.edu.fateclins.acesso.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{

    private Usuario usuario;
    private int idTab;
    private List<Usuario> listaUsuarios;
    private List<TipoUsuario> listaTipos;
    private String senha;

    public UsuarioBean() {
        prepararTela();
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    public List<Usuario> getListaUsuarios() {
        if(listaUsuarios == null){
            UsuarioDAO dao = new UsuarioDAO();
            listaUsuarios = dao.listarTodos();
        }
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<TipoUsuario> getListaTipos() {
        if(listaTipos == null){
            TipoUsuarioDAO dao = new TipoUsuarioDAO();
            listaTipos = dao.listarTodos();
        }
        return listaTipos;
    }

    public void setListaTipos(List<TipoUsuario> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void salvar(){
        String msg = "";
        FacesMessage fm;
        UsuarioDAO dao = new UsuarioDAO();
        
        if(usuario.getNome().isEmpty() || usuario.getEmail().isEmpty()){
            msg = "Nome e/ou e-mail devem ser informados!";
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Campos sem preenchimento", msg);
        } else {
            if(!this.senha.isEmpty()){
                usuario.setSenha(this.senha);
            }
            msg = dao.salvar(usuario);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao Salvar!",
                                  msg);
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        prepararTela();
        idTab = 1;
    }

    private void prepararTela() {
        usuario = new Usuario();
        listaTipos = null;
        listaUsuarios = null;
        senha = null;
        idTab = 0;
    }
    
    public void editar(Usuario usuario){
        this.usuario = usuario;
        idTab = 1;
    }
    
    public void limpar(){
        prepararTela();
        idTab = 1;
    }
    
    public void excluir (Usuario usuario){
        UsuarioDAO dao = new UsuarioDAO();
        String msg = dao.delete(usuario);
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sucesso ao Excluir", msg);
        fc.addMessage(null, fm);
        prepararTela();
        
    }
}
