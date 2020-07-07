package dyliang.dao;

import dyliang.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IAccountDao {

    @Select("select * from account")
    public List<Account> findAll();

    @Select("select * from account where id=#{id}")
    public Account findById(Integer id);

    @Insert("insert into account(name,money) values (#{name},#{money})")
    public int insertAccount(Account account);

    @Update("update account set name=#{name} where id=#{id}")
    public int updateAccount(Account account);

}
