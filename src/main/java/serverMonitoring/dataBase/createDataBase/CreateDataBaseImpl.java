package serverMonitoring.dataBase.createDataBase;

import org.apache.log4j.Logger;
import serverMonitoring.dataBase.CreateDataBase;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * This class responsible for creation of entity tables in Data Base
 */

public class CreateDataBaseImpl implements CreateDataBase {

    protected static Logger mylog = Logger.getLogger("CreateDataBaseImpl");
    private DataSource dataSource;

    /*
    * Execute SQL query to create Data Base
    */
    @Override
    public void createDataBase(String db_name) throws SQLException {

        String query = "CREATE DATABASE" + db_name;
//        try (Statement statement = con.createStatement()){
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            mylog.error(e);
//        }
    }

    /*
    * Execute SQL query to create employee_entity table
    */
    @Override
    public void createEmployeeEntityTable(EmployeeEntity employeeEntity,
                                          String db_name, String employeeEntity_table) throws SQLException {

        String query = "CREATE TABLE " +
                db_name + employeeEntity_table +
                "(id int NOT NULL, " +
                "employee_name varchar(20) NOT NULL, " +
                "login varchar(20) NOT NULL, " +
                "password varchar(64) NOT NULL, " +
                "email varchar(20) NOT NULL, " +
                "created datetime NOT NULL," +
                "lastLogin datetime NOT NULL," +
                "active int(1) NOT NULL," +
                "admin int(1) NOT NULL)";

//        try (Statement statement = con.createStatement()) {
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            mylog.error(e);
//        }
    }

    /*
    * Execute SQL query to create server_entity table
    */
    @Override
    public void createServerEntityTable(ServerEntity serverEntity,
                                        String db_name, String serverEntity_table) throws SQLException {
        String query = "CREATE TABLE " +
                db_name + serverEntity_table +
                "(id int NOT NULL, " +
                "server_name varchar(20) NOT NULL, " +
                "address varchar(20) NOT NULL, " +
                "port int(16) NOT NULL, " +
                "url varchar NOT NULL, " +
                "state varchar NOT NULL," +
                "response varchar NOT NULL," +
                "created datetime NOT NULL," +
                "lastCheck datetime NOT NULL," +
                "active int(1) NOT NULL)";

//        try (Statement statement = con.createStatement()) {
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            mylog.error(e);
//        }
    }
}
