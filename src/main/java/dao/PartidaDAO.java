package dao;

import Model.BancodeDados;
import Model.Partida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

    public void inserir(Partida partida) throws SQLException {
        // CORRIGIDO: Tabela Partida_Disputa e colunas
        String sql = "INSERT INTO Partida_Disputa (Local_Numero, Local_Quadra, Local_Cidade, Data_Partida, Hora, Equipe, Modalidade, Arbitro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, partida.getLocal_Numero());
            pstmt.setInt(2, partida.getLocal_Quadra());
            pstmt.setInt(3, partida.getLocal_Cidade());
            pstmt.setDate(4, partida.getData_Partida());
            pstmt.setTime(5, partida.getHora());
            pstmt.setInt(6, partida.getEquipe());
            pstmt.setInt(7, partida.getModalidade());

            if (partida.getArbitro() != null) {
                pstmt.setInt(8, partida.getArbitro());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    partida.setID_Partida(rs.getInt(1));
                }
            }
        }
    }

    public List<Partida> listarTodos() throws SQLException {
        List<Partida> partidas = new ArrayList<>();
        // CORRIGIDO: Tabela Partida_Disputa
        String sql = "SELECT * FROM Partida_Disputa";

        try (Connection conn = BancodeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Partida p = new Partida(
                        rs.getInt("Local_Numero"),
                        rs.getInt("Local_Quadra"),
                        rs.getInt("Local_Cidade"),
                        rs.getDate("Data_Partida"),
                        rs.getTime("Hora"),
                        rs.getInt("Equipe"),
                        rs.getInt("Modalidade"),
                        rs.getObject("Arbitro", Integer.class) // Permite nulo
                );
                p.setID_Partida(rs.getInt("ID_Partida"));
                p.setGanhador(rs.getObject("Ganhador", Integer.class)); // Seta o ganhador se houver
                partidas.add(p);
            }
        }
        return partidas;
    }

    public boolean atualizar(Partida partida) throws SQLException {
        // CORRIGIDO: Tabela Partida_Disputa e colunas
        // Nota: Não atualiza o Ganhador aqui, isso deve ser um método separado
        String sql = "UPDATE Partida_Disputa SET Local_Numero = ?, Local_Quadra = ?, Local_Cidade = ?, Data_Partida = ?, Hora = ?, Equipe = ?, Modalidade = ?, Arbitro = ? WHERE ID_Partida = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, partida.getLocal_Numero());
            pstmt.setInt(2, partida.getLocal_Quadra());
            pstmt.setInt(3, partida.getLocal_Cidade());
            pstmt.setDate(4, partida.getData_Partida());
            pstmt.setTime(5, partida.getHora());
            pstmt.setInt(6, partida.getEquipe());
            pstmt.setInt(7, partida.getModalidade());

            if (partida.getArbitro() != null) {
                pstmt.setInt(8, partida.getArbitro());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }

            pstmt.setInt(9, partida.getID_Partida());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        // CORRIGIDO: Tabela Partida_Disputa
        String sql = "DELETE FROM Partida_Disputa WHERE ID_Partida = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}