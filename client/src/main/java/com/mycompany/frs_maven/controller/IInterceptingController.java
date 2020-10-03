package com.mycompany.frs_maven.controller;
import com.mycompany.frs_maven.model.domain.DTO;

public interface IInterceptingController {
	public DTO performAction(DTO dto);
}