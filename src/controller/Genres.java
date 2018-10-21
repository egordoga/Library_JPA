package controller;


import entity.Genre;
import service.ServiceDB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class Genres implements Serializable {

    public List<Genre> getGenreList() {
        ServiceDB serviceDB = new ServiceDB();
        return serviceDB.findAllGenre();
    }

}
