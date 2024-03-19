package human.smart.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/index.do")
	public String index() {
		return "index";
	}
	
	@GetMapping("/common/error404.do")
	public String error404() {
		return "common/error404";
	}
	
	@GetMapping("/common/error405.do")
	public String error405() {
		return "common/error405";
	}
	
	@GetMapping("/common/error500.do")
	public String error500() {
		return "common/error500";
	}
	
}
