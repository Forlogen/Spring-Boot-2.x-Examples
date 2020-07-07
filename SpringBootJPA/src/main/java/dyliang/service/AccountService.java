package dyliang.service;

import dyliang.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AccountService {

    public Object save(Account account);


    public void delete(Integer id);


    public List<Account> findAll();


    public List<Account> findAll(Sort sort);


    public Account findById(Integer id);


    public long count();


    public Account findByName(String name);


    Float sumOfMoney();


    Float countAccountByMoney(Float money);


    Page<Account> listAccount(Pageable pageable);


    Page<Account> listAccount(String name, Pageable pageable);

}
