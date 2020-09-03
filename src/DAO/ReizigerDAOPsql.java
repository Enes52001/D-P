package DAO;

import model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection con;
    public ReizigerDAOPsql(Connection conn) throws SQLException {
        this.con = conn;
    }

    @Override
    public boolean save(Reiziger r)  {
        try{
            Statement stm = con.createStatement();

            stm.executeQuery("insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) \nvalues ("+r.getId()+", "
                    +"'"+r.getVoorletters()+"', "+"'"+r.getTussenvoegsel()+"', '"+r.getAchternaam()+"', "+" TO_DATE('"+r.getGeboortedatum()+"', 'yyyy-mm-dd'))");

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

            Statement stm = con.createStatement();
            stm.executeUpdate("delete from reiziger where reiziger_id = "+r.getId());

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
            Statement stm = con.createStatement();
            stm.executeUpdate("update reiziger set reiziger_id = '" + r.getId() + "', " + "voorletters = '" + r.getVoorletters() + "', " +
                    "tussenvoegsel = '" + r.getTussenvoegsel() + "', " + "achternaam = '" + r.getAchternaam() + "', " + "geboortedatum = '" + r.getGeboortedatum() + "' where reiziger_id = " + r.getId());
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }

    }

    @Override
    public Reiziger findById(int id) {
        try{

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("select * from reiziger where reiziger_id = "+id);
            result.next();
            System.out.println("reiziger met ID "+ id + " is gevonden!");
            return new Reiziger(result.getInt("reiziger_id"), result.getString("voorletters"), result.getString("tussenvoegsel"), result.getString("achternaam"), result.getDate("geboortedatum"));
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbDatum(String datum) {
        try{
            List<Reiziger> lijst = new ArrayList<>();
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("select * from reiziger where geboortedatum = TO_DATE('"+ datum+"', 'yyyy-mm-dd')");
            while(result.next()){
                lijst.add(new Reiziger(result.getInt("reiziger_id"), result.getString("voorletters"), result.getString("tussenvoegsel"), result.getString("achternaam"), result.getDate("geboortedatum")));
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
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("select * from reiziger");
            while(result.next()){
                lijst.add(new Reiziger(result.getInt("reiziger_id"), result.getString("voorletters"), result.getString("tussenvoegsel"), result.getString("achternaam"), result.getDate("geboortedatum")));
            }
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }
}
