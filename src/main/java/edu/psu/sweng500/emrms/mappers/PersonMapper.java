package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HPerson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PersonMapper {

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

}

