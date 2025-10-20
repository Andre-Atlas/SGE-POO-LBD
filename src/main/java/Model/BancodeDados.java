package Model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BancodeDados {
    private static Connection connection = null;

    // Méttodo para carregar as propriedades do arquivo de configuração
    private static Properties getProperties() throws IOException {
        Properties props = new Properties();
        // O nome do arquivo como está na pasta 'resources'
        String fileName = "config.properties";

        // Tenta carregar o arquivo a partir do 'classpath' (que inclui a pasta 'resources')
        try (InputStream inputStream = BancodeDados.class.getClassLoader().getResourceAsStream(fileName)) {

            if (inputStream == null) {
                throw new IOException("Arquivo de propriedades '" + fileName + "' não encontrado no classpath.");
            }

            // Carrega as propriedades do arquivo
            props.load(inputStream);
        }
        return props;
    }

    public static Connection conectar() {
        if (connection == null) {
            try {
                Properties props = getProperties();
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                // Validação para garantir que as propriedades foram carregadas
                if (url == null || user == null || password == null) {
                    throw new SQLException("Propriedades do banco de dados (url, user, password) não encontradas no arquivo config.properties.");
                }

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

            } catch (SQLException e) {
                System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo de configuração: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }

    public static void desconectar() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexão com o banco de dados fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}