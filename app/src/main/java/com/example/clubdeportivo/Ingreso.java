package com.example.clubdeportivo;

public class Ingreso {
        private String concepto;
        private String fecha;
        private double importe;

        public Ingreso(String concepto, String fecha, double importe) {
            this.concepto = concepto;
            this.fecha = fecha;
            this.importe = importe;
        }

        public String getConcepto() {
            return concepto;
        }

        public String getFecha() {
            return fecha;
        }

        public double getImporte() {
            return importe;
        }



}
