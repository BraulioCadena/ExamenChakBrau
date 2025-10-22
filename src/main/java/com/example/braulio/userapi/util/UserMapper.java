package com.example.braulio.userapi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.example.braulio.userapi.dto.AddressDTO;
import com.example.braulio.userapi.dto.UserDTO;
import com.example.braulio.userapi.model.Address;
import com.example.braulio.userapi.model.User;

public class UserMapper {

//CONVERTU CADA ADDRESSDTO EN UN ADDRESS DENTRO DEL METODO FROM DTO
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setTaxId(user.getTaxId());
        dto.setCreatedAt(user.getCreatedAt());
        
        if (user.getAddresses() != null) {
            List<AddressDTO> addressDTOs = user.getAddresses().stream().map(address -> {
                AddressDTO a = new AddressDTO();
                a.setId(address.getId());
                a.setName(address.getName());
                a.setStreet(address.getStreet());
                a.setCountryCode(address.getCountryCode());
                return a;
            }).collect(Collectors.toList());
            dto.setAddresses(addressDTOs);
        } else {
            dto.setAddresses(new ArrayList<>());
        }

        return dto;
    }

    public static User fromDTO(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setTaxId(dto.getTaxId());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(AESUtil.encrypt(dto.getPassword()));
        }

        List<Address> addresses = dto.getAddresses().stream().map(addressDTO -> {
            Address a = new Address();
            a.setId(addressDTO.getId());
            a.setName(addressDTO.getName());
            a.setStreet(addressDTO.getStreet());
            a.setCountryCode(addressDTO.getCountryCode());
            return a;
        }).collect(Collectors.toList());

        user.setAddresses(addresses);
        return user;
    }


}
