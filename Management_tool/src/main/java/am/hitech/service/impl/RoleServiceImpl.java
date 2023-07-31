package am.hitech.service.impl;

import am.hitech.model.Role;
import am.hitech.repository.RoleRepository;
import am.hitech.service.RoleService;
import am.hitech.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getById(int id) throws NotFoundException {
        Role role = roleRepository.findById(id);
        if (role == null) {
            throw new NotFoundException("Role not found for id = " + id);
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        List<Role> list = roleRepository.findAll();
        return list;
    }
}
