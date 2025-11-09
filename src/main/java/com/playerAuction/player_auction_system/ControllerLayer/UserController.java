package com.playerAuction.player_auction_system.ControllerLayer;


import com.playerAuction.player_auction_system.Model.Users;
import com.playerAuction.player_auction_system.ServiceLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public String addUser(@RequestBody Users user){
        userService.addUser(user);
        return "User added successfully";
    }
}
