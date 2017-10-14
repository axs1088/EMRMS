package edu.psu.sweng500.emrms.format;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

public class EMRMSCustomEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	       super.setValue(Integer.parseInt(text));
    }

    @Override
    public String getAsText() {
        return ((Number)this.getValue()).toString();
    }    

}
