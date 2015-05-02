package bookshare.Controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	
	@RequestMapping("/user")
	public Principal user(Principal user)
	{
		return user;
	}
	
	@RequestMapping("/token")
	  @ResponseBody
	  public Map<String,String> token(HttpSession session) {
	    return Collections.singletonMap("token", session.getId());
	    
	  }
	

	@RequestMapping(value="/logoutMeOut", method=RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{
		System.out.println("*************** LOGGING OUT ***************");
	
		  String cookieName = "SESSION";
		  Cookie cookie = new Cookie(cookieName, null);
		  cookie.setMaxAge(0);
		  cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
		  response.addCookie(cookie);
		
		  session.invalidate();
		
	}

}
