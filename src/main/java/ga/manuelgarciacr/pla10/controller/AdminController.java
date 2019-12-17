package ga.manuelgarciacr.pla10.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
		List<Authority> auths = new ArrayList<>(), allAuths = authorityService.getAuthorities();
		//List<Authority> userAuths = new ArrayList<>(user.getAuthorities());
		String rols = "";
		Boolean exists;
		for(Authority aa: allAuths) {
			exists = false;
			/*
			for(Authority ua: userAuths)
				if(ua.getAuthority().equals(aa.getAuthority())) {
					exists = true;
					break;
				}
			if(!exists)
			*/
			for(Authority a: auths)
				if(aa.getAuthority().equals(a.getAuthority())) {
					exists = true;
					break;
				}
			if(!exists) {
				aa.setUser(user);
				//aa.setUsername(user.getUsername());
				auths.add(aa);
				if(rols.equals(""))
					rols += "-" + aa.getAuthority().substring(5);
				else
					rols += "|-" + aa.getAuthority().substring(5);
			}
		}
		user.setRols(rols);
		user.setEnabled(true);
		user.setUserPwdDate(new Date());
		modelo.addAttribute("user", user);
		modelo.addAttribute("action", "Add");
		//modelo.addAttribute("auths", auths);
		//modelo.addAttribute("userAuths", userAuths);
		return "userForm";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username, Model modelo) {
		User user = userService.getUser(username);
		List<Authority> auths = new ArrayList<>(), allAuths = authorityService.getAuthorities();
		List<Authority> userAuths = new ArrayList<>(user.getAuthorities());
		String rols = "";
		Boolean exists;
		for(Authority aa: allAuths) {
			exists = false;
			for(Authority a: auths)
				if(aa.getAuthority().equals(a.getAuthority())) {
					exists = true;
					break;
				}
			if(!exists) {
				for(Authority ua: userAuths)
					if(aa.getAuthority().equals(ua.getAuthority())) {
						exists = true;
						break;
					}
				auths.add(new Authority(aa.getAuthority(), user));
				//auths.add(new Authority(aa.getAuthority(), user.getUsername()));
				if(exists) {
					if(rols.equals(""))
						rols += "+" + aa.getAuthority().substring(5);
					else
						rols += "|+" + aa.getAuthority().substring(5);
				}else {
					if(rols.equals(""))
						rols += "-" + aa.getAuthority().substring(5);
					else
						rols += "|-" + aa.getAuthority().substring(5);
				}
			}
		}
		user.setRols(rols);
		modelo.addAttribute("user", user);
		modelo.addAttribute("action", "Delete");
		return "userForm";
	}

	@GetMapping("/deleteUserProcess")
	//public String deleteUserProcess(@ModelAttribute("user") User user,
	//		BindingResult bindingResult, ModelMap model) {
	public String deleteUserProcess(@RequestParam("username") String username, Model modelo) {
		User user = userService.getUser(username);
		userService.delete(user);
		return "redirect:/admin/users";
	}

	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") User user,
			BindingResult bindingResult, ModelMap model){
		if(userService.userNameExists(user.getUsername()))
			bindingResult.addError(new FieldError("user", "username", "This user (" + user.getUsername() +") already exists"));

		if(userService.userEmailExists(user.getUserEmail()))
			bindingResult.addError(new FieldError("user", "userEmail", "This email (" + user.getUserEmail() + ") already exists"));
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("action", "Add");
			return "userForm";
		} else {
			PasswordEncoder pe = new BCryptPasswordEncoder();
			user.setPassword(pe.encode(user.getPassword()));
			userService.save(user);
System.out.println("ANTES");
			String[] strAuths = user.getRols().split("\\|");
			for(String str: strAuths)
				if(str.startsWith("+"))
					authorityService.save(new Authority("ROLE_" + str.substring(1), user));
			
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
