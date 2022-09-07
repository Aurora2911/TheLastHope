package com.adventure.database;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * It uses the H2 database.
 */
public class H2Utility extends DbUtility {
    private Connection conn = null;

    @Override
    public void openDbConnection(String pathDb, Properties dbProp) throws SQLException {
        this.conn = DriverManager.getConnection(pathDb, dbProp);
    }

    @Override
    public void closeDbConnection() throws SQLException {
        this.conn.close();
    }

    @Override
    public void createDbTable(String queryTableRec) throws SQLException {
        Statement stm = this.conn.createStatement();
        stm.executeUpdate(queryTableRec);
        stm.close();
    }

    @Override
    public ResultSet readFromDb(String queryReadDb) throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet result = stm.executeQuery(queryReadDb);

        return result;
    }

    @Override
    public void printReadDate(ResultSet resultRec) throws SQLException {
        while (resultRec.next()) {
            System.out.print(resultRec.getString(1));
        }
    }

    @Override
    public String getText(ResultSet resultSetRec) throws SQLException {
        String s = null;
        while (resultSetRec.next())
            s = resultSetRec.getString(1);
        return s;
    }

    @Override
    public void insertStringIntoDbTable(String queryInfoRec, List<String> tableValueRec, int columnsNumRec)
            throws SQLException {
        PreparedStatement prepStm = this.conn.prepareStatement(queryInfoRec);

        int i = 1;

        for (String value : tableValueRec) {
            prepStm.setString(i, value);

            if (i < columnsNumRec)
                i++;

            else
                i = 1;
        }

        prepStm.executeUpdate();
        prepStm.close();
    }

    @Override
    public Boolean isExisted(ResultSet resultRec, String stringRec) throws SQLException {
        Boolean toReturn = false;

        while (resultRec.next()) {
            if (resultRec.getString(1).equals(stringRec))
                toReturn = true;
        }

        return toReturn;
    }
}
