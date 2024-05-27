package org.danieska.entity;

import jakarta.persistence.*;


@Entity
    @Table(name= "client")
    public class Cliente {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
       @Column(name = "id")
    private int id;

        @Column (name= "primer_nombre")
    private String primerNombre;

        @Column(name = "apellido")
    private String apellido;

        @Column(name = "direccion")
    private String direccion;

        @Column(name = "celular")
    private String celular;

        public Cliente(){

        }
        public Cliente (String primerNombre, String apellido, String direccion, String celular){
            this.primerNombre = primerNombre;
            this.apellido = apellido;
            this.direccion = direccion;
            this.celular = celular;
        }

        public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getPrimerNombre(){return primerNombre;}

public void  setPrimerNombre(String primerNombre){this.primerNombre = primerNombre;}

public String getApellido(){return apellido;}

public void setApellido(String apellido){this.apellido = apellido;}

public String getDireccion(){return direccion;}

public void setDireccion(String direccion){this.direccion = direccion;}

public String getCelular(){return celular;}

public void setCelular(String celular){this.celular = celular; }

@Override
public String toString(){
        return "Cliente{" +
                "id=" + id +
                ", primerNombre='" + primerNombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +

                ", celular=;" + celular + '\'' +
                '}';
        }

}
