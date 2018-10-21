package dao;

import entity.User;

public interface IUserDao {

    User findByName(String name);
}
