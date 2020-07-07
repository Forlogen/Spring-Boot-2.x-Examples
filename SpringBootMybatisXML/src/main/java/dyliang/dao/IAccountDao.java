package dyliang.dao;

import dyliang.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAccountDao {

    public List<Account> findAll();

    public Account findById(Integer id);

    public int insertAccount(Account account);

}
