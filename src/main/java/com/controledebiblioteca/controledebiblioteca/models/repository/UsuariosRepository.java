package com.controledebiblioteca.controledebiblioteca.models.repository;

import com.controledebiblioteca.controledebiblioteca.connection.ConnectionFactory;
import com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios;
import com.controledebiblioteca.controledebiblioteca.models.enums.UsuarioTipo;

import java.sql.*;
import java.util.ArrayList;

public class UsuariosRepository {

    private Connection conn;
    private Statement state;

    public UsuariosRepository() {
        try {
            conn = ConnectionFactory.getConnection();
            state = conn.createStatement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Usuarios> readAllUsuarios(){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Usuarios> usuarios = new ArrayList<>();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_usuarios");

            while (resultSet.next()){
                usuarios.add(instantiateUsuario(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }

        return usuarios;
    }

    public Usuarios readUsuario(Integer id){
        Statement statement = null;
        ResultSet resultSet = null;
        Usuarios usuario = new Usuarios();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_usuarios WHERE id = " + id);

            while (resultSet.next()){
                usuario = instantiateUsuario(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }

        return usuario;
    }

    public Usuarios readUsuarioAutenticacao(Usuarios usuario){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuarios usuarioReturn = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM tb_usuarios " +
                    "WHERE login = ? AND senha = ?");

            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

            resultSet = statement.executeQuery();

            while (resultSet.next()){
                usuarioReturn = instantiateUsuario(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }

        return usuarioReturn;
    }

    public boolean doesLoginExist(String login){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuarios usuarioReturn = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM tb_usuarios " +
                    "WHERE login = ?");

            statement.setString(1, login);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }
    }

    public boolean createUsuario(Usuarios usuario) {
        PreparedStatement statement = null;
        try {
            if (!doesLoginExist(usuario.getLogin())) {
                statement = conn.prepareStatement("INSERT INTO tb_usuarios (login, senha, tipo)" +
                        " VALUES(?, ?, ?);");

                statement.setString(1, usuario.getLogin());
                statement.setString(2, usuario.getSenha());
                statement.setString(3, UsuarioTipo.CLIENTE.getTipo());

                statement.executeUpdate();

                return true;
            }
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);

        } finally {
            ConnectionFactory.statementClose(statement);
        }
        return false;
    }

    public boolean deleteUsuario(int id) {
        PreparedStatement statement = null;
        Integer rowsAffected = null;
            try {
                statement = conn.prepareStatement("DELETE FROM tb_usuarios "
                        + "WHERE id = ?");

                statement.setInt(1, id);

                rowsAffected = statement.executeUpdate();
            }
            catch (SQLException e1) {
                throw new RuntimeException(e1);

            } finally {
                ConnectionFactory.statementClose(statement);
            }
        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }

    public boolean updateUsuarios(ArrayList<Usuarios> usuariosList) {
        PreparedStatement statement = null;
        boolean loginDuplicado = false;
        try {
            for (Usuarios usuario : usuariosList) {
                String query = null;
                boolean temLogin = false;
                boolean temSenha = false;
                if (readUsuario(usuario.getId()) != null) {
                    query = "UPDATE tb_usuarios SET ";
                    if (usuario.getLogin() != null) {
                        query += "login = ?";
                        temLogin = true;
                    }
                    if (usuario.getSenha() != null) {
                        if (temLogin) {
                            query += ", ";
                        }
                        query += "senha = ?";
                        temSenha = true;
                    }
                    query += " WHERE id = ?";
                }
                if (query != null) {
                    statement = conn.prepareStatement(query);

                    int index = 1;
                    if (temLogin) {
                        statement.setString(index++, usuario.getLogin());
                    }
                    if (temSenha) {
                        statement.setString(index++, usuario.getSenha());
                    }
                    statement.setInt(index, usuario.getId());

                    try {
                        statement.executeUpdate();
                    }
                    catch (SQLIntegrityConstraintViolationException e1) {
                        loginDuplicado = true;
                    }
                }
            }
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);
        }
        finally {
            ConnectionFactory.statementClose(statement);
        }
        return loginDuplicado;
    }

    public Usuarios instantiateUsuario (ResultSet resultSet) throws SQLException {
        Usuarios usuario = new Usuarios();

        usuario.setId(resultSet.getInt("id"));
        usuario.setLogin(resultSet.getString("login"));
        usuario.setSenha(resultSet.getString("senha"));
        usuario.setTipo(resultSet.getString("tipo"));

        return usuario;
    }
}