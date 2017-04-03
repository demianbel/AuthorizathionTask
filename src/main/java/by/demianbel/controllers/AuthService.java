package by.demianbel.controllers;

import by.demianbel.DBService.DataBaseService;
import by.demianbel.DBService.domain.StatusResponse;
import by.demianbel.DBService.domain.UserDataSet;
import by.demianbel.exceptions.DataNotMatchException;
import by.demianbel.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AuthService {

    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private StatusResponse statusResponse;
    //get user by id if exist
    @RequestMapping(method = RequestMethod.GET, name = "/user")
    public UserDataSet getUserById(@RequestParam(value = "id") Long id){
        UserDataSet user = dataBaseService.getUserById(id);
        if (user != null){
            return user;
        } else {
            throw new UserNotFoundException("User not found by id " + id);
        }
    }
    //create new user
    @RequestMapping(method = RequestMethod.POST, name = "/user")
    public String saveUser(@NonNull @RequestBody String userJSON) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDataSet user;
        try {
            user = objectMapper.readValue(userJSON, UserDataSet.class);
        } catch (IOException e) {
           throw new IOException("input-output error");
        }
        if (user != null && (user.getOnlineStatus().equals("Online") || user.getOnlineStatus().equals("Offline"))) {
            return "{id:" + dataBaseService.createUser(user) + "}";
        } else throw new DataNotMatchException("status must be Online or Offline");
    }
    //set user status
    @RequestMapping(method = RequestMethod.PUT, name = "/user")
    public StatusResponse changeStatus(@NonNull @RequestParam(value = "id") Long id,@NonNull @RequestParam(value = "status") String newStatus) throws Exception {
        if(newStatus.equals("Offline") || newStatus.equals("Online")) {
            UserDataSet user = dataBaseService.getUserById(id);
            if (user == null) throw new UserNotFoundException("User not found by id " + id);
            statusResponse.setId(id);
            statusResponse.setOldStatus(user.getOnlineStatus());
            statusResponse.setNewStatus(dataBaseService.updateStatus(user, newStatus));
            return statusResponse;
        }else throw new DataNotMatchException("status must be Online or Offline");
    }
}
