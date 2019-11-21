package br.edu.fateclins.cafe.converter;

import br.edu.fateclins.acesso.dao.TipoUsuarioDAO;
import br.edu.fateclins.acesso.modelo.TipoUsuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("TipoUsuarioConverter")
public class TipoUsuarioConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        TipoUsuario tipo = null;
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
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
            return String.valueOf(((TipoUsuario)o).getId());
        } catch (Exception ex){
            return "";
        }
    }    
}