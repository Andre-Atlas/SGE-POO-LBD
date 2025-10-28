package Model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BancodeDados {

    // 1. REMOVIDO o campo estático da conexão
    // private static Connection connection = null;

    // Método para carregar as propriedades (permanece igual)
    private static Properties getProperties() throws IOException {
        Properties props = new Properties();
        String fileName = "config.properties";

        try (InputStream inputStream = BancodeDados.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("Arquivo de propriedades '" + fileName + "' não encontrado no classpath.");
            }
            props.load(inputStream);
        }
        return props;
    }

    /**
     * MÉTODO CONECTAR (MODIFICADO)
     * Agora lança SQLException e sempre cria uma NOVA conexão.
     */
    public static Connection conectar() throws SQLException {
        try {
            Properties props = getProperties();
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Validação
            if (url == null || user == null || password == null) {
                throw new SQLException("Propriedades do banco de dados (url, user, password) não encontradas no arquivo config.properties.");
            }

            // 2. MUITO IMPORTANTE:
            // Removemos o 'if (connection == null)'
            // Agora, ele SEMPRE cria e retorna uma NOVA conexão.
            // Ele não armazena mais a conexão em um campo estático.
            return DriverManager.getConnection(url, user, password);

        } catch (IOException e) {
            // Lança uma SQLException se não conseguir ler o config
            throw new SQLException("Erro ao ler o arquivo de configuração: " + e.getMessage(), e);
        }
        // Os try/catch de SQLException foram removidos para
        // que a exceção seja propagada para o DAO (que a propaga para o Controller).
    }

    /**
     * MÉTODO DESCONECTAR (MODIFICADO)
     */
    public static void desconectar() {
        // Esta classe não gerencia mais uma conexão estática.
        // O try-with-resources em cada DAO agora cuida do fechamento.
        // Este método não precisa fazer nada, mas o Main.java o chama.
        // System.out.println("Desconexão tratada automaticamente pelos DAOs.");
    }
}