package dao;

import model.RegistroTratte;

public interface IRegistroTratteDAO {
    public void saveOrUpdateRegistroTratte(RegistroTratte r);

    public void deleteRegistroTratte(long id);

    public void showAllRegistroTratte();

    public RegistroTratte trovaRegistroTratta(long id);
}
