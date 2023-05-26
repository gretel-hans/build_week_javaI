package dao;

import java.util.List;

import model.RegistroStatoMezzi;

public interface IRegistroStatoMezziDAO {
    public void saveOrUpdateRegistroStato(RegistroStatoMezzi r);

    public void deleteRegistroStato(long id);

    public void showAllRegistroStato();

    public RegistroStatoMezzi trovaRegistroStatoMezzi(long id);
}
