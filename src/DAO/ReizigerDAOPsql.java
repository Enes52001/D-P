package DAO;

import model.Adres;
import model.OVChipkaart;
import model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection con;
    private AdresDAO adao;
    private OVChipkaartDAO odao;
    public ReizigerDAOPsql(Connection conn) throws SQLException {
        this.con = conn;
    }

    @Override
    public boolean save(Reiziger r)  {
        try{
            PreparedStatement stm = con.prepareStatement("insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) \nvalues (?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'))");
            stm.setInt(1, r.getId());
            stm.setString(2, r.getVoorletters());
            stm.setString(3, r.getTussenvoegsel());
            stm.setString(4, r.getAchternaam());
            stm.setDate(5, r.getGeboortedatum());
            stm.executeQuery();
            System.out.println("reiziger is succesvol toegevoegd!");
        return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger r) {
        try{

            PreparedStatement stm = con.prepareStatement("delete from reiziger where reiziger_id = ?");
            stm.setInt(1, r.getId());
            stm.executeUpdate();

            System.out.println("reiziger is succesvol verwijderd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger r){
        try {
            PreparedStatement stm = con.prepareStatement("update reiziger set voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? where reiziger_id = ?");
            stm.setString(1, r.getVoorletters());
            stm.setString(2, r.getTussenvoegsel());
            stm.setString(3, r.getAchternaam());
            stm.setDate(4, r.getGeboortedatum());
            stm.setInt(5, r.getId());
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }

    }

    @Override
    public Reiziger findById(int id) {
        try{

            PreparedStatement stm = con.prepareStatement("select * from reiziger where reiziger_id = ?");
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            result.next();
            Reiziger r = new Reiziger(result.getInt("reiziger_id"), result.getString("voorletters"),
                    result.getString("tussenvoegsel"), result.getString("achternaam"), result.getDate("geboortedatum"));
            System.out.println("reiziger met ID "+ id + " is gevonden!");
            r.setAdres(adao.findByReiziger(r));
            r.addOV(odao.findByReiziger(r));
            return r;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbDatum(String datum) {
        try{
            List<Reiziger> lijst = new ArrayList<>();
            PreparedStatement stm = con.prepareStatement("select * from reiziger where geboortedatum = TO_DATE(?, 'yyyy-mm-dd')");
            stm.setString(1, datum);
            ResultSet result = stm.executeQuery();
            while(result.next()){
                Reiziger reiziger = new Reiziger(result.getInt("reiziger_id"), result.getString("voorletters"),
                        result.getString("tussenvoegsel"), result.getString("achternaam"), result.getDate("geboortedatum"));
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reiziger.addOV(odao.findByReiziger(reiziger));
                lijst.add(reiziger);
            }
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try{
            List<Reiziger> lijst = new ArrayList<>();
            PreparedStatement stm = con.prepareStatement("select * from reiziger");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                Reiziger reiziger = new Reiziger(result.getInt("reiziger_id"),
                        result.getString("voorletters"), result.getString("tussenvoegsel"), result.getString("achternaam"), result.getDate("geboortedatum"));
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reiziger.addOV(odao.findByReiziger(reiziger));
                lijst.add(reiziger);
            }
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

}
