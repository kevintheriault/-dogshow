package ca.sheridan.theriake.dogshows.repositories;

import ca.sheridan.theriake.dogshows.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SecurityRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

//    Find user by username in database.
    public User findUser(String userName){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM SEC_USER WHERE userName=:user";
        parameters.addValue("user", userName);
        ArrayList<User> usersList = (ArrayList<User>)
                jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
        if (!usersList.isEmpty()){
            return usersList.get(0);
        }else{
            return null;
        }
    }

    public List<String> getRolesById(long userId){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT user_role.userId, sec_role.roleId, sec_role.roleName FROM "
                + "user_role, sec_role WHERE user_role.roleid=sec_role.roleid "
                + "AND userid=:id";
        parameters.addValue("id", userId);

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        ArrayList<String> roles = new ArrayList<String>();

        for(Map<String, Object> row : rows){
            String role = (String)row.get("roleName");
            roles.add(role);
        }
        return roles;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(String username, String password){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO sec_user (username, encryptedPassword, ENABLED)"
                + "VALUES(:name, :pass, 1)";

        parameters.addValue("name", username);
        parameters.addValue("pass", passwordEncoder.encode(password));

        jdbc.update(query, parameters);
    }

    public void addRole(long userId, long roleId){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO user_role (userId, roleId)"
                + " VALUES (:userId, :roleId)";
        parameters.addValue("userId", userId);
        parameters.addValue("roleId", roleId);
        jdbc.update(query, parameters);

    }
}
