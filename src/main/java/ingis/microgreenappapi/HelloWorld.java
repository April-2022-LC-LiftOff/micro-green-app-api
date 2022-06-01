package ingis.microgreenappapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HelloWorld {

    @RequestMapping
    public String helloWorld() {
        return "Welcome to Ingi's Microgreens";
    }

    @RequestMapping("/goodbye")
    public String goodbye() {
        return"Goodbye, Farm";
    }
}