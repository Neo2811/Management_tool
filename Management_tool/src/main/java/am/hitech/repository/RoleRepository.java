package am.hitech.repository;

import am.hitech.model.Role;
import am.hitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findById(int id);

    List<Role> findAll();
}
