package com.acme.studenthome.controller.UserAccountSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.Account;
import com.acme.studenthome.domain.service.UserAccountSystemService.AccountService;
import com.acme.studenthome.resource.UserAccountSystemResource.AccountResource;
import com.acme.studenthome.resource.UserAccountSystemResource.SaveAccountResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountService accountService;

    @GetMapping("/users/{userId}/accounts")
    public Page<AccountResource> getAllAccountsByUserId(@PathVariable(name = "userId") Long userId, Pageable pageable) {
        Page<Account> accountPage = accountService.getAllAccountsByUserId(userId, pageable);
        List<AccountResource> resources = accountPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @GetMapping("/users/{userId}/accounts/{accountId}")
    public AccountResource getAccountByIdAndUserId(@PathVariable(name = "accountId") Long accountId, @PathVariable(name = "userId") Long userId) {
        return convertToResource(accountService.getAccountByIdAndUserId(accountId, userId));
    }

    @PostMapping("/users/{userId}/accounts")
    public AccountResource createAccount(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveAccountResource resource) {
        Account account = convertToEntity(resource);
        return convertToResource(accountService.createAccount(userId, account));
    }

    @PutMapping("/users/{userId}/accounts/{accountId}")
    public AccountResource updateAccount(@PathVariable(name = "userId") Long userId, @PathVariable(name = "accountId") Long accountId, @Valid @RequestBody SaveAccountResource resource) {
        Account account = convertToEntity(resource);
        return convertToResource(accountService.updateAccount(accountId, userId, account));
    }

    @DeleteMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "userId") Long userId, @PathVariable(name = "accountId") Long accountId) {

        return accountService.deleteAccount(accountId, userId);
    }


    private Account convertToEntity(SaveAccountResource resource) {

        return mapper.map(resource, Account.class);
    }

    private AccountResource convertToResource(Account entity) {

        return mapper.map(entity, AccountResource.class);
    }


}
