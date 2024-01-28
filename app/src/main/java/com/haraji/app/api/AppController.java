package com.haraji.app.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AppController {

    @RequestMapping("/")
    public String hello()
    {
        return "Hello User";
    }


}
