<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>

<style>
    .topcorner {
        position: absolute;
        top: 0;
        right: 0;
        margin-right: 50px;
    }
</style>
<div class="topcorner">
    <a href="/emrms/loginProcess" class="linkColor">Home</a>&nbsp;&nbsp;
    <a href="/emrms/patientLocator" class="linkColor">Patient Locator</a>&nbsp;&nbsp
    <a href="/emrms/logout" class="linkColor">Logout</a>
</div>

<form:form modelAttribute="siteHeader">
    <div>
        <style>
            #emrmsLabel {
                width: 63px
            }
        </style>
        <label id="emrmsLabel">EMRMS</label>
        <label>${physicianName}</label>
        <label>${clinicName}</label>
    </div>
    <hr/>
    <div>
        <style>
            #nameLabel {
                width: 80px
            }
        </style>
        <form:label id="nameLabel" path="nameLastCommaFirst">Patient: </form:label>
        <form:input path="nameLastCommaFirst" readonly="true"/>

        <style>
            #birthLabel {
                width: 110px;
            }

            #birthInput {
                width: 93px;
            }
        </style>
        <form:label id="birthLabel" path="birthDate">DOB: </form:label>
        <form:input id="birthInput" path="birthDate" readonly="true"/>

        <style>
            #attendingLabel {
                width: 140px;
            }

            #attendingInput {
                width: 110px;
            }
        </style>
        <form:label id="attendingLabel" path="attending">Attending: </form:label>
        <form:input id="attendingInput" path="attending" readonly="true"/>

        <style>
            #mrNumberLabel {
                width: 73px;
            }

            #mrNumberInput {
                width: 110px;
            }
        </style>
        <form:label id="mrNumberLabel" path="mrNumber">MR #: </form:label>
        <form:input id="mrNumberInput" path="mrNumber" readonly="true"/>

        <style>
            #allergiesLabel {
                width: 107px;
            }

            #allergiesInput {
                background-color: #505d5c;
                color: white;
                width: auto;
            }
        </style>
        <form:label id="allergiesLabel" path="ignoredSelectedAllergy">Allergies: </form:label>
        <form:select id="allergiesInput" path="ignoredSelectedAllergy" items="${severeAllergyList}"/>

        <a href="/emrms/charting">
            <img src="https://www.webpt.com/sites/default/files/images/icon-documentation.png" alt="Charting"
                 style="width: 25px; height:auto; border:0; background-color: white;"/>
        </a>
    </div>

    <div>
        <style>
            #typeLabel {
                width: 80px;
            }
        </style>
        <form:label id="typeLabel" path="encounterType">Enc Type: </form:label>
        <form:input path="encounterType" readonly="true"/>

        <style>
            #statusLabel {
                width: 110px;
            }

            #statusInput {
                width: 93px;
            }
        </style>
        <form:label id="statusLabel" path="encounterStatus">Enc Status: </form:label>
        <form:input id="statusInput" path="encounterStatus" readonly="true"/>

        <style>
            #startDateLabel {
                width: 140px;
            }

            #startDateInput {
                width: 110px;
            }
        </style>
        <form:label id="startDateLabel" path="encounterStartDate">Enc Start Date: </form:label>
        <form:input id="startDateInput" path="encounterStartDate" readonly="true"/>


        <style>
            #encNumberLabel {
                width: 73px;
            }

            #encNumberInput {
                width: 110px;
            }
        </style>
        <form:label id="encNumberLabel" path="encounterNumber">Enc #: </form:label>
        <form:input id="encNumberInput" path="encounterNumber" readonly="true"/>

        <style>
            #diagnosesLabel {
                width: 107px;
            }

            #diagnosesInput {
                background-color: #505d5c;
                color: white;
                width: auto;
            }
        </style>
        <form:label id="diagnosesLabel" path="ignoredSelectedDiagnosis">Diagnoses: </form:label>
        <form:select id="diagnosesInput" path="ignoredSelectedDiagnosis" items="${primaryDiagnosisList}"/>
    </div>
    <hr/>
</form:form>