package br.edu.fateclins.acesso.controle;

import br.edu.fateclins.acesso.dao.TipoUsuarioDAO;
import br.edu.fateclins.acesso.dao.UsuarioDAO;
import br.edu.fateclins.acesso.modelo.TipoUsuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class TipoUsuarioBean implements Serializable {

    private TipoUsuario tpUser;
    private int idTab;
    private List<TipoUsuario> listaTiposUsuario;

    public TipoUsuarioBean() {
        prepararTela();
    }

    public TipoUsuario getTpUser() {
        return tpUser;
    }

    public void setTpUser(TipoUsuario tpUser) {
        this.tpUser = tpUser;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    public List<TipoUsuario> getListaTiposUsuario() {
        if (listaTiposUsuario == null) {
            TipoUsuarioDAO dao = new TipoUsuarioDAO();
            listaTiposUsuario = dao.listarTodos();
        }
        return listaTiposUsuario;
    }

    public void setListaTiposUsuario(List<TipoUsuario> listaTiposUsuario) {
        this.listaTiposUsuario = listaTiposUsuario;
    }

    private void prepararTela() {
        tpUser = new TipoUsuario();
        listaTiposUsuario = null;
        idTab = 0;
    }

    public void editar(TipoUsuario tipo) {
        this.tpUser = tipo;
        idTab = 1;
    }

    public void limpar() {
        prepararTela();
        idTab = 1;
    }

    public void excluir(TipoUsuario tipo) {
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        String msg = dao.delete(tipo);
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sucesso ao Excluir", msg);
        fc.addMessage(null, fm);
        prepararTela();
    }
    
     public void salvar(){
        String msg = "";
        FacesMessage fm;
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        
        if(tpUser.getTipo().isEmpty()){
            msg = "É necessário informar ao menos o tipo de usuário!";
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Campos sem preenchimento", msg);
        } else {
            msg = dao.salvar(tpUser);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao Salvar!",
                                  msg);
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        prepararTela();
        idTab = 1;
    }
}
