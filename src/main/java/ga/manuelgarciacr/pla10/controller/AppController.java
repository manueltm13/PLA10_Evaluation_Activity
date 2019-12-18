package ga.manuelgarciacr.pla10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ga.manuelgarciacr.pla10.model.Center;
import ga.manuelgarciacr.pla10.model.User;
import ga.manuelgarciacr.pla10.model.dao.ICenterDAO;
import ga.manuelgarciacr.pla10.model.dao.IUserDAO;

@Controller
public class AppController {
	@Autowired
	private ICenterDAO centerService;
	
	@GetMapping("/intranet")
	public String intranet(Model modelo) {
		return "intranet";
	}

	@GetMapping("/administracion")
	public String administracion(Model modelo) {
		return "administracion";
	}

	@GetMapping("/edicion")
	public String edicion(Model modelo) {
		return "edicion";
	}

}
