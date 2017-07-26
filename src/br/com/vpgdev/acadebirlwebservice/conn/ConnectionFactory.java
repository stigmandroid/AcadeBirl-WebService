package br.com.vpgdev.acadebirlwebservice.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionFactory - Classe que realiza a conex�o com o banco de dados
 * @author Fernando
 *
 */
public class ConnectionFactory {	
	 private final static String HOST = "localhost"; // vari�vel que guarda o ip do banco de dados
	 private final static String DATABASE = "bdproject"; // vari�vel que recebe o nome do banco de dados
	 private final static String USER = "root"; // vari�vel que recebe o nome do usu�rio do MySQL
	 private final static String PASS = ""; // vari�vel que recebe a senha do usu�rio do MySQL
	 private final static String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + ""; // vari�vel que recebe a url pra conectar no MySql
	 private final static String DRIVERNAME = "com.mysql.jdbc.Driver"; // seta o nome do driver padr�o
     public static String status = "N�o foi poss�vel conectar ao Banco de Dados"; // vari�vel que recebe o status da conex�o
    
    /**
     * ConnectionFactory - M�todo construtor da classe.
     * Usado para iniciar a configura��o do driver padr�o do mysql
     */
    public ConnectionFactory() {
    	try {
            // Carregando o JDBC Driver padr�o
            Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
            status = "O driver especificado nao foi encontrado.";
            System.out.println(status);
		}
    } // fim construtor

    /**
     * getConnection - M�todo que realiza a conex�o com o banco de dados
     * @return Connection
     */
    public static Connection getConnection() {
        //cria uma vari�vel connection do tipo Connection pra controlar se conectou
        Connection connection = null;
        try {        
            // conecta no BCD
            connection = DriverManager.getConnection(URL, USER, PASS); // guarda na vari�vel a conex�o

            //Testa sua conex�o//              
            if (connection != null) { // se connection n�o for nulo conectou corretamente
                status = ("Conectado ao Banco de Dados"); // muda o status para conectado 
                System.out.println(status);               
            } else { // caso permane�a nulo n�o conectou corretamente
                status = ("N�o foi poss�vel conectar ao Banco de Dados");// muda o status para N�o Conectado       
                System.out.println(status);         
            }
            return connection; // retorna a conex�o
        } catch (SQLException e) { // n�o conectou no banco
            status = ("N�o foi poss�vel conectar ao Banco de Dados");
            System.out.println(status);
            return null;
        }
    }

    /**
     * getStatus - M�todo que retorna o status da conex�o com o banco de dados
     * @return String
     */
    public static String getStatus() {
        return status;
    } // fim m�todo

    /**
     * closeConnection - M�todo que finaliza a conex�o com o banco de dados
     * @return boolean
     */
    public static boolean closeConnection() {
        try {
            ConnectionFactory.getConnection().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    } // fim m�todo


   	/**
   	 * rebootConnection - M�todo que reinicia a conex�o com o banco de dados
   	 * @return Connection
   	 */
    public static Connection rebootConnection() {
        closeConnection();
        return ConnectionFactory.getConnection();
    } //fim m�todo
    
    /**
     * main - Usado para testar a conex�o com o banco de dados 
     * @param args
     */
    public static void main(String[] args) {
    	ConnectionFactory.getConnection();
		System.out.println(ConnectionFactory.getStatus());
	}
    
} // fim classe
