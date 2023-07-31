package am.hitech.repository;

import am.hitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findById(int id);

    List<User> findAllBy();

    @Query(nativeQuery = true, value = "select * from `user` where " +
            "status = 'active'")
    List<User> getOnlyActiveUsers();

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @Query(nativeQuery = true, value = "select * from `user` where " +
            "if(?1 is not null, lower(first_name) like lower(concat(?1,'%')), true )" +
            "and if(?2 is not null, lower(last_name) like lower(concat(?2,'%')), true )" +
            "and if(?3 is not null, lower(email) like lower(concat(?3,'%')), true )")
    List<User> search(String firstName, String lastName, String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `user` set status = if( status = 'active','unverified','active') where id = ?1")
    void change(int id);

//    List<User> findByFirstNameIgnoreCaseLikeAndLastNameIgnoreCaseLikeAndEmailIgnoreCaseLike(
//            Optional<String> firstName, Optional<String> lastName, Optional<String> email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `user` set first_name = ?1, last_name = ?2, status = 'unverified' where email = ?3")
    void edit(Optional<String> firstName, Optional<String> lastName, String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `user` set verification_code = ?1 where email = ?2")
    void code(String code, String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `user` set status = 'Active' where email = ?1")
    void verification(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `user` set password_token = ?1 where email = ?2")
    void token(int token, String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `user` set password = ?1 where email = ?2")
    void changePassword(String password, String email);
}
