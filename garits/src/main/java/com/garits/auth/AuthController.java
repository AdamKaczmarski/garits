package com.garits.auth;

import com.garits.user.GaritsUserDetails;
import com.garits.user.Role;
import com.garits.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);

        GaritsUserDetails garitsUserDetails = (GaritsUserDetails) auth.getPrincipal();
        List<String> roles = garitsUserDetails.getAuthorities().stream()
                .map(item->item.getAuthority()).collect(Collectors.toList());
        for (String role : roles) {
            System.out.println(role);
        }
        return ResponseEntity.ok(new JwtResponse(jwt,garitsUserDetails.getIdUser(),garitsUserDetails.getEmail(),roles));
    }
}
