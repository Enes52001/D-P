package DAO;

import model.Adres;
import model.Reiziger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdresDAOPsql implements AdresDAO {
    private Connection con;

    public AdresDAOPsql(Connection conn) throws SQLException {
        this.con = conn;
    }
    @Override
    public boolean save(Adres a) {
        try{
            Statement stm = con.createStatement();

            stm.executeUpdate("insert into adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) \nvalues ( '"
                            +a.getAdres_id()+"', "+"'"+a.getPostcode()+"', '"+a.getHuisnummer()+"', '"+a.getStraat()+"', '"+a.getWoonplaats()+ "', "+a.getReiziger_id()+")");

            System.out.println("Adres is succesvol toegevoegd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Adres a) {
        try{

            Statement stm = con.createStatement();
            stm.executeUpdate("delete from adres where adres_id = "+a.getAdres_id());

            System.out.println("Adres is succesvol verwijderd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres a) {
        try {
            Statement stm = con.createStatement();
            stm.executeUpdate("update adres set postcode = '" + a.getPostcode() + "', " +
                    "huisnummer = '" + a.getHuisnummer() + "', " + "straat = '" + a.getStraat() + "', " + "woonplaats = '" + a.getWoonplaats() +
                    "' where adres_id = " + a.getAdres_id());
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public Adres findById(int id) {
        try{

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("select * from adres where adres_id = "+id);
            result.next();
            System.out.println("Adres met ID "+ id + " is gevonden!");
            return new Adres(result.getInt("adres_id"), result.getString("postcode"), result.getString("huisnummer"),
                    result.getString("straat"), result.getString("woonplaats"),  result.getInt("reiziger_id"));
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Adres findByReizigerId(int r) {
        try{
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("select * from adres where reiziger_id = "+r);
            result.next();

            System.out.println("Adres gekoppeld aan reizigers-ID "+ r + " is gevonden!");
            return new Adres(result.getInt("adres_id"), result.getString("postcode"), result.getString("huisnummer"),
                    result.getString("straat"), result.getString("woonplaats"),  result.getInt("reiziger_id"));

        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }
}
