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
@Table(name = "transaksi")
@NamedQueries({
    @NamedQuery(name = "Transaksi.findAll", query = "SELECT t FROM Transaksi t"),
    @NamedQuery(name = "Transaksi.findByIdTransaksi", query = "SELECT t FROM Transaksi t WHERE t.idTransaksi = :idTransaksi"),
    @NamedQuery(name = "Transaksi.findByQty", query = "SELECT t FROM Transaksi t WHERE t.qty = :qty"),
    @NamedQuery(name = "Transaksi.findByTotalBayar", query = "SELECT t FROM Transaksi t WHERE t.totalBayar = :totalBayar"),
    @NamedQuery(name = "Transaksi.findByAlamatPembeli", query = "SELECT t FROM Transaksi t WHERE t.alamatPembeli = :alamatPembeli"),
    @NamedQuery(name = "Transaksi.findByNohpPembeli", query = "SELECT t FROM Transaksi t WHERE t.nohpPembeli = :nohpPembeli")})
public class Transaksi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_transaksi")
    private Integer idTransaksi;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @Column(name = "total_bayar")
    private int totalBayar;
    @Basic(optional = false)
    @Column(name = "alamat_pembeli")
    private String alamatPembeli;
    @Basic(optional = false)
    @Column(name = "nohp_pembeli")
    private String nohpPembeli;
    @JoinColumn(name = "id_pembeli", referencedColumnName = "id_pembeli")
    @OneToOne(optional = false)
    private Pembeli idPembeli;
    @JoinColumn(name = "kode_barang", referencedColumnName = "kode_barang")
    @OneToOne(optional = false)
    private Barang kodeBarang;

    public Transaksi() {
    }

    public Transaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Transaksi(Integer idTransaksi, int qty, int totalBayar, String alamatPembeli, String nohpPembeli) {
        this.idTransaksi = idTransaksi;
        this.qty = qty;
        this.totalBayar = totalBayar;
        this.alamatPembeli = alamatPembeli;
        this.nohpPembeli = nohpPembeli;
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getAlamatPembeli() {
        return alamatPembeli;
    }

    public void setAlamatPembeli(String alamatPembeli) {
        this.alamatPembeli = alamatPembeli;
    }

    public String getNohpPembeli() {
        return nohpPembeli;
    }

    public void setNohpPembeli(String nohpPembeli) {
        this.nohpPembeli = nohpPembeli;
    }

    public Pembeli getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(Pembeli idPembeli) {
        this.idPembeli = idPembeli;
    }

    public Barang getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(Barang kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaksi != null ? idTransaksi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaksi)) {
            return false;
        }
        Transaksi other = (Transaksi) object;
        if ((this.idTransaksi == null && other.idTransaksi != null) || (this.idTransaksi != null && !this.idTransaksi.equals(other.idTransaksi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PWSFirstMeet_C.PWSFirstMeet.Transaksi[ idTransaksi=" + idTransaksi + " ]";
    }
    
}
