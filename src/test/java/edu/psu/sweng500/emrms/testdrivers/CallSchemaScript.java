package edu.psu.sweng500.emrms.testdrivers;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallSchemaScript {
    static boolean ranAlready = false;

    public static void execute() throws SQLException, FileNotFoundException {
        if (ranAlready) {
            return;
        }

        ranAlready = true;

        String aSQLScriptFilePath = "Scripts\\MySql\\test_database_schema.sql";

        // Seefried: We should change this, having the hard-coded username and password is bad
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/emrms_test", "root", "emrms2017");
        ScriptRunner sr = new ScriptRunner(connection);

        Reader reader = new BufferedReader(
                new FileReader(aSQLScriptFilePath));

        sr.runScript(reader);
    }
}
