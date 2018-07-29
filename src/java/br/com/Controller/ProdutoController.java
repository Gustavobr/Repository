/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Controller;

import br.com.Entity.Produto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gustavoscipiao
 */
@Named(value = "produtoController")
@SessionScoped
public class ProdutoController implements Serializable {
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    Produto produto = new Produto();

    public ProdutoController() {
        //Produto produto = new Produto();
    }
    
    public void addProduto() throws SQLException, Exception {
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LojaPU");
            EntityManager em = emf.createEntityManager();
            Produto produto = new Produto();
            produto.setId(Integer.SIZE);
            produto.setDescricao("lapis");
            produto.setPreco("100");
            produto.setQuantidade("100");
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
            em.close();
        } catch (EntityExistsException ex) {
            FacesMessage fm;
            fm = new FacesMessage("erro");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            
        }
    }
    
}

/**
 * Creates a new instance of ProdutoController
 */
