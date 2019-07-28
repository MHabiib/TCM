package com.future.tcfm.controller;

import com.future.tcfm.model.User;
import com.future.tcfm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.future.tcfm.config.SecurityConfig.getCurrentUser;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity loadAll() {
        return ResponseEntity.ok(userService.loadAll());
    }

    @GetMapping("/search")
    public Page<User> searchUser(
            @RequestParam("name") String name,
            @RequestParam(value = "groupName",required = false) String groupName,
            @RequestParam(value = "page",required = false, defaultValue = "0") int page,
            @RequestParam(value = "size",required = false, defaultValue = "10") int size)  {
        return userService.searchByNameAndGroupName(name,getCurrentUser().getGroupName(),page,size);
    }

    @GetMapping("/email") // ini seharusnya gk usah, cukup @GetMapping aja gmn? biar jadi /api/user?email=value
    public User getUser(@RequestParam("email") String email) {
        return userService.getUser(email);
    }

    @GetMapping("/{id}") // ini seharusnya gk usah, cukup @GetMapping aja gmn? biar jadi /api/user?email=value
    public ResponseEntity getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(
            @Nullable @RequestPart("file") MultipartFile file,
            @RequestPart("user") String userJSONString
    ) throws IOException {
        return userService.createUserV2(userJSONString, file);
    }

    /**
     * api dibawah untuk SUPER_ADMIN yang mengupdate data user scr eksplisit
     * @param id
     * @param user
     * @return
     * @throws IOException
     */
    @PutMapping(value = "/managementUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(
            @PathVariable("id") String id,
            @RequestBody User user
    ) throws IOException {
        return userService.manageUser(id,user);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(
            @PathVariable("id") String id,
            @Nullable @RequestPart("file") MultipartFile file,
            @RequestPart("user") String userJSONString
    ) throws IOException {
        return userService.updateUserV2(id, userJSONString, file);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(String email) {
        return userService.deleteUser(email);
    }

}


//    @DeleteMapping(value = "/user/{id}",produces = MediaType.APPLICATION_JSON_VALUE  )
//    public ResponseEntity delete(@PathVariable("id")int id){
//        return userService.deleteUser(id);
//    }
//}
//    @PutMapping("/{id}")
//    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody User user) {
//        return userService.updateUser(id,user);
//    }