/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PWSFirstMeet_C.PWSFirstMeet;

import PWSFirstMeet_C.PWSFirstMeet.exceptions.IllegalOrphanException;
import PWSFirstMeet_C.PWSFirstMeet.exceptions.NonexistentEntityException;
import PWSFirstMeet_C.PWSFirstMeet.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HAHAHA
 */
public class TransaksiJpaController implements Serializable {

    public TransaksiJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transaksi transaksi) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Pembeli idPembeliOrphanCheck = transaksi.getIdPembeli();
        if (idPembeliOrphanCheck != null) {
            Transaksi oldTransaksiOfIdPembeli = idPembeliOrphanCheck.getTransaksi();
            if (oldTransaksiOfIdPembeli != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Pembeli " + idPembeliOrphanCheck + " already has an item of type Transaksi whose idPembeli column cannot be null. Please make another selection for the idPembeli field.");
            }
        }
        Barang kodeBarangOrphanCheck = transaksi.getKodeBarang();
        if (kodeBarangOrphanCheck != null) {
            Transaksi oldTransaksiOfKodeBarang = kodeBarangOrphanCheck.getTransaksi();
            if (oldTransaksiOfKodeBarang != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Barang " + kodeBarangOrphanCheck + " already has an item of type Transaksi whose kodeBarang column cannot be null. Please make another selection for the kodeBarang field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pembeli idPembeli = transaksi.getIdPembeli();
            if (idPembeli != null) {
                idPembeli = em.getReference(idPembeli.getClass(), idPembeli.getIdPembeli());
                transaksi.setIdPembeli(idPembeli);
            }
            Barang kodeBarang = transaksi.getKodeBarang();
            if (kodeBarang != null) {
                kodeBarang = em.getReference(kodeBarang.getClass(), kodeBarang.getKodeBarang());
                transaksi.setKodeBarang(kodeBarang);
            }
            em.persist(transaksi);
            if (idPembeli != null) {
                idPembeli.setTransaksi(transaksi);
                idPembeli = em.merge(idPembeli);
            }
            if (kodeBarang != null) {
                kodeBarang.setTransaksi(transaksi);
                kodeBarang = em.merge(kodeBarang);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransaksi(transaksi.getIdTransaksi()) != null) {
                throw new PreexistingEntityException("Transaksi " + transaksi + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transaksi transaksi) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaksi persistentTransaksi = em.find(Transaksi.class, transaksi.getIdTransaksi());
            Pembeli idPembeliOld = persistentTransaksi.getIdPembeli();
            Pembeli idPembeliNew = transaksi.getIdPembeli();
            Barang kodeBarangOld = persistentTransaksi.getKodeBarang();
            Barang kodeBarangNew = transaksi.getKodeBarang();
            List<String> illegalOrphanMessages = null;
            if (idPembeliNew != null && !idPembeliNew.equals(idPembeliOld)) {
                Transaksi oldTransaksiOfIdPembeli = idPembeliNew.getTransaksi();
                if (oldTransaksiOfIdPembeli != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Pembeli " + idPembeliNew + " already has an item of type Transaksi whose idPembeli column cannot be null. Please make another selection for the idPembeli field.");
                }
            }
            if (kodeBarangNew != null && !kodeBarangNew.equals(kodeBarangOld)) {
                Transaksi oldTransaksiOfKodeBarang = kodeBarangNew.getTransaksi();
                if (oldTransaksiOfKodeBarang != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Barang " + kodeBarangNew + " already has an item of type Transaksi whose kodeBarang column cannot be null. Please make another selection for the kodeBarang field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPembeliNew != null) {
                idPembeliNew = em.getReference(idPembeliNew.getClass(), idPembeliNew.getIdPembeli());
                transaksi.setIdPembeli(idPembeliNew);
            }
            if (kodeBarangNew != null) {
                kodeBarangNew = em.getReference(kodeBarangNew.getClass(), kodeBarangNew.getKodeBarang());
                transaksi.setKodeBarang(kodeBarangNew);
            }
            transaksi = em.merge(transaksi);
            if (idPembeliOld != null && !idPembeliOld.equals(idPembeliNew)) {
                idPembeliOld.setTransaksi(null);
                idPembeliOld = em.merge(idPembeliOld);
            }
            if (idPembeliNew != null && !idPembeliNew.equals(idPembeliOld)) {
                idPembeliNew.setTransaksi(transaksi);
                idPembeliNew = em.merge(idPembeliNew);
            }
            if (kodeBarangOld != null && !kodeBarangOld.equals(kodeBarangNew)) {
                kodeBarangOld.setTransaksi(null);
                kodeBarangOld = em.merge(kodeBarangOld);
            }
            if (kodeBarangNew != null && !kodeBarangNew.equals(kodeBarangOld)) {
                kodeBarangNew.setTransaksi(transaksi);
                kodeBarangNew = em.merge(kodeBarangNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transaksi.getIdTransaksi();
                if (findTransaksi(id) == null) {
                    throw new NonexistentEntityException("The transaksi with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaksi transaksi;
            try {
                transaksi = em.getReference(Transaksi.class, id);
                transaksi.getIdTransaksi();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transaksi with id " + id + " no longer exists.", enfe);
            }
            Pembeli idPembeli = transaksi.getIdPembeli();
            if (idPembeli != null) {
                idPembeli.setTransaksi(null);
                idPembeli = em.merge(idPembeli);
            }
            Barang kodeBarang = transaksi.getKodeBarang();
            if (kodeBarang != null) {
                kodeBarang.setTransaksi(null);
                kodeBarang = em.merge(kodeBarang);
            }
            em.remove(transaksi);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transaksi> findTransaksiEntities() {
        return findTransaksiEntities(true, -1, -1);
    }

    public List<Transaksi> findTransaksiEntities(int maxResults, int firstResult) {
        return findTransaksiEntities(false, maxResults, firstResult);
    }

    private List<Transaksi> findTransaksiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transaksi.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Transaksi findTransaksi(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transaksi.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransaksiCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transaksi> rt = cq.from(Transaksi.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
