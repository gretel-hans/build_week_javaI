package dao;

import java.util.List;

import model.RegistroTratte;

public interface IRegistroTratteDAO {
    public void saveOrUpdateRegistroTratte(RegistroTratte r);

    public void deleteRegistroTratte(long id);

    public List<RegistroTratte> showAllRegistroTratte();

    public RegistroTratte trovaRegistroTratta(long id);
}
