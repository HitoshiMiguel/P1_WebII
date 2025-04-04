package loja_api.controller;

import loja_api.model.Produto;
import loja_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController 
{

    @Autowired
    private ProdutoRepository produtoRepository;

    // Retorna todos os produtos
    @GetMapping
    public List<Produto> getAllProdutos() 
    {
        return produtoRepository.findAll();
    }

    // Cria um novo produto com validação
    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) 
    {
        // Validação dos dados de entrada
        if (produto.getNome().length() < 3) {
            throw new IllegalArgumentException("O nome do produto deve ter pelo menos 3 caracteres.");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser positivo.");
        }
        if (produto.getEstoque() < 0) {
            throw new IllegalArgumentException("O estoque do produto deve ser maior ou igual a zero.");
        }

        return produtoRepository.save(produto);
    }

    // Retorna um produto específico pelo ID
    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id) 
    {
        return produtoRepository.findById(id).orElse(null);  // Retorna null se o produto não for encontrado
    }

    // Atualiza um produto existente com validação
    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produto) 
    {
        // Validação dos dados de entrada
        if (produto.getNome().length() < 3) 
        {
            throw new IllegalArgumentException("O nome do produto deve ter pelo menos 3 caracteres.");
        }
        if (produto.getPreco() <= 0) 
        {
            throw new IllegalArgumentException("O preço do produto deve ser positivo.");
        }
        if (produto.getEstoque() < 0) 
        {
            throw new IllegalArgumentException("O estoque do produto deve ser maior ou igual a zero.");
        }

        produto.setId(id);  // Garantir que o id passado seja associado ao produto
        return produtoRepository.save(produto);
    }

    // Exclui um produto pelo ID
    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) 
    {
        produtoRepository.deleteById(id);
    }
}
