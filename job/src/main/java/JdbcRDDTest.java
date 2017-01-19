import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by phwang on 2017/1/18.
 */
public class JdbcRDDTest {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        String url = "jdbc:oracle:thin:@10.48.193.234:1521:hpdev26";
        Connection connection = DriverManager.getConnection(url, "rcontrol", "rcontrol");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from gov_para_system");
        while (resultSet.next()) {
            String govsystemname = resultSet.getString("GOVSYSTEMNAME");
            System.out.println(govsystemname);
        }
    }
}
