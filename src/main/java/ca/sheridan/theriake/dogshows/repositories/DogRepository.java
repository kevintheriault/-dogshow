package ca.sheridan.theriake.dogshows.repositories;

import ca.sheridan.theriake.dogshows.beans.Dog;
import ca.sheridan.theriake.dogshows.beans.ShowData;
import ca.sheridan.theriake.dogshows.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DogRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<String> getBreedNameOptions(){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Breed";

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        ArrayList<String> breeds = new ArrayList<String>();

        for(Map<String, Object> row : rows){
            String breed = (String)row.get("breedname");
            breeds.add(breed);
        }
    return breeds;
    }

    public List<String> getBreedTypeOptions(){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT DISTINCT breedtype FROM breed";

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        ArrayList<String> types = new ArrayList<String>();

        for(Map<String, Object> row : rows){
            String breedType = (String)row.get("breedtype");
            types.add(breedType);
        }
        return types;
    }

//    GET DOGS BY CATEGORY FOR SHOW LIST
    public ArrayList<ShowData> getDogByType(String category){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT COUNT(dog.breedid) AS NumOfDogs, breedname, breedtype, " +
                "SUM(CASE WHEN dog.gender = 'male' THEN 1 ELSE 0 END) AS NumOfMales, " +
                "SUM(CASE WHEN dog.gender = 'female' THEN 1 ELSE 0 END) AS NumOfFem, " +
                "SUM(CASE WHEN dog.pathway = 'class' THEN 1 ELSE 0 END) AS NumOfClass, " +
                "SUM(CASE WHEN dog.pathway = 'speciality' THEN 1 ELSE 0 END) as NumOfSpec " +
                "FROM dog INNER JOIN breed ON dog.breedid =  breed.breedid GROUP BY " +
                "breedname HAVING breedtype = :category";

        parameters.addValue("category", category);

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        ArrayList<ShowData> showListData = new ArrayList<ShowData>();

//        TODO: Refactor to separate method.
        for(Map<String, Object> row : rows){
            ShowData data = new ShowData();
            data.setBreedType((String)row.get("breedtype"));
            data.setNumDogs((Long)row.get("NumOfDogs"));
            data.setBreed((String)row.get("breedname"));
            data.setNumMales((Long)row.get("NumOfMales"));
            data.setNumFem((Long)row.get("NumOfFem"));
            data.setNumClass((Long)row.get("NumOfClass"));
            data.setNumSpec((Long)row.get("NumOfSpec"));
            showListData.add(data);
        }
        return showListData;
    }

    public void addDog(Dog dog, User user){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO dog (dogname, ownerid, breedid, gender, pathway) "
                + "VALUES(:dogname, :ownerid, :breedid, :gender, :pathway)";
        parameters.addValue("dogname", dog.getDogName());
        parameters.addValue("ownerid", user.getUserId());
        parameters.addValue("breedid", dog.getBreed());
        parameters.addValue("gender", dog.getGender());
        parameters.addValue("pathway", dog.getPathway());

        jdbc.update(query, parameters);
    }

    public ArrayList<Dog> getUserDogs(Long userId){

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM dog INNER JOIN sec_user ON dog.OWNERID=sec_user.userid INNER JOIN breed ON " +
                "dog.breedId = breed.breedid WHERE dog.ownerid = :userid";
        ArrayList<Dog> dogList = new ArrayList<Dog>();
        parameters.addValue("userid", userId);

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

        for(Map<String, Object> row : rows){
            Dog dog = new Dog();
            dog.setDogId((Long)row.get("dogid"));
            dog.setDogName((String)row.get("dogname"));
            dog.setOwnerName((String)row.get("username"));
            dog.setBreed((String)row.get("breedname"));
            dog.setGender((String)row.get("gender"));
            dog.setPathway((String)row.get("pathway"));
            dogList.add(dog);
        }
        return dogList;
    }

    public ArrayList<Dog> getAllDogs(){

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM dog INNER JOIN sec_user ON dog.OWNERID=sec_user.userid INNER JOIN breed ON " +
                "dog.breedId = breed.breedid";
        ArrayList<Dog> dogList = new ArrayList<Dog>();

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

        for(Map<String, Object> row : rows){
            Dog dog = new Dog();
            dog.setDogId((Long)row.get("dogid"));
            dog.setDogName((String)row.get("dogname"));
            dog.setOwnerName((String)row.get("username"));
            dog.setBreed((String)row.get("breedname"));
            dog.setGender((String)row.get("gender"));
            dog.setPathway((String)row.get("pathway"));
            dogList.add(dog);
        }
        return dogList;
    }

    public void deleteDog(Long dogId){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "DELETE FROM dog WHERE dogId = :dogid";
        parameters.addValue("dogid", dogId);

        jdbc.update(query, parameters);
    }

    public boolean checkOwner(Long dogId, Long ownerId){
        Long trueOwnerId = 0L;
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT ownerId FROM dog WHERE dogId = :dogid";
        parameters.addValue("dogid", dogId);

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

        for(Map<String, Object> row : rows){
            trueOwnerId = (Long)row.get("ownerId");
        }
        System.out.println(trueOwnerId + " " + ownerId);

        if(trueOwnerId == ownerId){
            return true;
        }else{
            return false;
        }
    }

    public Dog getDogDetails(Long dogId){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM dog INNER JOIN BREED B on DOG.BREEDID = B.BREEDID " +
                "INNER JOIN SEC_USER SU on SU.USERID = DOG.OWNERID WHERE dogid = :dogid";

        parameters.addValue("dogid", dogId);

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        Dog dog = new Dog();

        for(Map<String, Object> row : rows){
            dog.setDogId((Long)row.get("dogid"));
            dog.setDogName((String)row.get("dogname"));
            dog.setOwnerName((String)row.get("username"));
            dog.setBreed((String)row.get("breedname"));
            dog.setGender((String)row.get("gender"));
            dog.setPathway((String)row.get("pathway"));
        }
        return dog;
    }

    public void editDog(Dog dog, Long userid, Long dogid){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "UPDATE dog SET dogname=:dogname, ownerid=:ownerid, breedid=:breedid, " +
                "gender=:gender, pathway=:pathway WHERE dogid=:dogid";
        parameters.addValue("dogid", dogid);
        parameters.addValue("dogname", dog.getDogName());
        parameters.addValue("ownerid", userid);
        parameters.addValue("breedid", dog.getBreed());
        parameters.addValue("gender", dog.getGender());
        parameters.addValue("pathway", dog.getPathway());

        jdbc.update(query, parameters);
    }

}
