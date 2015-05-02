/**
 * This class is responsible for handling Cross-Site-Request-Forgery (CSRF) attacks.
 * Spring security expects a CSRF token called "X-CSRF" sent to it with every request.
 * This token is available on the server side in the HTTPRequest attribute when first page was loaded
 * To get it in client side, we would send that as a cookie as demonstrated below. 
 * Angular has built-in support for CSRF tokens which it calls XSRF based on cookies.
 * So we send the cookie named "XSRF-TOKEN" to angular
 *We need to add the resulting filter in the application where spring security is configured
 *Make sure this filter is added where request attribute is available preferably in the spring security filter chain 
 *
 * @author: Ashish Narkhede
 * */


package bookshare.Controllers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfHeaderFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//retrieve CSRF token from the request attribute
		CsrfToken csrf = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		
		if(csrf != null)
 		{   
			//getCookie retrieves first cookie with the given name
			Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
			String token = csrf.getToken();
			
			//now set this token in cookie only if token is not null or cookie is null and token and cookie values are not same
			if(cookie == null || token != null && !token.equals(cookie.getValue()) )
			{
				cookie = new Cookie("XSRF-TOKEN", token);
				//be sure to add cookie path to the context of application path
				cookie.setPath("/");
				//finally add cookie to the response
				response.addCookie(cookie);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
