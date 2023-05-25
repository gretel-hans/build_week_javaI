package dao;

import model.PuntoEmissione;

public interface IPuntoEmissioneDAO {
    public PuntoEmissione cercaPEperId(long id);
    public void salvaPuntoVendita(PuntoEmissione pe);
}
