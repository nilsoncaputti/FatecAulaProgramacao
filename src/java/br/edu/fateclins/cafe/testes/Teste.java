package br.edu.fateclins.cafe.testes;

import br.edu.fateclins.acesso.modelo.Usuario;
import br.edu.fateclins.cafe.modelo.Produto;
import br.edu.fateclins.cafe.modelo.TipoProduto;

/**
 *
 * @author aluno
 */
public class Teste {
    public static void main(String[] args) {
//        Tipo t1 = new Tipo(1, "Bolo");
//        Tipo t2 = new Tipo(2, "Bebidas Quentes");
//        Tipo t3 = new Tipo(3, "Bebidas Geladas");
//        
//        Produto p1 = new Produto(1, "Bolo de Cenoura",7.8, t1);
//        Produto p2 = new Produto(2, "Bolo de Laranja",8.6, t1);
//        Produto p3 = new Produto(3, "Chá Hortelã",5.5, t2);
//        Produto p4 = new Produto(4, "Suco Laranja",7.8, t3);
//        Produto p5 = new Produto(5, "Suco Amora",10.2, t3);
//        
//        t1.getCategoria();
//        p2.getTipo().getCategoria();
          Usuario user = new Usuario();
          System.out.println(user.criptografarSenha("123@456"));
    }
}
