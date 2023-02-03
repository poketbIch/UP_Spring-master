package com.example.RPD.Repository;


import com.example.RPD.Models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findByUsername(String username);
}
