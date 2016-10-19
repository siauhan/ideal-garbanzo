import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDrill {

    public static void main(String[] args) {

        try {
            Class.forName("org.apache.drill.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:drill:drillbit=localhost");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cp.`employee.json` LIMIT 5");
            while (rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}