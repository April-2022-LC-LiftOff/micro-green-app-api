package ingis.microgreenappapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SeedController {

    @GetMapping("seeds")
    @ResponseBody
    public String seeds() {
        return "seeds";
    }

    @GetMapping("inventory/seeds")
    @ResponseBody
    public String seedsInInventory() {

        return "seeds in inventory";
    }



}
