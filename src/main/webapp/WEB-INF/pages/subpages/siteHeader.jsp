<header>
    <p style="text-align:left;">
        <label style="font-weight: bold; font-size: 300%;">EMRMS</label>
        <span style="float:right;">
        <a href="/emrms/loginProcess" class="linkColor">Home</a>&nbsp;&nbsp;
        <a href="/emrms/patientLocator" class="linkColor">Patient Locator</a>&nbsp;&nbsp;
        <a href="/emrms/policies" class="linkColor">Policies</a>&nbsp;&nbsp;
        <a href="/emrms/logout" class="linkColor">Logout</a>
        </span>
    </p>

    <form:form modelAttribute="siteHeader">
        <hr/>

        <div>
            <h3> ${physicianName} ${clinicName}</h3>
        </div>

        <hr/>

        <c:if test="${showHeader == 'true'}">
            <div style="display: flex;">
                <div class="flexColumn">
                    <form:label class="leftLabel" path="nameLastCommaFirst">Patient: </form:label>
                    <form:label class="leftLabel" path="encounterType">Enc Type: </form:label>
                </div>

                <div class="flexColumn">
                    <form:input class="rightInput" path="nameLastCommaFirst" disabled="true"/>
                    <form:input class="rightInput" path="encounterType" disabled="true"/>
                </div>

                <div class="flexColumn">
                    <form:label class="leftLabel" path="birthDate">DOB: </form:label>
                    <form:label class="leftLabel" path="encounterStatus">Enc Status: </form:label>
                </div>

                <div class="flexColumn">
                    <form:input class="rightInput" id="birthInput" path="birthDate" disabled="true"/>
                    <form:input class="rightInput" id="statusInput" path="encounterStatus" disabled="true"/>
                </div>

                <div class="flexColumn">
                    <form:label class="leftLabel" path="attending">Attending: </form:label>
                    <form:label class="leftLabel" path="encounterStartDate">Enc Start Date: </form:label>
                </div>

                <div class="flexColumn">
                    <form:input class="rightInput" id="attendingInput" path="attending" disabled="true"/>
                    <form:input class="rightInput" id="startDateInput" path="encounterStartDate" disabled="true"/>
                </div>

                <div class="flexColumn">
                    <form:label class="leftLabel" path="mrNumber">MR #: </form:label>
                    <form:label class="leftLabel" path="encounterNumber">Enc #: </form:label>
                </div>

                <div class="flexColumn">
                    <form:input class="rightInput" id="mrNumberInput" path="mrNumber" disabled="true"/>
                    <form:input class="rightInput" id="encNumberInput" path="encounterNumber" disabled="true"/>
                </div>

                <div class="flexColumn">
                    <form:label class="leftLabel" path="ignoredSelectedAllergy">Allergies: </form:label>
                    <form:label class="leftLabel" path="ignoredSelectedDiagnosis">Diagnoses: </form:label>
                </div>

                <div class="flexColumn">
                    <form:select class="rightInput" id="allergiesInput" path="ignoredSelectedAllergy"
                                 items="${severeAllergyList}"/>
                    <form:select class="rightInput" id="diagnosesInput" path="ignoredSelectedDiagnosis"
                                 items="${primaryDiagnosisList}"/>
                </div>

                <div class="flexColumn">
                    <a href="/emrms/charting">
                        <img src="https://www.webpt.com/sites/default/files/images/icon-documentation.png"
                             alt="Charting"
                             style="width: 25px; height:auto; border:0; background-color: white;"/>
                    </a>
                    <label> </label>
                </div>
            </div>
        </c:if>
    </form:form>
</header>
