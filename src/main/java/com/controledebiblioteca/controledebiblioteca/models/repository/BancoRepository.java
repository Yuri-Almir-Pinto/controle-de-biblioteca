package com.controledebiblioteca.controledebiblioteca.models.repository;

import com.controledebiblioteca.controledebiblioteca.connection.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class BancoRepository {

    private Connection conn;
    private Statement state;

    public BancoRepository() {
        try {
            conn = ConnectionFactory.getConnection();
            state = conn.createStatement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void reiniciarBanco() throws IOException, FileNotFoundException {
        String arquivo = "/ArquivoBanco.sql";

        try {
            Statement statement = conn.createStatement();
            InputStream is = BancoRepository.class.getResourceAsStream(arquivo);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);

                if (line.trim().endsWith(";")) {
                    String sql = sb.toString();
                    statement.executeUpdate(sql);

                    sb.setLength(0);
                }
            }

        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
