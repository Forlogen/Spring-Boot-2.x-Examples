package dyliang.controller;

import dyliang.domain.Account;
import dyliang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/findall")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/findallsort")
    public List<Account> findALlSort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "money");
        return accountService.findAll(sort);
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Integer id) {
        Account account = accountService.findById(id);
        return account;
    }

    @GetMapping("/name/{name}")
    public Account findByName(@PathVariable String name) {
        return accountService.findByName(name);
    }

    @GetMapping("/sum")
    public Float sum() {
        return accountService.sumOfMoney();
    }

    @GetMapping("/money/{money}")
    public Float countByMoney(@PathVariable Float money) {
        return accountService.countAccountByMoney(money);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }

    @PostMapping("/save")
    public Object save(@RequestBody Account account) {
        return accountService.save(account);
    }

    @GetMapping("/list")
    public Page<Account> listBlog(@PageableDefault(size = 3, sort = {"money"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Account> accounts = accountService.listAccount(pageable);
        return accounts;
    }

    @GetMapping("/list1")
    public Page<Account> listBlog1(@PageableDefault(size = 3, sort = {"money"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Account> accounts = accountService.listAccount("o", pageable);
        return accounts;
    }
}
