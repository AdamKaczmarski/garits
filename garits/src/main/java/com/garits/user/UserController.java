package com.garits.user;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
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
    User one(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFound("Could not find user: " + id));
    }
    //POST MAPPINGS

    /**
     * Add new user mapping.
     *
     * @param newUser - user object
     * @return
     */
    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        newUser.setPassword("1234");
        newUser.setSalt("1234");
        userRepository.addUser(newUser.getEmail(), newUser.getPassword(), newUser.getSalt(), newUser.getFirstName(), newUser.getLastName());
        Integer id = userRepository.getUserId(newUser.getEmail());
        userRepository.addUserRole(id,newUser.getRoles().iterator().next().getRoleName());
        return userRepository.findById(id).orElseThrow(()->new NotFound("Couldn't add user"));
    }

    //PATCH MAPPINGS

    /**
     * Change user's role
     *
     * @return
     */
    @PatchMapping("/users/{idUser}/role")
    void changeRole(@RequestBody Role newRole, @PathVariable Integer idUser) {
        /*return userRepository.findById(id).map(user -> {
            if (newRole != null) user.changeRole(newRole);
            return userRepository.save(user);
        }).orElseThrow(() -> new NotFound("Could not find user: " + id));*/
        System.out.println(newRole.getRoleName());
        userRepository.changeUserRole(idUser,newRole.getRoleName());
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
    void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
