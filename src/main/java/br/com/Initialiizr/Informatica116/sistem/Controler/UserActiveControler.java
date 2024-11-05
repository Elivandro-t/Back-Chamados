package br.com.Initialiizr.Informatica116.sistem.Controler;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.UserActiveDTO;
import br.com.Initialiizr.Informatica116.sistem.Service.UserActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserActiveControler {

    private final UserActivityService userActivityService;

    public UserActiveControler(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @GetMapping("/api/user/status")
    public UserActiveDTO isUserOnline(@RequestParam String username) {
        return userActivityService.isUserOnline(username);
    }
}
