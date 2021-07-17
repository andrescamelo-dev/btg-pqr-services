package com.btg.pqr.btgpqrservices.dto;




public class ReclamoRequestDTO extends PqrRequestDTO{
    private String prqIdAsociado;
    private String tipoPrqIdAsociado;

    public String getPrqIdAsociado() {
        return prqIdAsociado;
    }
    public void setPrqIdAsociado(String prqIdAsociado) {
        this.prqIdAsociado = prqIdAsociado;
    }
    public String getTipoPrqIdAsociado() {
        return tipoPrqIdAsociado;
    }
    public void setTipoPrqIdAsociado(String tipoPrqIdAsociado) {
        this.tipoPrqIdAsociado = tipoPrqIdAsociado;
    }

    
}
