package br.edu.fateclins.acesso.controle;

/* @author Nilson Caputti */

import br.edu.fateclins.acesso.dao.TipoProdutoDAO;
import br.edu.fateclins.cafe.modelo.TipoProduto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class TipoProdutoBean {

    private TipoProduto tp;
    private List<TipoProduto> listaTipos;
    private int idTab;

    public TipoProdutoBean() {
        prepararTela();
    }

    public TipoProduto getTp() {
        return tp;
    }

    public void setTp(TipoProduto tp) {
        this.tp = tp;
    }

    public List<TipoProduto> getListaTipos() {
        if (listaTipos == null) {
            TipoProdutoDAO dao = new TipoProdutoDAO();
            listaTipos = dao.listarTodos();
        }
        return listaTipos;
    }

    public void setListaTipos(List<TipoProduto> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    public void salvar() {
        TipoProdutoDAO dao = new TipoProdutoDAO();
        String msg = "";
        FacesMessage fm;
        if ((tp.getCategoria().isEmpty())
                || (tp.getDescricao().isEmpty())) {
            msg = "Campos: Categoria e/ou Descrição sem preenchimento!";
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Campos em branco!", msg);

        } else {
            msg = dao.salvar(tp);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao Salvar!", msg);

        }
        FacesContext.getCurrentInstance().addMessage(null, fm);
        prepararTela();
        idTab = 1;
    }

    private void prepararTela() {
        tp = new TipoProduto();
        listaTipos = null;
        idTab = 0;
    }

    public void limpar() {
        prepararTela();
        idTab = 1;
    }

    public void excluir(TipoProduto tipo) {
        TipoProdutoDAO dao = new TipoProdutoDAO();
        String msg = dao.delete(tipo);
        listaTipos = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sucesso ao Excluir!", msg);
        fc.addMessage(null, fm);
    }

    public void editar(TipoProduto tipo) {
        this.tp = tipo;
        idTab = 1;
    }
}
