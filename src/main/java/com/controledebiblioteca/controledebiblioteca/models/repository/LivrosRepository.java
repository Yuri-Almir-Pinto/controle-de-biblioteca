package com.controledebiblioteca.controledebiblioteca.models.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.controledebiblioteca.controledebiblioteca.connection.ConnectionFactory;
import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.entities.LivroEnumResponse;
import com.controledebiblioteca.controledebiblioteca.models.entities.Livros;

public class LivrosRepository {
    private Connection conn;
    private Statement state;

    public LivrosRepository() {
        try {
            conn = ConnectionFactory.getConnection();
            state = conn.createStatement();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Livros> readAllLivros(){
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSetNew = null;
        ArrayList<Livros> livros = new ArrayList<Livros>();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_livros");

            while (resultSet.next()){
                Livros livro = new Livros();

                livro.setId(resultSet.getInt("id"));
                livro.setNome(resultSet.getString("nome"));
                livro.setData(resultSet.getDate("dataLancamento").toLocalDate());
                livro.setStatus(resultSet.getString("estado"));

                try (Statement innerStatement = conn.createStatement();
                     ResultSet innerResultSet = innerStatement.executeQuery("SELECT * FROM tb_autores WHERE id = " + resultSet.getInt("Autores"))) {

                    if (innerResultSet.next()) {
                        Autores autor = new Autores();
                        autor.setId(innerResultSet.getInt("id"));
                        autor.setNome(innerResultSet.getString("nome"));
                        livro.setAutor(autor);
                    }
                }
                livros.add(livro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }

        return livros;
    }

    public void updateLivrosStatus(ArrayList<LivroEnumResponse> livroEnumResponse) {
        PreparedStatement statement = null;

        try {
            for (LivroEnumResponse ler : livroEnumResponse) {
                statement = conn.prepareStatement("UPDATE tb_livros "
                        + "SET estado = ?"
                        + "WHERE id = ?");

                statement.setString(1, ler.status.toString());
                statement.setInt(2, ler.id);

                Integer rowsAffected = statement.executeUpdate();
                System.out.println("Rows Affected: " + rowsAffected);
            }
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);

        } finally {
            ConnectionFactory.statementClose(statement);
        }
    }

}
