package model;

import DAO.*;


import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {

            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip?user=postgres&password=wachtwoord");

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("select * from reiziger");

            //HIER ONDER IS OPDRACHT P1
            System.out.println("Alle reizigers:\n");

            while (rs.next()) {
                String tv;
                if(rs.getString("tussenvoegsel") == null){
                     tv = "";

                }else{
                     tv = rs.getString("tussenvoegsel");
                }

                System.out.println("   #" + rs.getString("reiziger_id") + ": "
                        + rs.getString("voorletters")+" "
                        + tv+" "
                        + rs.getString("achternaam")+
                        " (" + rs.getString("geboortedatum") + ")"
                );
            }


           // in het klassendiagram staat dat ik een private methode "testreizigerDAO" moet schrijven en daar de CRUD functies in moet testen, maar ik snap niet
           // niet precies hoe dat kan werken, dus heb ik het voorlopig apart gelaten
           // private boolean testReizigerDAO;


            // EIGEN CRUD TESTS, je kan een voor een de dubbele slash's weghalen en uittesten
            Reiziger Enes = new Reiziger(20, "E", "", "Sahin", java.sql.Date.valueOf("2001-12-24"));
            ReizigerDAOPsql dao = new ReizigerDAOPsql(con);

            // SAVE FUNCTIE
            //dao.save(Enes);

            // DELETE FUNCTIE
            //dao.delete(Enes);

            // UPDATE FUNCTIE
            //Enes.setAchternaam("Holland");
            //Enes.setGeboortedatum(java.sql.Date.valueOf("1999-10-04"));
            //dao.update(Enes);

            // FIND FUNCTIES
            //System.out.println(dao.findById(3));
            //System.out.println(dao.findByGbDatum("2002-09-17"));
            //System.out.println(dao.findAll());

            /**
             * P2. Reiziger DAO: persistentie van een klasse
             *
             * Deze methode test de CRUD-functionaliteit van de Reiziger DAO                   Dit is de test methode die op canvas stond, ik krijg hem niet aan de praat, kunt u
             *                                                                                 hier nog even naar kijken?   -Enes
             * @throws SQLException
             */
//            private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
//                System.out.println("\n---------- Test ReizigerDAO -------------");
//
//                // Haal alle reizigers op uit de database
//                List<Reiziger> reizigers = rdao.findAll();
//                System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//                for (Reiziger r : reizigers) {
//                    System.out.println(r);
//                }
//                System.out.println();
//
//                // Maak een nieuwe reiziger aan en persisteer deze in de database
//                String gbdatum = "1981-03-14";
//                Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
//                System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//                rdao.save(sietske);
//                reizigers = rdao.findAll();
//                System.out.println(reizigers.size() + " reizigers\n");
//
//                // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
//            }

        } catch (SQLException e) {
            System.err.println("er is een fout opgetreden: " + e.getMessage());
        }

    }
}
