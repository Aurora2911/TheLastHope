package com.adventure.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Contains necessary methods for a correct database using.
 */
public abstract class DbUtility {
    abstract public void openDbConnection(String pathDb, Properties dbProp) throws SQLException;

    abstract public void closeDbConnection() throws SQLException;

    abstract public void createDbTable(String queryTableRec) throws SQLException;

    abstract public void insertStringIntoDbTable(String queryInfoRec, List<String> tableValueRec, int columnsNumRec)
            throws SQLException;

    abstract public ResultSet readFromDb(String queryReadDb) throws SQLException;

    abstract public void printReadDate(ResultSet resultRec) throws SQLException;

    abstract public String getText(ResultSet resultSetRec) throws SQLException;

    abstract public Boolean isExisted(ResultSet resultRec, String stringRec) throws SQLException;
}