package serverMonitoring.dataBase;

import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

import java.sql.SQLException;

/**
 * Interface for Data Base creation functionality
 */
public interface CreateDataBase {

    /**
     * Defines method to create Data Base
     */
    public void createDataBase(String db_name) throws SQLException ;

    /**
     * Defines method to create EmployeeEntity Data Base table
     */
    public void createEmployeeEntityTable(EmployeeEntity employeeEntity,
                                          String db_name, String employeeEntity_table) throws SQLException;

    /**
     * Defines method to create ServerEntity Data Base table
     */
    public void createServerEntityTable(ServerEntity serverEntity,
                                        String db_name, String serverEntity_table) throws SQLException;
}
