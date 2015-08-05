package com.git.iboke.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.iboke.mapper.ImgtypeMapper;
import com.git.iboke.model.Imgtype;
import com.git.iboke.service.Demotest;

@Service
public class DemotestImpl implements Demotest{

	@Autowired
	
	private ImgtypeMapper imgtypemapper;
	
	public Imgtype select(int id) {
		
		Imgtype e =imgtypemapper.selectByPrimaryKey(id);
		
		
		return e;
		
	}
	
}
