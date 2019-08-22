package com.gproject.common.utils.common;

import com.gproject.common.dto.proto.TipDTO.TipCode;

public class GErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TipCode tipCode;
	
	public GErrorException(TipCode tipCode){
		this.tipCode=tipCode;
	}

}
