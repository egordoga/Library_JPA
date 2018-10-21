package dao;

import entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class GenreDao implements IGenreDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lib");
    EntityManager em = emf.createEntityManager();

    @Override
    public List<Genre> findAll() {
        Query query = em.createQuery("FROM Genre", Genre.class);
        return (List<Genre>) query.getResultList();
    }

    @Override
    public Genre findById(Long id) {
        Query query = em.createQuery("select g from Genre g where g.id=:gid", Genre.class);
        query.setParameter("gid", id);
        return (Genre) query.getSingleResult();
    }
}
