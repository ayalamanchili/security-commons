/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author k26758
 */
@RestController()
public class XssController {

    public static final String ROOT_URI = "/xss";

    @PostMapping(ROOT_URI + "/{pathVariable}")
    public String createTicket(@RequestParam(name = "requestParam", required = false) String requestParam, @PathVariable("pathVariable") String pathVariable, @RequestBody String body) {
        System.out.println("controller body:" + body);
        return body;
    }

}
