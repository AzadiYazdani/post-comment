package com.sale.app.model.dto;

import com.haraji.sale.dto.AbstractResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class AuthResponse extends AbstractResponse {
	private static final long serialVersionUID = -5842662313715118663L;

	private String token;
	private String username;
	private List<String> userRoles;
}
