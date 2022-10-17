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
import javax.persistence.Persistence;

/**
 *
 * @author HAHAHA
 */
public class PengelolaanDataJpaController implements Serializable {

    public PengelolaanDataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PWSFirstMeet_C_PWSFirstMeet_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PengelolaanDataJpaController() {
    }
    

    public void create(PengelolaanData pengelolaanData) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Barang kodeBarangOrphanCheck = pengelolaanData.getKodeBarang();
        if (kodeBarangOrphanCheck != null) {
            PengelolaanData oldPengelolaanDataOfKodeBarang = kodeBarangOrphanCheck.getPengelolaanData();
            if (oldPengelolaanDataOfKodeBarang != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Barang " + kodeBarangOrphanCheck + " already has an item of type PengelolaanData whose kodeBarang column cannot be null. Please make another selection for the kodeBarang field.");
            }
        }
        Pegawai idPegawaiOrphanCheck = pengelolaanData.getIdPegawai();
        if (idPegawaiOrphanCheck != null) {
            PengelolaanData oldPengelolaanDataOfIdPegawai = idPegawaiOrphanCheck.getPengelolaanData();
            if (oldPengelolaanDataOfIdPegawai != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Pegawai " + idPegawaiOrphanCheck + " already has an item of type PengelolaanData whose idPegawai column cannot be null. Please make another selection for the idPegawai field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barang kodeBarang = pengelolaanData.getKodeBarang();
            if (kodeBarang != null) {
                kodeBarang = em.getReference(kodeBarang.getClass(), kodeBarang.getKodeBarang());
                pengelolaanData.setKodeBarang(kodeBarang);
            }
            Pegawai idPegawai = pengelolaanData.getIdPegawai();
            if (idPegawai != null) {
                idPegawai = em.getReference(idPegawai.getClass(), idPegawai.getIdPegawai());
                pengelolaanData.setIdPegawai(idPegawai);
            }
            em.persist(pengelolaanData);
            if (kodeBarang != null) {
                kodeBarang.setPengelolaanData(pengelolaanData);
                kodeBarang = em.merge(kodeBarang);
            }
            if (idPegawai != null) {
                idPegawai.setPengelolaanData(pengelolaanData);
                idPegawai = em.merge(idPegawai);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPengelolaanData(pengelolaanData.getIdPengelolaan()) != null) {
                throw new PreexistingEntityException("PengelolaanData " + pengelolaanData + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PengelolaanData pengelolaanData) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PengelolaanData persistentPengelolaanData = em.find(PengelolaanData.class, pengelolaanData.getIdPengelolaan());
            Barang kodeBarangOld = persistentPengelolaanData.getKodeBarang();
            Barang kodeBarangNew = pengelolaanData.getKodeBarang();
            Pegawai idPegawaiOld = persistentPengelolaanData.getIdPegawai();
            Pegawai idPegawaiNew = pengelolaanData.getIdPegawai();
            List<String> illegalOrphanMessages = null;
            if (kodeBarangNew != null && !kodeBarangNew.equals(kodeBarangOld)) {
                PengelolaanData oldPengelolaanDataOfKodeBarang = kodeBarangNew.getPengelolaanData();
                if (oldPengelolaanDataOfKodeBarang != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Barang " + kodeBarangNew + " already has an item of type PengelolaanData whose kodeBarang column cannot be null. Please make another selection for the kodeBarang field.");
                }
            }
            if (idPegawaiNew != null && !idPegawaiNew.equals(idPegawaiOld)) {
                PengelolaanData oldPengelolaanDataOfIdPegawai = idPegawaiNew.getPengelolaanData();
                if (oldPengelolaanDataOfIdPegawai != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Pegawai " + idPegawaiNew + " already has an item of type PengelolaanData whose idPegawai column cannot be null. Please make another selection for the idPegawai field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (kodeBarangNew != null) {
                kodeBarangNew = em.getReference(kodeBarangNew.getClass(), kodeBarangNew.getKodeBarang());
                pengelolaanData.setKodeBarang(kodeBarangNew);
            }
            if (idPegawaiNew != null) {
                idPegawaiNew = em.getReference(idPegawaiNew.getClass(), idPegawaiNew.getIdPegawai());
                pengelolaanData.setIdPegawai(idPegawaiNew);
            }
            pengelolaanData = em.merge(pengelolaanData);
            if (kodeBarangOld != null && !kodeBarangOld.equals(kodeBarangNew)) {
                kodeBarangOld.setPengelolaanData(null);
                kodeBarangOld = em.merge(kodeBarangOld);
            }
            if (kodeBarangNew != null && !kodeBarangNew.equals(kodeBarangOld)) {
                kodeBarangNew.setPengelolaanData(pengelolaanData);
                kodeBarangNew = em.merge(kodeBarangNew);
            }
            if (idPegawaiOld != null && !idPegawaiOld.equals(idPegawaiNew)) {
                idPegawaiOld.setPengelolaanData(null);
                idPegawaiOld = em.merge(idPegawaiOld);
            }
            if (idPegawaiNew != null && !idPegawaiNew.equals(idPegawaiOld)) {
                idPegawaiNew.setPengelolaanData(pengelolaanData);
                idPegawaiNew = em.merge(idPegawaiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pengelolaanData.getIdPengelolaan();
                if (findPengelolaanData(id) == null) {
                    throw new NonexistentEntityException("The pengelolaanData with id " + id + " no longer exists.");
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
            PengelolaanData pengelolaanData;
            try {
                pengelolaanData = em.getReference(PengelolaanData.class, id);
                pengelolaanData.getIdPengelolaan();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pengelolaanData with id " + id + " no longer exists.", enfe);
            }
            Barang kodeBarang = pengelolaanData.getKodeBarang();
            if (kodeBarang != null) {
                kodeBarang.setPengelolaanData(null);
                kodeBarang = em.merge(kodeBarang);
            }
            Pegawai idPegawai = pengelolaanData.getIdPegawai();
            if (idPegawai != null) {
                idPegawai.setPengelolaanData(null);
                idPegawai = em.merge(idPegawai);
            }
            em.remove(pengelolaanData);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PengelolaanData> findPengelolaanDataEntities() {
        return findPengelolaanDataEntities(true, -1, -1);
    }

    public List<PengelolaanData> findPengelolaanDataEntities(int maxResults, int firstResult) {
        return findPengelolaanDataEntities(false, maxResults, firstResult);
    }

    private List<PengelolaanData> findPengelolaanDataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PengelolaanData.class));
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

    public PengelolaanData findPengelolaanData(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PengelolaanData.class, id);
        } finally {
            em.close();
        }
    }

    public int getPengelolaanDataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PengelolaanData> rt = cq.from(PengelolaanData.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
