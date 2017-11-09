package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HProblem;
import edu.psu.sweng500.emrms.service.ManageProblemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
public class ManageProblemServiceTest {
    @Autowired
    @Qualifier("manageProblemService")

    private ManageProblemService service;

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    @After
    public void tearDown() {
        //ToDO
    }

    public void verifySpringAnnotations() {
        assertThat(service, instanceOf(ManageProblemService.class));
    }

    @Test
    public void testAddReviseDeleteProblem() {
        HProblem problem = new HProblem();

        problem.setUserId("admin");
        problem.setCode("520.124");
        problem.setDescription("Headache");
        problem.setStatus("Active");
        problem.setPriority(1);
        problem.setEncounterID(1);
        problem.setPatientID(3);

        // ToDo
        int returnValue = service.AddProblem(problem);
        assertEquals(0,returnValue);

        int problemObjectId = problem.getObjectId();
        problem.setObjectId(problemObjectId);
        problem.setCode("521.113");
        problem.setDescription("Headache revised");
        problem.setPriority(1);
        problem.setStatus("Inactive");

        returnValue = service.ReviseProblem(problem);
        assertEquals(0,returnValue);

        List<HProblem>problems = service.GetProblemsList(problem.getPatientID(), problem.getEncounterID());
        assertEquals(1,problems.size());

        problem.setObjectId(problemObjectId);
        returnValue = service.DeleteProblem(problem);
        assertEquals(0,returnValue);
    }

}
