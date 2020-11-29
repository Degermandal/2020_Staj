package com.example.demo.service;

import com.example.demo.DTO.AccountInformationByBank;
import com.example.demo.DTO.AccountInformationGetDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.domain.User;

public interface UserService {

    UserDTO getUser(String identity, String password);

    // +++
    AccountInformationGetDTO getUserAccountInformation(AccountInformationByBank accountInformationByBank);
}
