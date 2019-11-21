package br.edu.fateclins.acesso.controle;

/* @author Nilson Caputti */

import br.edu.fateclins.acesso.dao.MesaDAO;
import br.edu.fateclins.cafe.modelo.Mesa;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@ViewScoped
public class MesaBean  implements Serializable{

    private Mesa mesa;
    private List<Mesa> listaMesas;
    private int idTab;
    private String setor;
    private int numeroMesa;
    private int qtdeLugares;
    private String status;
    
    public MesaBean() {
        prepararTela();
    }

   
    public List<Mesa> getListaMesas() {
        if (listaMesas == null) {
            MesaDAO dao = new MesaDAO();
            listaMesas = dao.listarTodos();
        }
        return listaMesas;
    }

    public void setListaMesas(List<Mesa> listaMesas) {
        this.listaMesas = listaMesas;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    public void salvar() {
        MesaDAO dao = new MesaDAO();
        String msg = "";
        FacesMessage fm;
        if (mesa.getSetor().isEmpty()) {
            msg = "Campos: Mesa e/ou Descrição sem preenchimento!";
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Campos em branco!", msg);

        } 
        else {
            msg = dao.salvar(mesa);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao Salvar!", msg);

        }
        FacesContext.getCurrentInstance().addMessage(null, fm);
        prepararTela();
        idTab = 1;
    }

    private void prepararTela() {
        mesa = new Mesa();
        idTab = 0;
        listaMesas=null;
    }

    public void limpar() {
        prepararTela();
        idTab = 1;
    }

    public void excluir(Mesa mesa) {
        MesaDAO dao = new MesaDAO();
        String msg = dao.delete(mesa);
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sucesso ao Excluir!", msg);
        fc.addMessage(null, fm);
        prepararTela();
    }

    public void editar(Mesa mesa) {
        this.mesa = mesa;
        idTab = 1;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getQtdeLugares() {
        return qtdeLugares;
    }

    public void setQtdeLugares(int qtdeLugares) {
        this.qtdeLugares = qtdeLugares;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    
}
