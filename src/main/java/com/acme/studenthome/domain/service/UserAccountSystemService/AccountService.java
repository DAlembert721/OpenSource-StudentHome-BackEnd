package com.acme.studenthome.domain.service.UserAccountSystemService;

import com.acme.studenthome.domain.model.UserAccountSystem.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface AccountService {

    Page<Account> getAllAccountsByUserId(Long userId, Pageable pageable);

    Account createAccount(Long userId, Account account);

    Account getAccountByIdAndUserId(Long accountId, Long userId);

    Account updateAccount(Long accountId, Long userId, Account accountRequest);

    ResponseEntity<?> deleteAccount(Long accountId, Long userId);

}
