package ga.manuelgarciacr.pla10.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		 
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/pla10/admin/users");
        } else if(roles.contains("ROLE_ADMINISTRATIVO") && roles.size() == 1){
            response.sendRedirect("/pla10/administracion");
        } else if(roles.contains("ROLE_EDITOR") && roles.size() == 1){
            response.sendRedirect("/pla10/edicion");
        }else {
            response.sendRedirect("/pla10/intranet");
        }
    }
}
