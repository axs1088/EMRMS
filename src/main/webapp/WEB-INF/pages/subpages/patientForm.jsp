<h4> Patient Information</h4>

<div style="display: flex;">
    <div class="flexColumn">
        <form:label cssClass="leftLabel" path="name.first">First Name:
            <mandatory>*</mandatory>
        </form:label>
        <form:label cssClass="leftLabel" path="name.middle">Middle Name: </form:label>
        <form:label cssClass="leftLabel" path="birthDate">Birth Date:
            <mandatory>*</mandatory>
        </form:label>
    </div>

    <div class="flexColumn">
        <form:input cssClass="rightInput" path="name.first"/>
        <form:input cssClass="rightInput" path="name.middle"/>
        <form:input cssClass="rightInput" type="date" path="birthDate"/>
    </div>

    <div class="flexColumn">
        <form:label cssClass="leftLabel" path="name.last">Last Name:
            <mandatory>*</mandatory>
        </form:label>
        <form:label cssClass="leftLabel" path="gender">Gender:
            <mandatory>*</mandatory>
        </form:label>
    </div>

    <div class="flexColumn">
        <form:input cssClass="rightInput" path="name.last" id="lastNameTxt"/>
        <form:select path="gender" id="genderTxt">
            <form:option value="1" label="Male"/>
            <form:option value="2" label="Female"/>
            <form:option value="3" label="Other"/>
        </form:select>
    </div>
</div>

<h4> Address</h4>

<div style="display: flex;">
    <div class="flexColumn">
        <form:label cssClass="leftLabel" path="address.line1">Street Address: </form:label>
        <form:label cssClass="leftLabel" path="address.city">City/State/Zip: </form:label>
        <form:label cssClass="leftLabel" path="address.line1">Mailing Address: </form:label>
        <form:label cssClass="leftLabel" path="homePhone.number">Home Phone: </form:label>
        <form:label cssClass="leftLabel" path="cellPhone.number">Cell Phone: </form:label>
        <form:label cssClass="leftLabel" path="email">Email: </form:label>
    </div>

    <div class="flexColumn">
        <form:input cssClass="rightInput" id="addressInput" path="address.line1"/>
        <div>
            <form:input cssClass="rightInput" path="address.city"/>
            <form:select path="address.state">
                <form:option value="Alabama"/>
                <form:option value="Alaska"/>
                <form:option value="Arizona"/>
                <form:option value="Arkansas"/>
                <form:option value="California"/>
                <form:option value="Colorado"/>
                <form:option value="Connecticut"/>
                <form:option value="Delaware"/>
                <form:option value="Florida"/>
                <form:option value="Georgia"/>
                <form:option value="Hawaii"/>
                <form:option value="Idaho"/>
                <form:option value="Illinois"/>
                <form:option value="Indiana"/>
                <form:option value="Iowa"/>
                <form:option value="Kansas"/>
                <form:option value="Kentucky"/>
                <form:option value="Louisiana"/>
                <form:option value="Maine"/>
                <form:option value="Maryland"/>
                <form:option value="Massachusetts"/>
                <form:option value="Michigan"/>
                <form:option value="Minnesota"/>
                <form:option value="Mississippi"/>
                <form:option value="Missouri"/>
                <form:option value="Montana"/>
                <form:option value="Nebraska"/>
                <form:option value="Nevada"/>
                <form:option value="New Hampshire"/>
                <form:option value="New Jersey"/>
                <form:option value="New Mexico"/>
                <form:option value="New York"/>
                <form:option value="North Carolina"/>
                <form:option value="North Dakota"/>
                <form:option value="Ohio"/>
                <form:option value="Oklahoma"/>
                <form:option value="Oregon"/>
                <form:option value="Pennsylvania"/>
                <form:option value="Rhode Island"/>
                <form:option value="South Carolina"/>
                <form:option value="South Dakota"/>
                <form:option value="Tennessee"/>
                <form:option value="Texas"/>
                <form:option value="Utah"/>
                <form:option value="Vermont"/>
                <form:option value="Virginia"/>
                <form:option value="Washington"/>
                <form:option value="West Virginia"/>
                <form:option value="Wisconsin"/>
                <form:option value="Wyoming"/>
            </form:select>
            <form:input cssClass="rightInput" id="zipInput" path="address.zip"/>
        </div>
        <div>
            <form:checkbox cssClass="rightInput" path="address.mailingAddrSameAsHomeAddr" value="1"/>
            <label class="leftLabel">Same as home</label>
        </div>
        <form:input cssClass="rightInput" path="homePhone.number"/>
        <form:input path="cellPhone.number" id="cellphoneTxt"/>
        <form:input path="email" id="emailTxt"/>
    </div>
</div>

<h4> Miscellaneous</h4>

<div style="display: flex;">
    <div class="flexColumn">
        <form:label cssClass="leftLabel" path="mPINumber">MPI Number: </form:label>
        <form:label cssClass="leftLabel" path="organDonor">Organ Donor: </form:label>
    </div>
    <div class="flexColumn">
        <form:input cssClass="rightInput" path="mPINumber"/>
        <form:select path="organDonor">
            <form:option value="true" label="Yes"/>
            <form:option value="false" label="No"/>
        </form:select>
    </div>
</div>