package com.aula.model;



import com.aula.dto.UsuarioDto;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 15)
    private String telefone;

    @Column(length = 255)
    private String endereco;

	// Construtor estático para criar um Usuario a partir de um UsuarioDto com tratamento da senha
    public static Usuario fromDto(UsuarioDto usuarioDto) {
        return new Usuario(
                null,                         // O ID será gerado pelo banco de dados
                usuarioDto.nome(),             // Nome
                usuarioDto.email(),            // Email
                usuarioDto.senha() != null ?   // Criptografa a senha se for informada
                        BCrypt.hashpw(usuarioDto.senha(), BCrypt.gensalt()) : null,
                usuarioDto.telefone(),         // Telefone
                usuarioDto.endereco()          // Endereço
        );
    }
}

