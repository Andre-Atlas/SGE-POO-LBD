package controller;

import java.sql.*;
import java.util.*;
import Model.Competicao;
import Model.BancodeDados;

public class MetodoCompeticao {
    private Connection connection;

    public MetodoCompeticao() {
        this.connection = BancodeDados.conectar();
        if (this.connection == null) {
            throw new RuntimeException("Falha na conexão com o banco de dados");
        }
    }

    // Método para listar todas as competições cadastradas
    public List<Competicao> listarCompeticoes() {
        List<Competicao> lista = new ArrayList<>();
        String sql = "SELECT ID_Competição, Nome, Local_Cidade, Entidade_Organizadora, Ano FROM Competição";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Percorre o resultado da consulta e cria objetos Competicao
            while (rs.next()) {
                Competicao c = new Competicao(
                        rs.getString("Nome"),
                        rs.getString("Local_Cidade"), // corresponde à coluna da cidade no SQL
                        rs.getString("Entidade_Organizadora"),
                        rs.getInt("Ano")
                );
                c.setID_Competicao(rs.getInt("ID_Competição"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar competições: " + e.getMessage());
            // NÃO relançar a exceção - seguir o mesmo padrão dos outros controllers
        }

        return lista;
    }
}