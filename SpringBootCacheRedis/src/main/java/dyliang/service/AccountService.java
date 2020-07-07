package dyliang.service;

import dyliang.domain.Account;
import dyliang.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

//    @Qualifier("cacheManager")
//    @Autowired
//    RedisCacheManager cacheManager;

    @Cacheable(value = "account")
    public List<Account> findALl(){
        System.out.println("service findAll...");
        List<Account> all = accountMapper.findAll();
        return all;
    }

    @Cacheable(value = "account", key = "#id")
    public Account findById(Integer id){
        System.out.println("service findById"+id);
        Account account = accountMapper.findById(id);

        return account;
    }
}
