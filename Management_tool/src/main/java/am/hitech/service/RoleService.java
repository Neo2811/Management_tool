package am.hitech.service;

import am.hitech.model.Role;
import am.hitech.util.exception.NotFoundException;

import java.util.List;

public interface RoleService {

    Role getById(int id) throws NotFoundException;

    List<Role> getAll();
}
