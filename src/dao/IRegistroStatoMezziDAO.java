package dao;

import java.util.List;

import model.RegistroStatoMezzi;

public interface IRegistroStatoMezziDAO {
    public void saveRegistro(RegistroStatoMezzi r);
    public void updateRegistro(RegistroStatoMezzi r);
    public void deleteRegistro(long id);
    public List<RegistroStatoMezzi> showAllRegistro();
    public RegistroStatoMezzi trovaRegistroStatoMezzi(long id);
}
