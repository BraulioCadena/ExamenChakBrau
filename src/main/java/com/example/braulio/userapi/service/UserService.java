package com.example.braulio.userapi.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.braulio.userapi.dto.UserDTO;
import com.example.braulio.userapi.model.User;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {
	private final List<User> users = new ArrayList<>(); //simula la memoria
	
	@PostConstruct
	public void initUsers() {
		users.add(UserFactory.create("user@gmail.com","user1","+52 5636956631","BRR210800XXX","calle arboledas No.1","MX","calle No.2","US"));
		users.add(UserFactory.create("usuario2@gmail.com","user2","+52 5636956632","YZZ121298YYY","calle pino No.2","MX","calle No.3","MX"));
		users.add(UserFactory.create("usuario3@gmail.com","user3","+52 5636956633","LSY120901ZZZ","calle arbol No.3","MX","calle No.4","FR"));
	}
	
	public List<UserDTO> getUsersSortedBy(String sortedBy){
		Comparator<User> comparator = UserComparator.getComparator(sortedBy);
		return users.stream()
				.sorted(comparator)
				.map(UserMapper::toDTO)
				.collect(Collectors.toList());
		
	}
	public List<UserDTO> filterUsers(String filter){
		return users.stream()
				.filter(user -> FilterUtil.matches(user, filter))
				.map(UserMapper::toDTO)
				.collect(Collectors.toList());
	}
	public UserDTO createUser(UserDTO userDTO) {
		ValidationUtil.validateTaxId(dto.getTaxId());
		ValidationUtil.validatePhone(dto.getPhone());
		
		if (users.stream().anyMatch(u -> u.getTaxId().equals(dto.getId()))) {
			throw new DuplicateTaxIdException("Tax ID must be unique");
		}
		User user =UserMapper.fromDTO(dto);
		user.setId(UUID.randomUUID());
		user.setPassword(AESUtil.encrypt(dto.getPassword()));
		user.setCreatedAt(DateUtil.getMadagascarTimestamp());
		
		users.add(user);
		return UserMapper.toDTO(user);
	}
	
	public UserDTO updateUser(UUID id, Map<String, Object> updates) {
		User user = users.stream()
				.filter(u -> u.getId().equals(id))
				.findFirst()
				.orElseThrow()->new UsernameNotFoundException("User not found"));
				
		updates.forEach((key, value) ->{
			switch(key) {
			case "email" -> user.setEmail((String ) value);
			case "name" -> user.setName((String) value);
			case "phone" -> {
				ValidationUtil.validateTaxId((String) value);
				if (users.stream().anyMatch(u -> u.getTaxId().equals(value) && !u.getId().equals(id))) {
					throw new DuplicateTaxIdException("Tax ID must be unique");
					
				}
				user.setTaxId((String) value);
			}
			}
		});
	}
	
	public void deleteUser(UUID id) {
		users.removeIf(u -> u.getId().equals(id));
		
	}
	public Optional<User> findTaxId(String taxId){
		return users.stream().filter(u -> u.getTax_id().equals(taxId)).findFirst();
		
	}
}
