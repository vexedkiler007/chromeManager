package dbconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// visit_duration is in microseconds
public class connect {

    public static List<DataBaseContainer> Connect() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        List<DataBaseContainer> listContainers = new ArrayList<DataBaseContainer>() {
        };
        String url = "jdbc:sqlite:/home/newuser/.config/google-chrome/Profile 1/History"; //path to history DB
        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();
        String sql = "SELECT visits.visit_duration/1000000/60 AS visit_duration_secs, urls.url, urls.visit_count FROM visits INNER JOIN urls ON  visits.url = urls.id WHERE visit_duration_secs != 0";
        String sql2 = "PRAGMA busy_timeout = 1000";
        stmt.executeQuery(sql2);
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            DataBaseContainer container = new DataBaseContainer(rs.getString("url"), rs.getInt("visit_duration_secs"), rs.getInt("visit_count"));
            listContainers.add(container);
        }
        if (conn != null) conn.close();
        return listContainers;

    }
}



