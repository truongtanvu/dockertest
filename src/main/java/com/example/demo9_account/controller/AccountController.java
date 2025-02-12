package com.example.demo9_account.controller;

import com.example.demo9_account.dto.AccountDTO;
import com.example.demo9_account.entity.Account;
import com.example.demo9_account.entity.Department;
import com.example.demo9_account.repository.AccountRepository;
import com.example.demo9_account.repository.DepartmentRepository;
import com.example.demo9_account.req.AccountCreateReq;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(value = "*")
@RestController
@Slf4j
public class AccountController {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private ModelMapper modelMapper;

    private DepartmentRepository departmentRepo;

    public AccountController(AccountRepository accountRepo, ModelMapper modelMapper, DepartmentRepository departmentRepo) {
        this.accountRepo = accountRepo;
        this.modelMapper = modelMapper;
        this.departmentRepo = departmentRepo;
    }
    @GetMapping("account")
    public ResponseEntity<?> getAll() {
        
        List<Account> accounts = accountRepo.findAll();

        List<AccountDTO> accountDTOS = modelMapper.map(accounts, new TypeToken<List<AccountDTO>>(){}.getType());
        return ResponseEntity.ok(accountDTOS);
    }

    @GetMapping("account/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Account> opAccount = accountRepo.findById(id);

        if (opAccount.isEmpty()) {
            return new ResponseEntity<>("Not found with id: " +id, HttpStatus.BAD_REQUEST);
        }

        Account account = opAccount.get();
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        return ResponseEntity.ok(accountDTO);
    }
    @PostMapping("account")
    public  ResponseEntity<?> create(@RequestBody AccountCreateReq accountCreateReq){
        Optional<Account> opAccount = accountRepo.findByUsername(accountCreateReq.getUsername());
        if (opAccount.isPresent()){
            return new ResponseEntity<>("Department Name is exists: " + accountCreateReq.getUsername(), HttpStatus.BAD_REQUEST);
        }

        Optional<Department> opDepartment = departmentRepo.findById(accountCreateReq.getDepartmentId());
        if (opDepartment.isEmpty()){
            return new ResponseEntity<>("Department Id is not found: " + accountCreateReq.getDepartmentId(), HttpStatus.BAD_REQUEST);
        }
        Account account = modelMapper.map(accountCreateReq, Account.class);
        account.setId(null);
        accountRepo.save(account);
        return ResponseEntity.ok("Create Successfully: " + account.getUsername());
    }
}
