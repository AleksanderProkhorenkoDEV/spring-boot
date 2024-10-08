package first_app.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController

public class GreatingRestController {

    @GetMapping("/dinamic/{name}")
    public String greeting(@PathVariable String name) {

        return "Hola, " + name;
    }
}