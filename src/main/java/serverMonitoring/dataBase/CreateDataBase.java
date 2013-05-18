package serverMonitoring.dataBase;

import java.sql.SQLException;

/**
 * Interface for Data Base creation functionality
 */
public interface CreateDataBase {
    /**
     * Defines method for confirmation of Data Base existence
     */
    void getDBExistsConfirmation();

    /**
     * Defines method to create EmployeeEntity Data Base table
     */
    public void createEmployeeEntityTable() throws SQLException;

    /**
     * Defines method to create ServerEntity Data Base table
     */
    public void createServerEntityTable() throws SQLException;


}
