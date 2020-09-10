package DAO;

import model.Adres;
import model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection con;
    private ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn) throws SQLException {
        this.con = conn;
    }
    @Override
    public boolean save(Adres a) {
        try{
            PreparedStatement stm = con.prepareStatement("insert into adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) \nvalues " +
                    "( '?', '?', '?', '?', '?', ?)");
            stm.setInt(1, a.getAdres_id());
            stm.setString(2, a.getPostcode());
            stm.setString(3, a.getHuisnummer());
            stm.setString(4, a.getStraat());
            stm.setString(5, a.getWoonplaats());
            stm.setInt(6, a.getReiziger_id());
            stm.executeUpdate();

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

            PreparedStatement stm = con.prepareStatement("delete from adres where adres_id = ?");
            stm.setInt(1, a.getAdres_id());
            stm.executeUpdate();

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
            PreparedStatement stm = con.prepareStatement("update adres set postcode = '?', " +
                    "huisnummer = '?', straat = '?', woonplaats = '?' where adres_id = ?");
            stm.setString(1, a.getPostcode());
            stm.setString(2, a.getHuisnummer());
            stm.setString(3, a.getStraat());
            stm.setString(4, a.getWoonplaats());
            stm.setInt(5, a.getAdres_id());
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public Adres findById(int id) {
        try{

            PreparedStatement stm = con.prepareStatement("select * from adres where adres_id = ?");
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
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
            PreparedStatement stm = con.prepareStatement("select * from adres where reiziger_id = ?");
            stm.setInt(1, r);
            ResultSet result = stm.executeQuery();
            result.next();

            System.out.println("Adres gekoppeld aan reizigers-ID "+ r + " is gevonden!");
            return new Adres(result.getInt("adres_id"), result.getString("postcode"), result.getString("huisnummer"),
                    result.getString("straat"), result.getString("woonplaats"),  result.getInt("reiziger_id"));

        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try{
            List<Adres> lijst = new ArrayList<>();
            PreparedStatement stm = con.prepareStatement("select * from adres");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                lijst.add(new Adres(result.getInt("adres_id"), result.getString("postcode"), result.getString("huisnummer"), result.getString("straat"), result.getString("woonplaats"), result.getInt("reiziger_id")));
            }
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }
}
