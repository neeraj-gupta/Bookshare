package Application;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BookshareCORSPolicy implements Filter {

	  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
		
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse)res;
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	    //set headers to accept header x-auth-token sent by angular
	    response.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    if (request.getMethod().equals("OPTIONS")) {
	    	
	    	try {
	            response.getWriter().print("OK");
	            response.getWriter().flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	    	  try {
	  			chain.doFilter(req, res);
	  		} catch (IOException | ServletException e) {
	  			System.out.println("********************** Exception in CORS policy *************************** ");
	  			e.printStackTrace();
	  		}
	    }
	  }

	  public void init(FilterConfig filterConfig) {}

	  public void destroy() {}

	
}
