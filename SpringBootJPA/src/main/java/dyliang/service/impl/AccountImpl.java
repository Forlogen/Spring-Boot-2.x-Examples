package dyliang.service.impl;

import dyliang.domain.Account;
import dyliang.repository.AccountRepository;
import dyliang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Object save(Account account) {
        accountRepository.save(account);
        return null;
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        Account a = Account.builder().id(10).name("Amy").money(2000.0f).email("Amy@163.com").build();
        Account account1 = account.orElse(a);
        return account1;

    }

    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public Account findByName(String name){
        return accountRepository.findByName(name);
    }

    @Override
    public Float sumOfMoney() {
        return accountRepository.sumOfMoney();
    }

    @Override
    public Float countAccountByMoney(Float money) {
        return accountRepository.countAccountByMoney(money);
    }

    @Override
    public Page<Account> listAccount(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> listAccount(String name, Pageable pageable) {
        return accountRepository.findByQuery(name,pageable);
    }
}
