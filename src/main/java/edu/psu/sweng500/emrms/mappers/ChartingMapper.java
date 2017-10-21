package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HAllergy;
import edu.psu.sweng500.emrms.model.HDiagnosis;
import org.apache.ibatis.annotations.*;

public interface ChartingMapper {

    String UPDATE_HDIAGNOSIS = "update h_diagnosis set code=#{code},description=#{description} where hdiagnosisid=#{hDiagnosisId}";
    String DELETE_HDIAGNOSIS = "DELETE FROM h_diagnosis WHERE hdiagnosisid=#{hDiagnosisId}";
    String DELETE_HALLERGY = "DELETE FROM h_Allergy WHERE HAllergyID=#{allergyID}";

    @Insert("INSERT INTO h_diagnosis(UserId, Code, Description,EncounterID,PatientID  ) " +
            "VALUES (#{userId}, #{code},#{description}, #{encounterID}, #{patientID})")
    public void addDiagnosis(HDiagnosis diagnosis);

    @Update(UPDATE_HDIAGNOSIS)@Options(useGeneratedKeys = true, keyProperty = "hDiagnosisId" )
    public void reviseDiagnosis(HDiagnosis diagnosis) throws Exception;

    @Delete(DELETE_HDIAGNOSIS) @Options(keyProperty = "hDiagnosisId" )
    public int deleteDiagnosis(HDiagnosis diagnosis) throws Exception;

    @Insert("INSERT INTO h_Allergy(UserId, AllergyName, AllergyCode, AllergyType, PatientID, Severity  ) " +
            "VALUES (#{userId}, #{allergyName},#{allergyCode}, #{allergyType}, #{patientID}, #{severity})")
    public void addAllergy(HAllergy allergy);

    @Delete(DELETE_HALLERGY) @Options(keyProperty = "allergyID" )
    public int deleteAllergy(HAllergy allergy) throws Exception;

}