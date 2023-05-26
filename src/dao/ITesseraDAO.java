package dao;

import model.Tessera;

public interface ITesseraDAO {

    public void saveOrUpdateTessera(Tessera t);

    public Tessera trovaTessera(long id);

    public void rinnovaTessera(Tessera t);
}
