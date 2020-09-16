package DAO;

import model.OVChipkaart;
import model.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection con;
    public OVChipkaartDAOPsql(Connection con) throws SQLException{
        this.con = con;
    }
    @Override
    public boolean save(OVChipkaart o) {
        try{
                    PreparedStatement stm = con.prepareStatement("insert into ov_chipkaart (kaartnummer, geldig_tot, klasse, saldo, reiziger_id) \nvalues (?, ?, TO_DATE(?, 'yyyy-mm-dd')), ?, ?");
            stm.setInt(1, o.getKaartnummer());
            stm.setDate(2, o.getGeldig_tot());
            stm.setInt(3, o.getKlasse());
            stm.setDouble(4, o.getSaldo());
            stm.setInt(5, o.getReiziger_id());
            stm.executeQuery();
            System.out.println("ov chipkaart is succesvol toegevoegd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart o) {
        try{

            PreparedStatement stm = con.prepareStatement("delete from ov_chipkaart where kaartnummer = ?");
            stm.setInt(1, o.getKaartnummer());

            stm.executeUpdate();

            System.out.println("ov chipkaart is succesvol verwijderd!");
            return true;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart o) {
        try {
            PreparedStatement stm = con.prepareStatement("update ov_chipkaart set geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? where kaartnummer = ?");
            stm.setDate(1, o.getGeldig_tot());
            stm.setInt(2, o.getKlasse());
            stm.setDouble(3, o.getSaldo());
            stm.setInt(4, o.getReiziger_id());
            stm.setInt(5, o.getKaartnummer());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("er ging iets mis: " + e.getMessage());
            return false;
        }
    }

    @Override
    public OVChipkaart findByKaartnummer(int knummer) {
            try{
                PreparedStatement stm = con.prepareStatement("select * from ov_chipkaart where kaartnummer = ?");
                stm.setInt(1, knummer);
                ResultSet result = stm.executeQuery();
                result.next();
                System.out.println("ov chipkaart met kaartnummer "+ knummer + " is gevonden!");
                return new OVChipkaart(result.getInt("kaartnummer"), result.getDate("geldig_tot"), result.getInt("klasse"), result.getDouble("saldo"));
            }catch(SQLException e){
                System.err.println("er ging iets mis: "+e.getMessage());
                return null;
            }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try{
            List<OVChipkaart> lijst = new ArrayList<>();
            PreparedStatement stm = con.prepareStatement("select * from ov_chipkaart");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                lijst.add(new OVChipkaart(result.getInt("kaartnummer"), result.getDate("geldig_tot"), result.getInt("klasse"), result.getDouble("saldo")));
            }
            return lijst;
        }catch(SQLException e){
            System.err.println("er ging iets mis: "+e.getMessage());
            return null;
        }
    }
}
