package dao;

import entity.User;

import javax.persistence.*;

public class UserDao implements IUserDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lib");
    private EntityManager em = emf.createEntityManager();

    @Override
    public User findByName(String name) {
        TypedQuery<User> query = null;
        try {
            query = em.createQuery("FROM User u WHERE u.username=:name", User.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
