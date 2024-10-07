package first_app.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorldRestController {

    @GetMapping("/first-endpoint")
    public String hello() {
        return "hola este es mi primer get";
    }


}
