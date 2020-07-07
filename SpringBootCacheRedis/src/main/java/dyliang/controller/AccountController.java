package dyliang.controller;

import dyliang.domain.Account;
import dyliang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public List<Account> testFindAll(){
        List<Account> aLl = accountService.findALl();
        return aLl;
    }

    @ResponseBody
    @GetMapping("/account/{id}")
    public Account testFindById(@PathVariable("id") Integer id){
        Account account = accountService.findById(id);
        return account;
    }
}
