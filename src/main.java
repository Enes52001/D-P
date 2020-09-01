import java.sql.*;

public class main {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection( "jdbc:postgresql:://[127.0.0.1]:55255/ovchip", "postgres", "wachtwoord");

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("select * from reizigers");

            while (rs.next()){
                System.out.println(rs.getString("id = "+rs.getString("reiziger_id")));
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}

