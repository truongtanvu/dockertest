package com.example.demo9_account.service;

import com.example.demo9_account.entity.Account;
import com.example.demo9_account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> opAccount = accountRepo.findByUsername(username);

        if (opAccount.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username" + username);
        }
        return new User(username, opAccount.get().getPassword(), Collections.emptyList());
    }

}
