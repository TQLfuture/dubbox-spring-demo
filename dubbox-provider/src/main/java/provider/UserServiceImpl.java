package provider;

import api.User;
import api.UserService;

public class UserServiceImpl implements UserService {

    public User getUser(Long id) {
        return new User(id, "username" + id);
    }
}