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

    /**
     * Get single user
     *
     * @param id - user's id
     * @return User object
     */
    @GetMapping("/users/{id}")
    User one(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFound("Could not find user: "+id));
    }
    //POST MAPPINGS

    /**
     * Add new user mapping.
     *
     * @param newUser - user object
     * @return
     */
    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    //PUT MAPPINGS

    /**
     * Change user's role
     *
     * @return
     */
    @PutMapping("/user/{id}/role")
    User changeRole(@RequestBody Role newRole, @PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            user.changeRole(newRole);
            return userRepository.save(user);
        }).orElseThrow(() -> new NotFound("Could not find user: "+id));
    }

    /**
     * EDIT USER
     *
     * @param id - Edited user's id
     */
    @PutMapping("/user/{id}")
    User editUser(@RequestBody User editedUser, @PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(editedUser.getEmail());
            user.setFirstName(editedUser.getFirstName());
            user.setLastName(editedUser.getLastName());
            return userRepository.save(user);
        }).orElseThrow(() -> new NotFound("Could not find user: "+id));
    }
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
