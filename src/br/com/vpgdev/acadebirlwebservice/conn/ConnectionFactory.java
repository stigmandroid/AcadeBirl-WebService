package br.com.vpgdev.acadebirlwebservice.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionFactory - Classe que realiza a conexão com o banco de dados
 * @author Fernando
 *
 */
public class ConnectionFactory {	
	 private final static String HOST = "localhost"; // variável que guarda o ip do banco de dados
	 private final static String DATABASE = "bdproject"; // variável que recebe o nome do banco de dados
	 private final static String USER = "root"; // variável que recebe o nome do usuário do MySQL
	 private final static String PASS = ""; // variável que recebe a senha do usuário do MySQL
	 private final static String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + ""; // variável que recebe a url pra conectar no MySql
	 private final static String DRIVERNAME = "com.mysql.jdbc.Driver"; // seta o nome do driver padrão
     public static String status = "Não foi possível conectar ao Banco de Dados"; // variável que recebe o status da conexão
    
    /**
     * ConnectionFactory - Método construtor da classe.
     * Usado para iniciar a configuração do driver padrão do mysql
     */
    public ConnectionFactory() {
    	try {
            // Carregando o JDBC Driver padrão
            Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
            status = "O driver especificado nao foi encontrado.";
            System.out.println(status);
		}
    } // fim construtor

    /**
     * getConnection - Método que realiza a conexão com o banco de dados
     * @return Connection
     */
    public static Connection getConnection() {
        //cria uma variável connection do tipo Connection pra controlar se conectou
        Connection connection = null;
        try {        
            // conecta no BCD
            connection = DriverManager.getConnection(URL, USER, PASS); // guarda na variável a conexão

            //Testa sua conexão//              
            if (connection != null) { // se connection não for nulo conectou corretamente
                status = ("Conectado ao Banco de Dados"); // muda o status para conectado 
                System.out.println(status);               
            } else { // caso permaneça nulo não conectou corretamente
                status = ("Não foi possível conectar ao Banco de Dados");// muda o status para Não Conectado       
                System.out.println(status);         
            }
            return connection; // retorna a conexão
        } catch (SQLException e) { // não conectou no banco
            status = ("Não foi possível conectar ao Banco de Dados");
            System.out.println(status);
            return null;
        }
    }

    /**
     * getStatus - Método que retorna o status da conexão com o banco de dados
     * @return String
     */
    public static String getStatus() {
        return status;
    } // fim método

    /**
     * closeConnection - Método que finaliza a conexão com o banco de dados
     * @return boolean
     */
    public static boolean closeConnection() {
        try {
            ConnectionFactory.getConnection().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    } // fim método


   	/**
   	 * rebootConnection - Método que reinicia a conexão com o banco de dados
   	 * @return Connection
   	 */
    public static Connection rebootConnection() {
        closeConnection();
        return ConnectionFactory.getConnection();
    } //fim método
    
    /**
     * main - Usado para testar a conexão com o banco de dados 
     * @param args
     */
    public static void main(String[] args) {
    	ConnectionFactory.getConnection();
		System.out.println(ConnectionFactory.getStatus());
	}
    
} // fim classe
