package dao;

import model.Tessera;

public interface ITesseraDAO {

    public void salvaTessera(Tessera t);

    public Tessera trovaTessera(long id);

    public void deleteTessera(long id);

    public void updateTessera(Tessera t);

    public void rinnovaTessera(Tessera t);
}
