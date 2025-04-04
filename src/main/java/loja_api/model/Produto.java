package loja_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Produto 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private int estoque;

    // Getters e Setters
    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public double getPreco() 
    {
        return preco;
    }

    public void setPreco(double preco) 
    {
        this.preco = preco;
    }

    public int getEstoque() 
    {
        return estoque;
    }

    public void setEstoque(int estoque) 
    {
        this.estoque = estoque;
    }
}
