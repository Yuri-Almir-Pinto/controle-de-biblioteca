package com.controledebiblioteca.controledebiblioteca.models.repository;

import com.controledebiblioteca.controledebiblioteca.connection.ConnectionFactory;
import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.entities.Livros;
import com.controledebiblioteca.controledebiblioteca.models.enums.LivroStatus;

import java.sql.*;
import java.util.ArrayList;

public class AutoresRepository {

    private Connection conn;
    private Statement state;

    public AutoresRepository() {
        try {
            conn = ConnectionFactory.getConnection();
            state = conn.createStatement();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Autores> readAllAutores(){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Autores> autores = new ArrayList<>();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_autores");

            while (resultSet.next()){
                Autores autor = new Autores();

                autor.setId(resultSet.getInt("id"));
                autor.setNome(resultSet.getString("nome"));

                autores.add(autor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }

        return autores;
    }

    public Autores readAutor(int id){
        Statement statement = null;
        ResultSet resultSet = null;
        Autores autor = new Autores();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_autores WHERE id = " + id);

            while (resultSet.next()){
                autor.setId(resultSet.getInt("id"));
                autor.setNome(resultSet.getString("nome"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }

        return autor;
    }

    public void updateAutores(ArrayList<Autores> autores) {
        PreparedStatement statement = null;

        try {
            for (Autores autor : autores) {
                statement = conn.prepareStatement("UPDATE tb_autores "
                        + "SET nome = ?"
                        + "WHERE id = ?");

                statement.setString(1, autor.getNome());
                statement.setInt(2, autor.getId());

                statement.executeUpdate();
            }
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);

        } finally {
            ConnectionFactory.statementClose(statement);
        }
    }

    public void createAutor(Autores autor) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("INSERT INTO tb_autores (nome)" +
                    " VALUES(?)");

            statement.setString(1, autor.getNome());

            statement.executeUpdate();
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);

        } finally {
            ConnectionFactory.statementClose(statement);
        }
    }

    public boolean deleteAutor(int id) {
        LivrosRepository repository = new LivrosRepository();
        PreparedStatement statement = null;

        if (readAutor(id) != null) {
            try {
                ArrayList<Livros> livros = repository.readLivrosAutores(id);
                if (livros != null) {
                    ArrayList<Integer> ids = new ArrayList<>();
                    for (Livros l : livros) {
                        ids.add(l.getId());
                    }
                    repository.deleteMuitosLivros(ids);
                }
                statement = conn.prepareStatement("DELETE FROM tb_autores "
                        + "WHERE id = ?");

                statement.setInt(1, id);

                statement.executeUpdate();
            }
            catch (SQLException e1) {
                throw new RuntimeException(e1);
            } finally {
                ConnectionFactory.statementClose(statement);
            }
            return true;
        }
        return false;
    }
}
