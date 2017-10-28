package edu.psu.sweng500.emrms.testdrivers;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallSchemaScript {
    static boolean ranAlready = false;

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void execute() throws SQLException, IOException {
        if (ranAlready) {
            return;
        }

        ranAlready = true;

        // Seefried: We should change this, having the hard-coded username and password is bad
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/emrms_test", "root", "emrms2017");
        ScriptRunner scriptRunner = new ScriptRunner(connection);

        /*
        String schemaScriptName = "Scripts\\MySql\\emrms-schema-20170906-2220.sql";
        String scriptFileAsString = readFile(schemaScriptName, Charset.defaultCharset());
        scriptFileAsString = scriptFileAsString.replaceAll("emrms", "emrms_test");
        scriptRunner.runScript(new StringReader(scriptFileAsString));

        // Seefried: This should work, but does not.  I manually executed the modified stored procedure script and it
        // worked fine on the test database.  However, executing it through ScriptRunner fails.
        for (File file : new File("Scripts\\MySql\\StoredProcedures").listFiles()) {
            if (!file.isFile()) {
                continue;
            }

            scriptRunner = new ScriptRunner(connection);
            String storedProcedureScript = readFile(file.getAbsolutePath(), Charset.defaultCharset());
            storedProcedureScript = storedProcedureScript.replaceAll("[Uu][Ss][Ee] [Ee][Mm][Rr][Mm][Ss]",
                    "USE EMRMS_TEST");
            scriptRunner.runScript(new StringReader(storedProcedureScript));
        }
        */
    }
}
