package com.example.Clinica.service.impl;

import com.example.Clinica.config.ConfiguracaoJDBC;
import com.example.Clinica.model.Usuario;
import com.example.Clinica.service.IService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

@Service//para o Spring identificar que esse objeto é para ser gerenciado por ele
public class UsuarioServiceImpl implements IService<Usuario> {
    //Mensagem mencionou que a configuração não havia sido inicializada
     private ConfiguracaoJDBC configuracaoJDBC;

     //linkar a classe com a configuração para conectar
    public UsuarioServiceImpl(){
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }
    @Override
    public Usuario salvar(Usuario usuario) { // seleciona o atributo e shift F6 (muda todas)
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("INSERT INTO USUARIO (NOME ,EMAIL, SENHA, NIVELACESSO) " +
                        "VALUES ('%s','%s','%s','%s')", usuario.getNome(),
                usuario.getEmail(), usuario.getSenha(), usuario.getNivelacesso());
        try {
            stmt = conexao.createStatement();
            ((Statement) stmt).executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next())
                usuario.setId(keys.getInt(1));
            stmt.close();
            conexao.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;
    }


    @Override
    public List<Usuario> buscarTodos() {
         Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
         Statement stmt = null;
         String query = "SELECT * FROM USUARIO";
         List<Usuario> usuarios = new ArrayList<>();

         try {
             stmt = conexao.createStatement();
             ResultSet result = stmt.executeQuery(query);
             while (result.next()) {
                 usuarios.add(criarObjetoUsuario(result));
             }
             stmt.close();
             conexao.close();
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
         return usuarios;
     }

    @Override
    public void excluir(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("DELETE FROM USUARIO WHERE ID = %s", id);
        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conexao.close();
        } catch (SQLException throwables) {

        }
    }

    private Usuario criarObjetoUsuario(ResultSet resultado) throws SQLException {
        Integer id = resultado.getInt("ID");
        String nome = resultado.getString("NOME");
        String email = resultado.getString("EMAIL");
        String senha = resultado.getString("SENHA");
        String nivelAcesso = resultado.getString("NIVEL_ACESSO");
        Usuario usuario = new Usuario(id, nome, email, senha, nivelAcesso);
        return usuario;
    }

}