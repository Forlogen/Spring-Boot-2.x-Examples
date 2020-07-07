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
}
