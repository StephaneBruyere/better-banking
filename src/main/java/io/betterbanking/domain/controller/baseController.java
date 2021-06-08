package io.betterbanking.domain.controller;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class baseController {
	
	@GetMapping(path = "/")
    public String index(Model model, Principal principal) {
		if(principal != null)
			model.addAttribute("name",principal.getName());
        return "index";
    }
	
	@GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "index";
	}

}
