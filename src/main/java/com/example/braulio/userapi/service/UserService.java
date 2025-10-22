package com.example.braulio.userapi.service;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.braulio.userapi.dto.UserDTO;
import com.example.braulio.userapi.exception.DuplicateTaxIdException;
import com.example.braulio.userapi.exception.UserNotFoundException;
import com.example.braulio.userapi.model.User;
import com.example.braulio.userapi.repository.UserRepository;
import com.example.braulio.userapi.util.AESUtil;
import com.example.braulio.userapi.util.DateUtil;
import com.example.braulio.userapi.util.FilterUtil;
import com.example.braulio.userapi.util.UserMapper;
import com.example.braulio.userapi.util.ValidationUtil;


@Service
public class UserService {

@Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsersSortedBy(String sortedBy) {
        List<User> users = userRepository.findAll();
        Comparator<User> comparator = com.example.braulio.userapi.util.UserComparator.getComparator(sortedBy);
        return users.stream()
                .sorted(comparator)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> filterUsers(String filter) {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> FilterUtil.matches(user, filter))
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO dto) {
        ValidationUtil.validateTaxId(dto.getTaxId());
        ValidationUtil.validatePhone(dto.getPhone());

        if (userRepository.existsByTaxId(dto.getTaxId())) {
            throw new DuplicateTaxIdException("Tax ID must be unique");
        }

        User user = UserMapper.fromDTO(dto);
        user.setId(UUID.randomUUID());
        user.setPassword(AESUtil.encrypt(dto.getPassword()));
        user.setCreatedAt(DateUtil.getMadagascarTimestamp());

        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public UserDTO updateUser(UUID id, Map<String, Object> updates) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "email" -> user.setEmail((String) value);
                case "name" -> user.setName((String) value);
                case "phone" -> {
                    ValidationUtil.validatePhone((String) value);
                    user.setPhone((String) value);
                }
                case "taxId" -> {
                    ValidationUtil.validateTaxId((String) value);
                    if (userRepository.existsByTaxId((String) value) && !user.getTaxId().equals(value)) {
                        throw new DuplicateTaxIdException("Tax ID must be unique");
                    }
                    user.setTaxId((String) value);
                }
            }
        });

        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findByTaxId(String taxId) {
        return userRepository.findByTaxId(taxId);
    }


}
