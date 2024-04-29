package dev.james.restfulwebservice.helloworld;

//Expose RestApi
//Give it a specific url

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/")
@RestController
public class HelloWorldController {


    @GetMapping("root")
    public String helloWorld(){
        return "Hello World People ";
    }

    @GetMapping("helloworld/{names}")
    public String helloWorldPaths(
            @PathVariable(name = "names")
            String name
    ){
        return name;
    }
}
