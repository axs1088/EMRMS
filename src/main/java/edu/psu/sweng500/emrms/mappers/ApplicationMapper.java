package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.HPerson;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import edu.psu.sweng500.emrms.model.User;
public interface ApplicationMapper {
		
	@Select("SELECT HPERSONID as personId, USERID as userId, "
			+ "CREATIONDATETIME as creationDateTime, GENDER as gender, "
			+ "BIRTHDATE as birthDate, RACE as race FROM h_person")
	public List<HPerson> getPersonDetails();
		
	@Insert("INSERT INTO h_person(HPERSONID, userId, creationDateTime,"
			+ "gender, birthDate, race) VALUES"
			+ "(#{personId}, #{userId}, #{creationDateTime}, #{gender},"
			+ "#{birthDate}, #{race})")
	//@Options(useGeneratedKeys=true, keyProperty="id", flushCache=true, keyColumn="id")
	public void insertPersonDetails(HPerson person);

	@Select("SELECT LoginId as LoginId, UserType as UserType, "
			+ " HPersonID as HPersonID FROM h_User "
            + " where loginid = #{loginID}")
	public User validateUser(String loginID);
	
	//user object id
	@Select(value= "{ CALL  Emrms_GetPhyciainCensusList(#{userObjectID, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<HCensus> getPhysicianCensus(Integer userObjectID);
}

