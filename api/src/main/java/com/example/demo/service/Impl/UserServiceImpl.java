package com.example.demo.service.Impl;

import com.example.demo.DTO.AccountInformationByBank;
import com.example.demo.DTO.AccountInformationGetDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.domain.AccountInformation;
import com.example.demo.domain.User;
import com.example.demo.repository.AccountInformationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountInformationRepository accountInformationRepository;

    @Override
    public UserDTO getUser(String identity, String password) {
        User user = userRepository.findByIdentityId(identity);
        if(user != null) {
            if (user.getPassword().equals(password)) {
                return new UserDTO(user, true);
            }
        }
        return new UserDTO(new User(),false);
    }

    // +++
    @Override
    public AccountInformationGetDTO getUserAccountInformation(AccountInformationByBank accountInformationByBank) {
        List<AccountInformation> accountInformationList = accountInformationRepository.findAllByUserIdentityIdAndBankCode(accountInformationByBank.getKimlikNo(), accountInformationByBank.getBankCode());
        AccountInformationGetDTO accountInformationGetDTO = new AccountInformationGetDTO();

        if(accountInformationList.size() != 0){

            accountInformationGetDTO.setAdi(accountInformationList.get(0).getUser().getName());
            accountInformationGetDTO.setIban(accountInformationList.get(0).getIban());
            accountInformationGetDTO.setKimlikNo(accountInformationList.get(0).getUser().getIdentityId());
            accountInformationGetDTO.setSoyadi(accountInformationList.get(0).getUser().getSurname());
//            accountInformationGetDTO.set;
        }

        return accountInformationGetDTO;
    }
}
