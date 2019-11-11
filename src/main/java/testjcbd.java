import java.sql.*;

public class testjcbd {

    public static void main(String[] args) {

        config C = new config();
        String url = C.url;
        String user = C.user;
        String mdp = C.mdp;

        Connection connexion = null;
        Statement state = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            connexion = DriverManager.getConnection(url, user, mdp);
            state = connexion.createStatement();
            result = state.executeQuery("SELECT * FROM TestTable");

            while (result.next()) {
                int id = result.getInt("ID");
                String text = result.getString("Text");
                int numero = result.getInt("int");
                boolean bool = result.getBoolean("Boolean");

                System.out.println("ID=" + id + ", Text=" + text + ", Int=" + numero + ", Boolean=" + bool);
            }
            result.close();
/*            int nbRows = state.executeUpdate("INSERT INTO SECTEUR(LIBELLE) VALUES ('Santé')");
            System.out.println("Nombre de lignes insérées=" + nbRows);

            nbRows=state.executeUpdate("INSERT INTO SECTEUR(LIBELLE) VALUES ('Aéronautique'),('Aérospatial')",
                    Statement.RETURN_GENERATED_KEYS);
            System.out.println("\nNombre de lignes insérées=" + nbRows);

            result=state.getGeneratedKeys();
            System.out.println("Identifiants:");
            while (result.next()) {
                int id = result.getInt(1);
                System.out.println(id);
            }
            result.close();

            System.out.println("Saisissez le nom d'une ville pour lister ses structures:");
            Scanner sc = new Scanner(System.in);
            String ville = sc.nextLine();
            sc.close();

            ps = connexion.prepareStatement("SELECT * FROM STRUCTURE WHERE VILLE = ?");

            ps.setString(1, ville);
            result = ps.executeQuery();
            while (result.next()) {
                String nom = result.getString("NOM");
                System.out.println("Nom : " + nom);
            }
            result.close();

            result = state.executeQuery(
                    "SELECT NOM, ID_STRUCTURE, COUNT(*) AS 'Nb_secteurs_activites' " +
                            "FROM STRUCTURE, SECTEUR, SECTEURS_STRUCTURES " +
                            "WHERE SECTEUR.ID = ID_SECTEUR " +
                            "AND ID_STRUCTURE = STRUCTURE.ID " +
                            "GROUP BY ID_STRUCTURE, NOM");

            while (result.next()) {
                String nom = result.getString("NOM");
                int id = result.getInt("ID_STRUCTURE");
                int nbSecteurs = result.getInt("Nb_secteurs_activites");
                System.out.println("NOM=" + nom + ", ID=" + id + ", " + "nb secteurs=" + nbSecteurs);
            }
            result.close();
*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null)
                    result.close();
                if (state != null)
                    state.close();
                if (ps != null)
                    ps.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
