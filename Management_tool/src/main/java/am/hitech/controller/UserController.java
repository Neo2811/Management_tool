package am.hitech.controller;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDto;
import am.hitech.service.UserService;
import am.hitech.util.exception.DuplicateException;
import am.hitech.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<User> getById(@RequestParam int id) throws NotFoundException {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getOnlyActiveUsers() {
        List<User> list = userService.getOnlyActiveUsers();
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAuthority('HR') OR hasAuthority('PM')")
    @GetMapping("/users/hr-pm")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String email) {

        List<User> users = userService.searchUsers(firstName, lastName, email);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid UserRequestDto requestDto) throws DuplicateException {

        userService.create(requestDto);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/edit")
    public ResponseEntity<Void> edit(@RequestParam String name,
                                     @RequestParam String surname){
        userService.edit(name, surname, getCurrentUsername());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('HR')")
    @PatchMapping("/change")
    public ResponseEntity<Void> change(@RequestParam int id){
        userService.change(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/code")
    public ResponseEntity<Void> code(){
        Random random = new Random();

        String code = UUID.randomUUID().toString();
        userService.code(code, getCurrentUsername());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/verification")
    public ResponseEntity<Void> verification(@RequestParam String code){
        User user = userService.findByEmail(getCurrentUsername());
        if (code.equals(user.getVerificationCode())){
            userService.verification(getCurrentUsername());
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/token")
    public ResponseEntity<Void> token(){
        Random random = new Random();

        int token = random.nextInt(200 + 100000);
        userService.token(token, getCurrentUsername());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestParam int token,
                                               @RequestParam String password,
                                               @RequestParam String matchPassword){

        User user = userService.findByEmail(getCurrentUsername());

        if (token == user.getPasswordToken() && password.equals(matchPassword)){
            userService.changePassword(passwordEncoder.encode(password), getCurrentUsername());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/currentUsername")
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String username = authentication.getName();
        return username;
    }
}
