package dao;

import java.util.List;

import model.Tratta;

public interface ITrattaDAO {
    public void saveTratta(Tratta t);
    public void updateTratta(Tratta t);
    //public void deleteTratta(long id);
    public List<Tratta> showAllTratta();
}
