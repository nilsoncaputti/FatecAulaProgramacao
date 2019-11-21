package br.edu.fateclins.cafe.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/* @author Nilson Caputti */

@Entity
public class Produto implements Serializable{
    @Id
    @GeneratedValue    
    private Integer id;
    @Column(length = 60, nullable = false, unique = true)
    private String descricao;
    @Column(length = 10, precision = 2)
    private double preco;
    @ManyToOne
    @JoinColumn(name="idTipo")
    private TipoProduto tipo;
    @Transient
    private List<ItemPedido> listaItens;
    private String categoria;

    public Produto() {
    }

    public Produto(String categoria, Integer id, String descricao, double preco, TipoProduto tipo) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
        this.categoria=categoria;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<ItemPedido> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemPedido> listaItens) {
        this.listaItens = listaItens;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
    
}
