/**
 * 
 */
package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kosta
 *
 */
@RestController
public class TestController {
	
	@RequestMapping("/")
	public List<String> test() {
		return Arrays.asList("hello","hello","hello","hello","hello","hello","hello");
	}
}
