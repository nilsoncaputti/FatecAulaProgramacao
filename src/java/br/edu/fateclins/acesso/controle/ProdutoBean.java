package br.edu.fateclins.acesso.controle;

/* @author Nilson Caputti */

import br.edu.fateclins.acesso.dao.ProdutoDAO;
import br.edu.fateclins.cafe.modelo.Produto;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

    private Produto pro;
    private List<Produto> listaProdutos;
    private int idTab;
    private String categoria;  
    private String descricao;
    private int id;

    public ProdutoBean() {
        prepararTela();
    }

    public Produto getPro() {
        return pro;
    }

    public void setPro(Produto pro) {
        this.pro = pro;
    }

    public List<Produto> getListaProdutos() {
        if (listaProdutos == null) {
            ProdutoDAO dao = new ProdutoDAO();
            listaProdutos = dao.listarTodos();
        }
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    public void salvar() {
        
        ProdutoDAO dao = new ProdutoDAO();
        String msg = "";
        FacesMessage fm;
                
        if((pro.getPreco()==0)||(pro.getDescricao().isEmpty())){
            msg ="Campos: Preço e/ou Descrição sem preenchimento!";
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Campos em branco!", msg);        
        }       
        
        else {
            
            msg = dao.salvar(pro);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao Salvar!", msg);

        }
        
        FacesContext.getCurrentInstance().addMessage(null, fm);
        prepararTela();
        idTab = 1;
    }
    private void prepararTela() {
        
        pro = new Produto();
        listaProdutos = null;
        idTab = 0;
    }

    public void limpar() {
        
        prepararTela();
        idTab = 1;
    }

    public void excluir(Produto produto) {
        
        ProdutoDAO dao = new ProdutoDAO();
        String msg = dao.delete(produto);
        listaProdutos = null;
        
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sucesso ao Excluir!", msg);
        fc.addMessage(null, fm);
    }

    public void editar(Produto produto) {
        
        this.pro = produto;
        idTab = 1;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

