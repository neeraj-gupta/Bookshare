package Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApplicationController {
	@RequestMapping( method=RequestMethod.GET, value="/")
    public @ResponseBody String getHome() {    	
    	return "index";
    }
	
	@RequestMapping( method=RequestMethod.GET, value="/error")
    public @ResponseBody String getError() {    	
    	return "login";
    }
}
