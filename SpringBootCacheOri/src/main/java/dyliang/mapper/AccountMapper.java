package dyliang.mapper;

import dyliang.domain.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select * from account")
    public List<Account> findAll();

    @Select("select * from account where id=#{id}")
    public Account findById(Integer id);

    @Select("select * from account where name=#{name}")
    public Account findByName(String name);

    @Insert("insert into account(name,money) values (#{name},#{money})")
    public int insertAccount(Account account);

    @Update("update account set name=#{name} where id=#{id}")
    public int updateAccount(Account account);

    @Delete("delete from account where id=#{id}")
    public void deleteAccount(Integer id);
}
