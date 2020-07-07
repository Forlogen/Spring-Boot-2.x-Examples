package dyliang.repository;

import dyliang.domain.Account;
import dyliang.domain.QueryRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByName(String name);

    @Transactional
    @Query("select sum(a.money) from Account a")
//    @Query(value = "select sum(a.money) from t_account a", nativeQuery=true)
    Float sumOfMoney();

    Float countAccountByMoney(Float money);

//    @Query("select a from Account a where a.name like %?1%")
    @Query("select a from Account a where a.name like %:name%")
    Page<Account> findByQuery(@Param("name") String name, Pageable pageable);

    @Query("select a from Account a where a.name like %:name%")
    Page<Account> findByQuery(QueryRes query, Pageable pageable);
}
