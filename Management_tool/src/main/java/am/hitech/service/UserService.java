package am.hitech.service;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDto;
import am.hitech.util.exception.DuplicateException;
import am.hitech.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User getById(int id) throws NotFoundException;

    List<User> getAll();

    List<User> getOnlyActiveUsers();

    List<User> search(String firstName, String lastName, String email);

    List<User> searchUsers(String firstName, String lastName, String email);

    User getByUserName(String email);

    void create(UserRequestDto requestDto) throws DuplicateException;

    void edit(String firstName, String lastName, String email);

    void change(int id);

    void code(String code, String email);

    void verification(String email);

    User findByEmail(String email);

    void token(int token, String email);

    void changePassword(String password, String email);
}
