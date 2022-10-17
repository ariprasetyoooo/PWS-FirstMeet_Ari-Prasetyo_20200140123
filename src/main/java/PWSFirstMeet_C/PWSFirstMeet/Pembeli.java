/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PWSFirstMeet_C.PWSFirstMeet;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author HAHAHA
 */
@Entity
@Table(name = "pembeli")
@NamedQueries({
    @NamedQuery(name = "Pembeli.findAll", query = "SELECT p FROM Pembeli p"),
    @NamedQuery(name = "Pembeli.findByIdPembeli", query = "SELECT p FROM Pembeli p WHERE p.idPembeli = :idPembeli"),
    @NamedQuery(name = "Pembeli.findByNamaPembeli", query = "SELECT p FROM Pembeli p WHERE p.namaPembeli = :namaPembeli"),
    @NamedQuery(name = "Pembeli.findByNohpPembeli", query = "SELECT p FROM Pembeli p WHERE p.nohpPembeli = :nohpPembeli"),
    @NamedQuery(name = "Pembeli.findByAlamatPembeli", query = "SELECT p FROM Pembeli p WHERE p.alamatPembeli = :alamatPembeli")})
public class Pembeli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pembeli")
    private Integer idPembeli;
    @Basic(optional = false)
    @Column(name = "nama_pembeli")
    private String namaPembeli;
    @Basic(optional = false)
    @Column(name = "nohp_pembeli")
    private String nohpPembeli;
    @Basic(optional = false)
    @Column(name = "alamat_pembeli")
    private String alamatPembeli;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPembeli")
    private Transaksi transaksi;

    public Pembeli() {
    }

    public Pembeli(Integer idPembeli) {
        this.idPembeli = idPembeli;
    }

    public Pembeli(Integer idPembeli, String namaPembeli, String nohpPembeli, String alamatPembeli) {
        this.idPembeli = idPembeli;
        this.namaPembeli = namaPembeli;
        this.nohpPembeli = nohpPembeli;
        this.alamatPembeli = alamatPembeli;
    }

    public Integer getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(Integer idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNohpPembeli() {
        return nohpPembeli;
    }

    public void setNohpPembeli(String nohpPembeli) {
        this.nohpPembeli = nohpPembeli;
    }

    public String getAlamatPembeli() {
        return alamatPembeli;
    }

    public void setAlamatPembeli(String alamatPembeli) {
        this.alamatPembeli = alamatPembeli;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPembeli != null ? idPembeli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pembeli)) {
            return false;
        }
        Pembeli other = (Pembeli) object;
        if ((this.idPembeli == null && other.idPembeli != null) || (this.idPembeli != null && !this.idPembeli.equals(other.idPembeli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PWSFirstMeet_C.PWSFirstMeet.Pembeli[ idPembeli=" + idPembeli + " ]";
    }
    
}
