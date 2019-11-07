package com.kodilla.kodilla.diplomaBackend.service;

import com.kodilla.kodilla.diplomaBackend.domain.User;
import com.kodilla.kodilla.diplomaBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String ADMIN_LOGIN = "admin";

    public User getAdmin() {
        return userRepository.findByLogin(ADMIN_LOGIN).orElseThrow(NoSuchElementException::new);
    }

    public User findUser(long id) {

        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

/* --->>> TO DO AS A NEXT STEP OF DEVELOPMENT<<<<----

    @Autowired
    private UserRepository userRepository;

    public User registerNewUserAccount(User user) throws EmailExistsException{


        if (emailExists(user.getMail())){
            throw new EmailExistsException("There is an account with that email address: " + user.getMail());
        }

        return userRepository.save(user);


    }

    private boolean emailExists(String email) {
        return findUserByMail(email).isPresent();
    }

    public boolean confirmLogInCredentials(String email, String password) throws WrongCredentialsException{

        Optional<User> user = findUserByMail(email);
        boolean confirmation= user.isPresent() && user.get().getPassword().equals(password);
        if(!confirmation){
            throw new WrongCredentialsException("Wrong credentials. Check your email and password.");
        }
        return confirmation;

        return email.equals("user")&&password.equals("userPass") || email.equals("admin")&&password.equals("adminPass");
    }

    public Optional<User> findUserByMail(String email){
        return userRepository.findByMail(email);
    }
*/


}
