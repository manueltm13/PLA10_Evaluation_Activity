package ga.manuelgarciacr.pla10.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ga.manuelgarciacr.pla10.model.Authority;
import ga.manuelgarciacr.pla10.model.User;
import ga.manuelgarciacr.pla10.model.dao.IAuthorityDAO;
import ga.manuelgarciacr.pla10.model.dao.IUserDAO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IUserDAO userService;
	@Autowired
	private IAuthorityDAO authorityService;

	@GetMapping("/users")
	public String users(Model modelo) {
		List<User> users = userService.getUsers();
		modelo.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping("/addUser")
	public String addUser(Model modelo) {
		User user = new User();
		List<Authority> aths = authorityService.getAuthorities();
		modelo.addAttribute("user", user);
		modelo.addAttribute("aths", aths);
		return "userForm";
	}

	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") User user, 
			BindingResult bindingResult) {
		
		if(userService.userNameExists(user.getUsername()))
			bindingResult.addError(new FieldError("user", "username", "This user (" + user.getUsername() +") already exists"));
		if(userService.userEmailExists(user.getUserEmail()))
			bindingResult.addError(new FieldError("user", "userEmail", "This email (" + user.getUserEmail() + ") already exists"));

		for(ObjectError oe: bindingResult.getAllErrors())
			System.out.println(oe.toString()+ " * " + oe.getDefaultMessage());
		if (bindingResult.hasErrors()) {
			/* bindingResult.addError(new ObjectError( )); */
			return "userForm";
		} else {
			PasswordEncoder pe = new BCryptPasswordEncoder();
			String pw = user.getPassword();
			if(pw.length() < 8)
				user.setPassword(pe.encode(pw));
			userService.save(user);
			return "redirect:/admin/users";
		}
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    binder.setBindingErrorProcessor(new DefaultBindingErrorProcessor() {
	        @Override
	        public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
	            String propertyName = ex.getPropertyName();
	            Object value = ex.getValue();
	            bindingResult.addError(new FieldError(bindingResult.getObjectName(), propertyName, value, true,
	            new String[] { "moderation.field.error" }, new Object[] { propertyName, value },
	            	//"Invalid value for " + propertyName + "(" + value + ")"));
	            	"Invalid date " + "(" + value + ")"));
	        }
	    });
	    
	}

	/*
	@GetMapping("/deleteproduct")
	public String deleteProduct(@RequestParam("idproduct") int idproduct) {
		Product product = productService.getProduct(idproduct);
		int idcategory=product.getCategory().getIdcategory();
		productService.delete(product);

		return "redirect:/products?id="+idcategory;
	}
	*/
}
