package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.mappers.AuditMapper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.Policy;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        //service.deleteAuditRecordsByPolicyId(101);

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

    @Test
    public void testGetAuditPolilcies() {
        List<Policy>policies = service.getAuditPolicies();
        assertEquals(21,policies.size());
    }

    @Test
    public void testGetAuditRecords() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar yesterday = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        tomorrow.add(Calendar.DATE, 5);

        Date fDate = yesterday.getTime(); // get a Date object
        String fromDate = dateFormat.format(fDate);

        Date tDate = tomorrow.getTime(); // get a Date object
        String toDate = dateFormat.format(tDate);


        List<HAuditRecord>auditRecords = service.getAuditRecords(fromDate,toDate,101);
        assertEquals(1,auditRecords.size());

        auditRecords = null;
        auditRecords = service.getAuditRecordsByPolicyId(101);
        assertEquals(1,auditRecords.size());

        service.deleteAuditRecordsByPolicyId(101);
    }

}
