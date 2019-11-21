package br.edu.fateclins.acesso.controle;

/* @author Nilson Caputti */
import br.edu.fateclins.acesso.dao.PedidoDAO;
import br.edu.fateclins.cafe.modelo.Pedido;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable {

    private List<Pedido> listaItens;
    private int id;
    private int idTab;
    private Date dataHora;
    private bool pago;
    private bool realizado;
    private Pedido ped;

    public int getIdtab() {
        return idTab;
    }

    public void setIdtab(int idtab) {
        this.idTab = idtab;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public bool getPago() {
        return pago;
    }

    public void setPago(bool pago) {
        this.pago = pago;
    }

    public bool getRealizado() {
        return realizado;
    }

    public void setRealizado(bool realizado) {
        this.realizado = realizado;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    private int mesa;

    public PedidoBean() {
        prepararTela();
    }

    public List<Pedido> getListaPedidos() {
        if (listaItens == null) {
            PedidoDAO dao = new PedidoDAO() {};
            listaItens = dao.listarTodos();
        }
        return listaItens;
    }

    public void setListaItens(List<Pedido> listaProdutos) {
        this.listaItens = listaItens;
    }

    public void salvar() {

        PedidoDAO dao = new PedidoDAO() {};
        String msg = "";
        FacesMessage fm;

        if ((ped.getPreco() == 0) || (ped.getDescricao().isEmpty())) {
            msg = "Campos: Preço e/ou Descrição sem preenchimento!";
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Campos em branco!", msg);
        } else {

            msg = dao.salvar(ped);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao Salvar!", msg);

        }

        FacesContext.getCurrentInstance().addMessage(null, fm);
        prepararTela();
        idTab = 1;
    }

    private void prepararTela() {

        ped = new Pedido();
        listaItens = null;
        idTab = 0;
    }

    public void limpar() {

        prepararTela();
        idTab = 1;
    }

    public void excluir(Pedido pedido) {

        PedidoDAO dao = new PedidoDAO() {};
        String msg = dao.delete(pedido);
        listaItens = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sucesso ao Excluir!", msg);
        fc.addMessage(null, fm);
    }

    public void editar(Pedido pedido) {

        this.ped = pedido;
        idTab = 1;
    }    
}
