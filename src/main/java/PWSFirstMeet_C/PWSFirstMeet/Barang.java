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
@Table(name = "barang")
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b"),
    @NamedQuery(name = "Barang.findByKodeBarang", query = "SELECT b FROM Barang b WHERE b.kodeBarang = :kodeBarang"),
    @NamedQuery(name = "Barang.findByNamaBarang", query = "SELECT b FROM Barang b WHERE b.namaBarang = :namaBarang"),
    @NamedQuery(name = "Barang.findByMerk", query = "SELECT b FROM Barang b WHERE b.merk = :merk"),
    @NamedQuery(name = "Barang.findByHarga", query = "SELECT b FROM Barang b WHERE b.harga = :harga"),
    @NamedQuery(name = "Barang.findByKategori", query = "SELECT b FROM Barang b WHERE b.kategori = :kategori"),
    @NamedQuery(name = "Barang.findByQty", query = "SELECT b FROM Barang b WHERE b.qty = :qty")})
public class Barang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_barang")
    private Integer kodeBarang;
    @Basic(optional = false)
    @Column(name = "nama_barang")
    private String namaBarang;
    @Basic(optional = false)
    @Column(name = "merk")
    private String merk;
    @Basic(optional = false)
    @Column(name = "harga")
    private int harga;
    @Basic(optional = false)
    @Column(name = "kategori")
    private String kategori;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "kodeBarang")
    private Transaksi transaksi;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "kodeBarang")
    private PengelolaanData pengelolaanData;

    public Barang() {
    }

    public Barang(Integer kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public Barang(Integer kodeBarang, String namaBarang, String merk, int harga, String kategori, int qty) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.merk = merk;
        this.harga = harga;
        this.kategori = kategori;
        this.qty = qty;
    }

    public Integer getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(Integer kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public PengelolaanData getPengelolaanData() {
        return pengelolaanData;
    }

    public void setPengelolaanData(PengelolaanData pengelolaanData) {
        this.pengelolaanData = pengelolaanData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeBarang != null ? kodeBarang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.kodeBarang == null && other.kodeBarang != null) || (this.kodeBarang != null && !this.kodeBarang.equals(other.kodeBarang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PWSFirstMeet_C.PWSFirstMeet.Barang[ kodeBarang=" + kodeBarang + " ]";
    }
    
}
