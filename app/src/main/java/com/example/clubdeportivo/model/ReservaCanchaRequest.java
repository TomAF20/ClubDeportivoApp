package com.example.clubdeportivo.model;

import java.math.BigDecimal;
import java.util.List;

public class ReservaCanchaRequest {
    private Integer idSocio;
    private Integer idCancha;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private List<Integer> productos;
    private BigDecimal total;

    public Integer getIdSocio() { return idSocio; }
    public void setIdSocio(Integer idSocio) { this.idSocio = idSocio; }

    public Integer getIdCancha() { return idCancha; }
    public void setIdCancha(Integer idCancha) { this.idCancha = idCancha; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

    public List<Integer> getProductos() { return productos; }
    public void setProductos(List<Integer> productos) { this.productos = productos; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
