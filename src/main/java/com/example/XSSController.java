package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class XSSController {
	private static final Logger log = LoggerFactory.getLogger(XSSController.class);
	
	@RequestMapping("/xss")
	public String xss(@RequestParam(value="name") String name, Model model) {
		log.info("name = " + name);
//		model.addAttribute("name", name.replaceAll("<", "&lt;").replaceAll(">", "&gt;")); //"<script>alert('xss');</script>"
		model.addAttribute("name", name); //"<script>alert('xss');</script>"
		return "index";
	}
	
}
