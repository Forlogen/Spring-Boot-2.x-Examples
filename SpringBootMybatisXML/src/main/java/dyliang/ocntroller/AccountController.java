package dyliang.ocntroller;

import dyliang.dao.IAccountDao;
import dyliang.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private IAccountDao accountDao;


    // http://localhost:8080/account
    @GetMapping("/account")
    public List<Account> testFindAll(){
        return accountDao.findAll();
    }

    // http://localhost:8080/account/1
    @GetMapping("account/{id}")
    public Account testFindById(@PathVariable("id") Integer id){
        return accountDao.findById(id);
    }

    // http://localhost:8080/accountInsert?name=Amy&money=1000
    @GetMapping("/accountInsert")
    public Account testFindById(Account account){
        accountDao.insertAccount(account);
        return account;
    }
}
