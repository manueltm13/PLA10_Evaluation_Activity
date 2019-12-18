package ga.manuelgarciacr.pla10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String index() {
		return "redirect:/tripmemories";
	}

	@GetMapping("/tripmemories")
	public String formLogin() {
		return "login";
	}
	
	@RequestMapping("/prohibido")
	public String prohibido() {
		return "prohibido";
	}
	
}