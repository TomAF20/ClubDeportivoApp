package com.example.clubdeportivo;

public class BajaProducto {
    private Long productoId;
    private String motivo;

    public BajaProducto() {}

    public BajaProducto(Long productoId, String motivo) {
        this.productoId = productoId;
        this.motivo = motivo;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }



}
