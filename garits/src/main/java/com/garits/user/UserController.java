package com.garits.user;

import com.garits.exceptions.NotFound;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // GET MAPPINGS

    /**
     * This method returns all users in the system.
     *
     * @return Array of User objects.
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/mechanics")
    Iterable<User> getAllMechanics() {
        return userRepository.findAllMechanics();
    }

    /**
     * Get single user
     *
     * @param id - user's id
     * @return User object
     */
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    User one(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFound("Could not find user: " + id));
    }
    //POST MAPPINGS

    /**
     * Add new user.
     *
     * @param newUser - user object
     * @return created user object
     */
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<User> newUser(@RequestBody User newUser) {
        if (userRepository.checkEmail(newUser.getEmail()) == null || userRepository.checkEmail(newUser.getEmail()) != 1) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String randPass = RandomString.make(16);
        System.out.println(randPass);
        newUser.setPassword(encoder.encode(randPass));
            System.out.println("SEND EMAIL TO THE USER WITH GENERATED PASSWORD");
            userRepository.addUser(newUser.getEmail(), newUser.getPassword(), newUser.getFirstName(), newUser.getLastName());
            Integer id = userRepository.getUserId(newUser.getEmail());
            userRepository.addUserRole(id, newUser.getRoles().iterator().next().getRoleName());
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.findById(id).orElseThrow(() -> new NotFound("Couldn't add user")));

        }
        return ResponseEntity.status(400).body(null);
    }

    //PATCH MAPPINGS

    /**
     * Update user's role
     * @param newRole new Role object to assign to the user
     * @param idUser id of the user
     */
    @PatchMapping("/users/{idUser}/role")
    @PreAuthorize("hasRole('ADMIN')")
    void changeRole(@RequestBody Role newRole, @PathVariable Integer idUser) {
        /*return userRepository.findById(id).map(user -> {
            if (newRole != null) user.changeRole(newRole);
            return userRepository.save(user);
        }).orElseThrow(() -> new NotFound("Could not find user: " + id));*/
        System.out.println(newRole.getRoleName());
        userRepository.changeUserRole(idUser, newRole.getRoleName());
    }

    /**
     * Reset user's password to a random one.
     * @param idUser id of the user
     */
    @PatchMapping("/users/{idUser}/password")
    @PreAuthorize("hasRole('ADMIN')")
    void resetPassword(@PathVariable Integer idUser) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new NotFound("Could not find user: " + idUser));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String randPass = RandomString.make(16);
        System.out.println(randPass);
        u.setPassword(encoder.encode(randPass));
        System.out.println("SEND EMAIL TO THE USER WITH GENERATED PASSWORD");
        userRepository.save(u); 
    }

    /*
     */
/**
 * EDIT USER
 *
 * @param id - Edited user's id
 *//*

    @PatchMapping("/users/{id}")
    User editUser(@RequestBody User editedUser, @PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            if (editedUser.getEmail()!=null)user.setEmail(editedUser.getEmail());
            if (editedUser.getFirstName()!=null)user.setFirstName(editedUser.getFirstName()z);
            if(editedUser.getLastName()!=null)user.setLastName(editedUser.getLastName());
            return userRepository.save(user);
        }).orElseThrow(() -> new NotFound("Could not find user: " + id));
    }
*/
    //DELETE MAPPINGS

    /**
     * Deletes the user
     *
     * @param id - user ID
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
