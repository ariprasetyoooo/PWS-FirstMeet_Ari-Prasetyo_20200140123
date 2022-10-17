/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PWSFirstMeet_C.PWSFirstMeet;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author HAHAHA
 */
@Entity
@Table(name = "pengelolaan_data")
@NamedQueries({
    @NamedQuery(name = "PengelolaanData.findAll", query = "SELECT p FROM PengelolaanData p"),
    @NamedQuery(name = "PengelolaanData.findByIdPengelolaan", query = "SELECT p FROM PengelolaanData p WHERE p.idPengelolaan = :idPengelolaan"),
    @NamedQuery(name = "PengelolaanData.findByQty", query = "SELECT p FROM PengelolaanData p WHERE p.qty = :qty"),
    @NamedQuery(name = "PengelolaanData.findByHarga", query = "SELECT p FROM PengelolaanData p WHERE p.harga = :harga")})
public class PengelolaanData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pengelolaan")
    private Integer idPengelolaan;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @Column(name = "harga")
    private int harga;
    @JoinColumn(name = "kode_barang", referencedColumnName = "kode_barang")
    @OneToOne(optional = false)
    private Barang kodeBarang;
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai")
    @OneToOne(optional = false)
    private Pegawai idPegawai;

    public PengelolaanData() {
    }

    public PengelolaanData(Integer idPengelolaan) {
        this.idPengelolaan = idPengelolaan;
    }

    public PengelolaanData(Integer idPengelolaan, int qty, int harga) {
        this.idPengelolaan = idPengelolaan;
        this.qty = qty;
        this.harga = harga;
    }

    public Integer getIdPengelolaan() {
        return idPengelolaan;
    }

    public void setIdPengelolaan(Integer idPengelolaan) {
        this.idPengelolaan = idPengelolaan;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Barang getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(Barang kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public Pegawai getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Pegawai idPegawai) {
        this.idPegawai = idPegawai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPengelolaan != null ? idPengelolaan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PengelolaanData)) {
            return false;
        }
        PengelolaanData other = (PengelolaanData) object;
        if ((this.idPengelolaan == null && other.idPengelolaan != null) || (this.idPengelolaan != null && !this.idPengelolaan.equals(other.idPengelolaan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PWSFirstMeet_C.PWSFirstMeet.PengelolaanData[ idPengelolaan=" + idPengelolaan + " ]";
    }
    
}
