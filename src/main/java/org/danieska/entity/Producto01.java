package org.danieska.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "producto01")
public class Producto01 {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name= "primer_nombre")
    private String primerNombre;

    @Column (name= "precio")
    private double  precio;


    public Producto01(String primerNombre, Double precio){
        this.primerNombre = primerNombre;
        this.precio = precio;
    }
    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getPrimerNombre(){return primerNombre;}

    public void  setPrimerNombre(String primerNombre){this.primerNombre = primerNombre;}

    public Double getPrecio(){return precio;}

    public void  setPrecio(Double precio){this.precio = precio;}

    @Override
    public String toString(){
        return "Producto{" +
                "id=" + id +
                ", primerNombre='" + primerNombre + '\'' +
                ", precio='" + precio + '\'' +

                '}';
    }

}
