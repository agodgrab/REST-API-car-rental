package com.kodilla.kodilla.diplomaBackend.User.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/user/")
public class UserController {

    /* --->>> TO DO AS A NEXT STEP OF DEVELOPMENT<<<<----

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    LogHistoryService logHistoryService;


    @PostMapping(value= "registration", consumes = APPLICATION_JSON_VALUE)
    public void registerUserAccount(@RequestBody UserDto userDto){

        try{
            userService.registerNewUserAccount(userMapper.mapToUser(userDto));
            logHistoryService.saveLog(userMapper.mapToUser(userDto), "Account creation.");
        }
        catch(EmailExistsException e){
        }
    }


    @GetMapping(value="logIn")
    public void logInUser(@RequestParam String mail, @RequestParam String password){

        try {
            userService.confirmLogInCredentials(mail, password);
            logHistoryService.saveLog(userService.findUserByMail(mail).get(), "Log in.");
        }
        catch(Exception e){

        }
    }

    @GetMapping(value="logOut")
    public void logOut(){

    }

*/


}
