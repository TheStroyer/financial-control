package br.com.fpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fernando
 *
 */
@Controller
public class FinancialController {

	/**
	 * @param map
	 * @return
	 */
	@RequestMapping("/")
	public String home(ModelMap map) {
		return "index.html";
	}
	
	// Casa tudo que nao tenha sufixo (não é um recurso estatico)
	@RequestMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}

}
