package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HAllergy;
import edu.psu.sweng500.emrms.model.HAssessment;
import edu.psu.sweng500.emrms.model.HDiagnosis;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface ChartingMapper {

    String UPDATE_HDIAGNOSIS = "update h_diagnosis set code=#{code},description=#{description} where hdiagnosisid=#{diagnosisObjectId}";
    String DELETE_HDIAGNOSIS = "DELETE FROM h_diagnosis WHERE hdiagnosisid=#{diagnosisObjectId}";
    String DELETE_HALLERGY = "DELETE FROM h_Allergy WHERE HAllergyID=#{allergyID}";

    @Insert("INSERT INTO h_diagnosis(UserId, Code, Description, Priority, EncounterID,PatientID  ) " +
            "VALUES (#{userId}, #{code},#{description}, #{priority}, #{encounterID}, #{patientID})")
    public void addDiagnosis(HDiagnosis diagnosis);

    @Update(UPDATE_HDIAGNOSIS)
    @Options(useGeneratedKeys = true, keyProperty = "hDiagnosisId")
    public void reviseDiagnosis(HDiagnosis diagnosis) throws Exception;

    @Delete(DELETE_HDIAGNOSIS)
    @Options(keyProperty = "hDiagnosisId")
    public int deleteDiagnosis(HDiagnosis diagnosis) throws Exception;

    @Insert("INSERT INTO h_Allergy(UserId, AllergyName, AllergyCode, AllergyType, PatientID, Severity  ) " +
            "VALUES (#{userId}, #{allergyName},#{allergyCode}, #{allergyType}, #{patientID}, #{severity})")
    public void addAllergy(HAllergy allergy);

    @Delete(DELETE_HALLERGY)
    @Options(keyProperty = "allergyID")
    public int deleteAllergy(HAllergy allergy) throws Exception;

    String INSERT_H_ASSESSMENT = "INSERT INTO h_Assessment(UserId, collectedDateTime, AssessmentID, Status, Encounter_ObjectID, " +
            "temperature, height, weight, pulse, systolicBP, dystolicBP, heightmeasureId, weightmeasureId, temperaturemeasureId, patient_ObjectID)" +
            "VALUES (#{assessment.userId}, #{assessment.collectedDateTime},#{assessment.assessmentId}, #{assessment.status},#{assessment.encounterObjectId}, " +
            "#{assessment.temperature}, #{assessment.height}, #{assessment.weight}, " +
            "#{assessment.pulse}, #{assessment.systolicBP}, #{assessment.dystolicBP}, #{assessment.heightmeasureId}, " +
            "#{assessment.weightmeasureId}, #{assessment.temperaturemeasureId}, #{assessment.patientObjectId})";

    @Insert(INSERT_H_ASSESSMENT)
    @Options(useGeneratedKeys = true, keyProperty = "assessment.objectId", keyColumn = "HAssessmentID")
    public void addAssessment(@Param("assessment") HAssessment assessment);

    String UPDATE_H_ASSESSMENT = "update h_Assessment set collectedDateTime = #{collectedDateTime}, Status=#{status},temperature=#{temperature}," +
            " height=#{height}, weight=#{weight}, pulse=#{pulse}, systolicBP=#{systolicBP}, " +
            "dystolicBP=#{dystolicBP} where HAssessmentID=#{objectId}";

    @Update(UPDATE_H_ASSESSMENT)
    @Options(useGeneratedKeys = true, keyProperty = "HAssessmentID")
    public void reviseAssessment(HAssessment assessment);

    @Select(value = "{ CALL emrms_getassessments(#{patientObjectId, mode=IN, jdbcType=INTEGER}," +
            "#{encounterObjectId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HAssessment> getPatientAssessments(@Param("patientObjectId")int patientObjectId, @Param("encounterObjectId")int encounterObjectId);

}