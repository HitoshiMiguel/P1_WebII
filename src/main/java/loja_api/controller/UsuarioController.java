package loja_api.controller;

import loja_api.model.Usuario;
import loja_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retorna todos os usuários
    @GetMapping
    public List<Usuario> getAllUsuarios() 
    {
        return usuarioRepository.findAll();
    }

    // Cria um novo usuário com validação
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) 
    {
        // Validação dos dados de entrada
        if (usuario.getNome().length() < 3) 
        {
            throw new IllegalArgumentException("O nome do usuário deve ter pelo menos 3 caracteres.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) 
        {
            throw new IllegalArgumentException("O e-mail do usuário é inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) 
        {
            throw new IllegalArgumentException("A senha do usuário deve ter pelo menos 6 caracteres.");
        }

        return usuarioRepository.save(usuario);
    }

    // Retorna um usuário específico pelo ID
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) 
    {
        return usuarioRepository.findById(id).orElse(null);  // Retorna null se o usuário não for encontrado
    }

    // Atualiza um usuário existente com validação
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) 
    {
        // Validação dos dados de entrada
        if (usuario.getNome().length() < 3) 
        {
            throw new IllegalArgumentException("O nome do usuário deve ter pelo menos 3 caracteres.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) 
        {
            throw new IllegalArgumentException("O e-mail do usuário é inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) 
        {
            throw new IllegalArgumentException("A senha do usuário deve ter pelo menos 6 caracteres.");
        }

        usuario.setId(id);  // Garantir que o id passado seja associado ao usuário
        return usuarioRepository.save(usuario);
    }

    // Exclui um usuário pelo ID
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) 
    {
        usuarioRepository.deleteById(id);
    }
}
