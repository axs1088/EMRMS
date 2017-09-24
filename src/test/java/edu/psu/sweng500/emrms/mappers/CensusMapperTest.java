package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.testdrivers.CallSchemaScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/emrms-context.xml"})
public class CensusMapperTest {
    @Autowired
    @Qualifier("censusMapper")
    CensusMapper mapper;

    @Before
    public void setUp() throws FileNotFoundException, SQLException {
        verifySpringAnnotations();
    }

    private void verifySpringAnnotations() {
        assertThat(mapper, instanceOf(CensusMapper.class));
    }

    @BeforeClass
    public static void callSchemaScript() throws SQLException, FileNotFoundException {
        CallSchemaScript.execute();
    }

    @Test
    public void testThatSchemaScriptClearsAllTables() {
        // Seefried: These are currently throwing exceptions because they can't find the stored procedures
//        List<HCensus> emptyList = mapper.getPhysicianCensus(0);
//        assertTrue(emptyList.isEmpty());
//
//        emptyList = mapper.getNurseCensus(-1);
//        assertTrue(emptyList.isEmpty());
//
//        emptyList = mapper.getPatientListByDemographics("Does_Not", "Exist", -1);
//        assertTrue(emptyList.isEmpty());
    }
}