import java.sql.*;

public class main {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost/ovchip?user=postgres&password=wachtwoord");

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("select * from reiziger");
            System.out.println("Alle reizigers:\n");
            while (rs.next()){
                System.out.println("   #"+rs.getString("reiziger_id")+": "
                        +rs.getString("voorletters")
                        +rs.getString("achternaam")
                        +rs.getString("tussenvoegsel")+
                        " ("+rs.getString("geboortedatum")+")"
                );
            }
        }catch(SQLException e){
            System.err.println("er is een fout opgetreden: "+e.getMessage());
        }
    }
}

