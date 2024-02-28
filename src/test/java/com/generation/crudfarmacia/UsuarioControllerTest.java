package com.generation.crudfarmacia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.crudfarmacia.model.Usuario;
import com.generation.crudfarmacia.repository.UsuarioRepository;
import com.generation.crudfarmacia.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start() {

        usuarioRepository.deleteAll();

        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Root", "root@root.com", "rootroot", " "));

    }

    @Test
    @DisplayName("Cadastrar Um Usuário")
    public void deveCriarUmUsuario() {

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(new Usuario(0L,
                "Ana Eliza", "ana_eliza@email.com.br", "12345678", "-"));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Não se deve permitir duplicação do Usuário")
    public void naoDeveDuplicarUsuario() {
        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Maria Helena", "maria_helena@email.com.br", "12345678", "-"));
        
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(new Usuario(0L,
                "Maria Helena", "maria_helena@email.com.br", "12345678", "-"));
        
        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST,
                        corpoRequisicao, Usuario.class);
        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualiza um Usuário")
    public void deveAtualizarUmUsuario() {
        Optional<Usuario> usuarioCadastrado =
                usuarioService.cadastrarUsuario(new Usuario(0L,
                        "Ana Paula", "ana_paula@email.com.br", "anapaula123", "-"));
        Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(),
                "Ana Paula Olinda", "anapaula_olinda@email.com.br", "anapaula123", "-");
        
        HttpEntity<Usuario> corpoRequisicao = new
                HttpEntity<Usuario>(usuarioUpdate);
        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT,
                        corpoRequisicao, Usuario.class);
        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os Usuários")
    public void deveMostrarTodosOsUsuarios() {
        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Aurora Matilda", "aurora_matilda@email.com.br", "auroramatilda123", "-"));
        
        usuarioService.cadastrarUsuario(new Usuario(0L,
                "Cacau Quintino", "cacauquintino@email.com.br",  "cacauquintino123", "-"));
        
        ResponseEntity<String> Resposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/all", HttpMethod.GET, null,
                        String.class);
        assertEquals(HttpStatus.OK, Resposta.getStatusCode());
    }
}
