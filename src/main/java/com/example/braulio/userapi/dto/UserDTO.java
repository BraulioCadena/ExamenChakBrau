package com.example.braulio.userapi.dto;

import java.util.List;
import java.util.UUID;

public class UserDTO {
	private UUID id;
	private String email;
	private String phone;
	private String taxId;
	private String createdAt;
	private List<AddressDTO> addresses;

}
