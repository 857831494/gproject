package com.gproject.gate.control;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gproject.common.config.AuthHandler;

@RestController
public class PlayerInitControl {

	@RequestMapping(AuthHandler.NEED_AUTH_PATH+"getToken")
	public byte[] getToken(@RequestBody byte[] msg) {
		return null;
	}
}
