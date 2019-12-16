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
	
	@GetMapping("/")
	public String index(Model modelo) {
		List<Center> centers = centerService.getCenters();
		modelo.addAttribute("centers", centers);
		return "index";
	}

/*
	@GetMapping("/products")
	public String productos(Model modelo, @RequestParam int id) {
		
		List<Product> products = productService.getProducts(id);
		Category category=categoryService.getCategory(id);
		modelo.addAttribute("products", products);
		modelo.addAttribute("category", category);

		return "products";
	}
*/
}
