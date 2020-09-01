import java.sql.*;

public class main {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection( "jdbc:postgresql:://[127.0.0.1]:55255/", "postgres", "enes9@ms");

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("tekst");

            while (rs.next()){
                System.out.println(rs.getString("naam"));
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}

