package dyliang.service;

import dyliang.domain.Account;
import dyliang.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Cacheable(value = "account")
    public List<Account> findALl(){
        System.out.println("service findAll...");
        List<Account> all = accountMapper.findAll();
        return all;
    }

    @Cacheable(value = "account", key = "#id", condition = "#id>1")
    public Account findById(Integer id){
        System.out.println("service findById"+id);
        Account account = accountMapper.findById(id);

        return account;
    }

    @CachePut(value = "account", key = "#result.id")
    public Account updateAccount(Account account){
        accountMapper.updateAccount(account);
        return account;

    }

    @CacheEvict(value = "account", key = "#id", beforeInvocation = true)
    public void deleteAccount(Integer id){
        accountMapper.deleteAccount(id);
    }

    @Caching(
            cacheable = {@Cacheable(value = "account", key = "#name")},
            put = {
                    @CachePut(value = "account", key = "#result.id"),
                    @CachePut(value = "account", key = "#result.name")
            }
    )
    public Account findByName(String name){
        Account account = accountMapper.findByName(name);
        return account;
    }
}
