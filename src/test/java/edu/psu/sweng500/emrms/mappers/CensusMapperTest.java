package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
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
    public static void callSchemaScript() throws SQLException, IOException {
        CallSchemaScript.execute();
    }

    @Test
    public void testThatSchemaScriptClearsAllTables() {
        // Seefried: Unfortunately, we can't test this until the stored procedures scripts are executed properly
        List<HCensus> emptyList = mapper.getPhysicianCensus(0);
        assertTrue(emptyList.isEmpty());

        emptyList = mapper.getNurseCensus(-1);
        assertTrue(emptyList.isEmpty());

        emptyList = mapper.getPatientListByDemographics("Does_Not", "Exist", -1);
        assertTrue(emptyList.isEmpty());
    }
}