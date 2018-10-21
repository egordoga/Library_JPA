package dao;

import entity.Genre;

import java.util.List;

public interface IGenreDao {
    List<Genre> findAll();
    Genre findById(Long id);
}
