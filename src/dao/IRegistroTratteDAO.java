package dao;

import model.RegistroTratte;

public interface IRegistroTratteDAO {
    public void saveRegistroTratte(RegistroTratte r);
   public void updateRegistroTratte(RegistroTratte r);
    public void deleteRegistroTratte(RegistroTratte r);
    public void showAllRegistroTratte();
}

