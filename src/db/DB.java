package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null; //Atributo estático para manter uma única conexão com o banco de dados
	
	public static Connection getConnection() { //Método que retorna a conexão com o DB
		if(conn == null) { //Estará null se for a primeira conexão
			try {
				Properties props = loadProperties(); //Carrega as propriedades do arquivo db.properties
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);//Estabelece a conexão usando a url e as propriedades(senha, usuário, etc.)
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	

	public static void closeConnection() {
		if(conn != null) {//Verifica se a conexão foi estabelecida antes de tentar fechá-la
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	

	private static Properties loadProperties() { //Método para carregar as propriedades de configurações do banco de dados a partir de um arquivo
		try(FileInputStream fs = new FileInputStream("db.properties")){//Tenta abrir o arquivo
			Properties props = new Properties(); //Cria um objeto Properties para armazenar os dados 
			props.load(fs); //Carrega os dados para o objeto
			return props;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			}
			catch(SQLException e){
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
