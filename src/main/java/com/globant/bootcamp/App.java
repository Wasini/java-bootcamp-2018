package com.globant.bootcamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import com.globant.bootcamp.factory.FactoryProducer;

public class App {
    public static void main(String[] args) throws Exception {
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        Properties connProperties = getConnectionProperties();
        String query = queryGetScheduleForTeacher(1);
        String output = null;
        
        try {
            conn = FactoryProducer.getFactory(DBType.MYSQL).getConnection(connProperties);
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            output = processResultSet(rs);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { st.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        
        System.out.println(output);
    }
    
    private static Properties getConnectionProperties(){
        ResourceBundle labels = ResourceBundle.getBundle("credentials");
	    String user = labels.getString("user");
	    String url = labels.getString("db.url");
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("url", url);
        return properties;
    }
    
    private static String queryGetScheduleForTeacher(int teacher) {
        return new StringBuilder()
    		.append("SELECT CONCAT(t.last_name, ' ', t.first_name) AS teacher, ")
    		.append("RPAD(schedule.weekday, 10, ' ') AS weekday, ")
    		.append("TIME_FORMAT(schedule.start_time, '%H:%i') AS start_time, ")
            .append("TIME_FORMAT(schedule.end_time, '%H:%i') AS end_time, ")
            .append("schedule.course AS course ")
            .append("FROM Teacher t INNER JOIN Course c ")
            .append("ON (t.ID = c.teacher && t.ID = ").append(teacher).append(") ")
            .append("INNER JOIN Schedule schedule ")
            .append("ON schedule.course = c.name ")
            .append("ORDER BY schedule.weekday ASC, schedule.start_time ASC;")
            .toString();
    }

    private static String processResultSet(ResultSet resultSet) throws SQLException {
        String teacher = null;
        StringBuilder schedule = new StringBuilder();
        schedule.append("Schedule: \n");
        
        while (resultSet.next()) {
            teacher = resultSet.getString("teacher");
            schedule.append("\t")
                .append(resultSet.getString("weekday")).append(" ")
                .append(resultSet.getString("start_time"))
                .append(" - ")
                .append(resultSet.getString("end_time")).append(": ")
                .append(resultSet.getString("course"))
                .append("\n");
        }
        
        return ("Teacher: "+teacher + "\n" + schedule.toString());
    }
}
    
    

