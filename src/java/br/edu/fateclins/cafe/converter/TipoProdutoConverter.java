package br.edu.fateclins.cafe.converter;

/*@author Nilson Caputti */

import br.edu.fateclins.acesso.dao.TipoProdutoDAO;
import br.edu.fateclins.cafe.modelo.TipoProduto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("TipoProdutoConverter")

public class TipoProdutoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        TipoProduto tipo = null;
        TipoProdutoDAO dao = new TipoProdutoDAO();
        try{
            tipo = dao.procurarPorId(Integer.parseInt(string));
        } catch (Exception ex){
            System.out.println("Erro no converter");
        }
        return tipo;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try{         
            return String.valueOf(((TipoProduto)o).getId());
        } catch (Exception ex){
            return "";
        }
    }    
}
