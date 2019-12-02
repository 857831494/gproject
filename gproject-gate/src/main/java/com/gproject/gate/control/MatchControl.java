package com.gproject.gate.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchControl {

	@PostMapping("/brocast")
	public void brocast(@RequestBody byte[] data) {
		//广播，消息到指定客户端,跨服消息,比较少，还是直接http
	}
}
