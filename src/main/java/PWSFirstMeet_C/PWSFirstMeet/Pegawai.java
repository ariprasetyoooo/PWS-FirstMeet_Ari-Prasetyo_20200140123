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
@Table(name = "pegawai")
@NamedQueries({
    @NamedQuery(name = "Pegawai.findAll", query = "SELECT p FROM Pegawai p"),
    @NamedQuery(name = "Pegawai.findByIdPegawai", query = "SELECT p FROM Pegawai p WHERE p.idPegawai = :idPegawai"),
    @NamedQuery(name = "Pegawai.findByNamaPegawai", query = "SELECT p FROM Pegawai p WHERE p.namaPegawai = :namaPegawai"),
    @NamedQuery(name = "Pegawai.findByNohpPegawai", query = "SELECT p FROM Pegawai p WHERE p.nohpPegawai = :nohpPegawai"),
    @NamedQuery(name = "Pegawai.findByAlamatPegawai", query = "SELECT p FROM Pegawai p WHERE p.alamatPegawai = :alamatPegawai")})
public class Pegawai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pegawai")
    private Integer idPegawai;
    @Basic(optional = false)
    @Column(name = "nama_pegawai")
    private String namaPegawai;
    @Basic(optional = false)
    @Column(name = "nohp_pegawai")
    private String nohpPegawai;
    @Basic(optional = false)
    @Column(name = "alamat_pegawai")
    private String alamatPegawai;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPegawai")
    private PengelolaanData pengelolaanData;

    public Pegawai() {
    }

    public Pegawai(Integer idPegawai) {
        this.idPegawai = idPegawai;
    }

    public Pegawai(Integer idPegawai, String namaPegawai, String nohpPegawai, String alamatPegawai) {
        this.idPegawai = idPegawai;
        this.namaPegawai = namaPegawai;
        this.nohpPegawai = nohpPegawai;
        this.alamatPegawai = alamatPegawai;
    }

    public Integer getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Integer idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getNohpPegawai() {
        return nohpPegawai;
    }

    public void setNohpPegawai(String nohpPegawai) {
        this.nohpPegawai = nohpPegawai;
    }

    public String getAlamatPegawai() {
        return alamatPegawai;
    }

    public void setAlamatPegawai(String alamatPegawai) {
        this.alamatPegawai = alamatPegawai;
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
        hash += (idPegawai != null ? idPegawai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pegawai)) {
            return false;
        }
        Pegawai other = (Pegawai) object;
        if ((this.idPegawai == null && other.idPegawai != null) || (this.idPegawai != null && !this.idPegawai.equals(other.idPegawai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PWSFirstMeet_C.PWSFirstMeet.Pegawai[ idPegawai=" + idPegawai + " ]";
    }
    
}
