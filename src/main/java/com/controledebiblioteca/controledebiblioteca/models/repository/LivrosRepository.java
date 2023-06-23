package com.controledebiblioteca.controledebiblioteca.models.repository;

import java.sql.*;
import java.util.ArrayList;

import com.controledebiblioteca.controledebiblioteca.connection.ConnectionFactory;
import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.entities.Livros;
import com.controledebiblioteca.controledebiblioteca.models.enums.LivroStatus;

public class LivrosRepository {
    private final Connection conn;

    public LivrosRepository() {
        try {
            conn = ConnectionFactory.getConnection();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Livros> readAllLivros(){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Livros> livros = new ArrayList<>();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_livros");

            while (resultSet.next()){
                Livros livro = instantiateLivro(resultSet);

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

    public void updateLivrosStatus(ArrayList<Livros> livroResponse) {
        PreparedStatement statement = null;

        try {
            for (Livros ler : livroResponse) {
                statement = conn.prepareStatement("UPDATE tb_livros "
                        + "SET estado = ?"
                        + "WHERE id = ?");

                statement.setString(1, ler.getStatus().toString());
                statement.setInt(2, ler.getId());

                int rowsAffected = statement.executeUpdate();
                System.out.println("Rows Affected: " + rowsAffected);
            }
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);

        } finally {
            ConnectionFactory.statementClose(statement);
        }
    }

    public Livros readLivro(int id){
        Statement statement = null;
        ResultSet resultSet = null;
        Livros livro = new Livros();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_livros WHERE id = " + id);

            while (resultSet.next()){

                livro = instantiateLivro(resultSet);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.statementClose(statement);
            ConnectionFactory.resultSetClose(resultSet);
        }

        return livro;
    }

    public boolean deleteLivro(int id) {
        PreparedStatement statement = null;

        Livros livro = readLivro(id);
        if (livro.getStatus() == LivroStatus.INDISPONIVEL) {
            try {
                statement = conn.prepareStatement("DELETE FROM tb_livros "
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

    public void deleteMuitosLivros(ArrayList<Integer> ids) {
        PreparedStatement statement = null;

        try {
            if (ids.size() > 0) {
                String query = "DELETE FROM tb_livros WHERE id IN (";
                for (int i = 0; i < ids.size() ; i++) {
                    if (i+1 != ids.size()) {
                        query += "?,";
                    }
                    else {
                        query += "?)";
                    }
                }

                statement = conn.prepareStatement(query);

                for (int i = 0; i < ids.size() ; i++) {
                    statement.setInt(i+1, ids.get(i));
                }

                statement.executeUpdate();
            }
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);

        } finally {
            ConnectionFactory.statementClose(statement);
        }

    }

    public ArrayList<Livros> readLivrosAutores(int autorId){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Livros> livros = new ArrayList<>();

        try {

            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_livros WHERE autores = " + autorId);

            while (resultSet.next()){
                Livros livro = instantiateLivro(resultSet);

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

    public void updateLivro(Livros livro) {
        PreparedStatement statement = null;
            try {
                statement = conn.prepareStatement("UPDATE tb_livros "
                        + "SET nome = ?, " +
                        "dataLancamento = ?, " +
                        "autores = ?, " +
                        "estado = ? " +
                        "WHERE id = ? ");

                statement.setString(1, livro.getNome());
                statement.setDate(2, Date.valueOf(livro.getData()));
                statement.setInt(3, livro.getAutor().getId());
                statement.setString(4, livro.getStatus().toString());
                statement.setInt(5, livro.getId());

                statement.executeUpdate();
            }
            catch (SQLException e1) {
                throw new RuntimeException(e1);

            } finally {
                ConnectionFactory.statementClose(statement);
            }

    }

    public void createLivro(Livros livro) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("INSERT INTO tb_livros (nome, dataLancamento, autores, estado)" +
                    " VALUES(?,?,?,?)");

            statement.setString(1, livro.getNome());
            statement.setDate(2, Date.valueOf(livro.getData()));
            statement.setInt(3, livro.getAutor().getId());
            statement.setString(4, livro.getStatus().toString());

            statement.executeUpdate();
        }
        catch (SQLException e1) {
            throw new RuntimeException(e1);

        } finally {
            ConnectionFactory.statementClose(statement);
        }
    }

    public Livros instantiateLivro(ResultSet resultSet) throws SQLException {
        Livros livro = new Livros();
        AutoresRepository repository = new AutoresRepository();

        livro.setId(resultSet.getInt("id"));
        livro.setNome(resultSet.getString("nome"));
        livro.setData(resultSet.getDate("dataLancamento").toLocalDate());
        livro.setStatus(resultSet.getString("estado"));
        livro.setAutor(repository.readAutor(resultSet.getInt("autores")));

        return livro;
    }

}
