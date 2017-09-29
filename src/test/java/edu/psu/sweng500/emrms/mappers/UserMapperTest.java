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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
public class UserMapperTest {
    @Autowired
    @Qualifier("userMapper")
    UserMapper mapper;

    @BeforeClass
    public static void callSchemaScript() throws FileNotFoundException, SQLException {
        CallSchemaScript.execute();
    }

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    private void verifySpringAnnotations() {
        assertThat(mapper, instanceOf(UserMapper.class));
    }

    @Test
    public void validateUser() throws Exception {
        assertNull(mapper.validateUser("doesNotExist", "badPassword"));
    }

}