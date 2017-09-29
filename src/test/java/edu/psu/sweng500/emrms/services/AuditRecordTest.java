package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.service.AuditEventService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})

public class AuditRecordTest {

    @Autowired
    @Qualifier("auditEventService")

    private AuditEventService service;

    @Before
    public void setUp() { verifySpringAnnotations();
    }

    @After
    public void tearDown() {
        //ToDO
        //delete from h_encounter where HEncounterID = 1001;
    }

    public void verifySpringAnnotations() {
        assertThat(service, instanceOf(AuditEventService.class));
    }

    @Test
    public void testAddAnAuditRecord() {
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Login");
        auditRecord.setPolicyId(101);
        auditRecord.setUserId("admin");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        auditRecord.setCreationDateTime(dateFormat.format(date));

        int returnValue = service.auditEvent(auditRecord);
        assertEquals(0,returnValue);
    }
}
