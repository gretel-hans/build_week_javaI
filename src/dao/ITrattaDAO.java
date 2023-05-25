package dao;

import java.util.List;

import model.Tratta;

public interface ITrattaDAO {
    public void saveOrUpdateTratta(Tratta t);

    public void deleteTratta(long id);

    public Tratta showTratta(long id);

    public List<Tratta> showAllTratta();
}
