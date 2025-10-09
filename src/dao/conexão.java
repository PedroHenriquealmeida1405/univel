package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexão {
    private static final String URL = "jdbc:mysql://localhost:3306/univel?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection conectar() {
        Connection Conexao = null;
        try {

            Class.forName(className:"com.mysql.cj.jdbc.Driver");


            Conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(x:"conectado com sucesso ao Mysql!");


        }catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do MySql não encontrado:" + e.getMessage());
        }catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        return Conexao;
    }
}