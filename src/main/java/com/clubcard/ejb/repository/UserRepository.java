package com.clubcard.ejb.repository;

import com.clubcard.entities.User;
import com.clubcard.generic.repository.GenericRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserRepository extends GenericRepository<User, Long> {

    @PersistenceContext(unitName = "ClubCardPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<User> getEntityClass() {
        return com.clubcard.entities.User.class;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User find(String socnetUid, Short socnetType){
        Query query = em.createNamedQuery("User.findBySocnetType");
        query.setParameter("socnetUid",socnetUid);
        query.setParameter("socnetType",socnetType);
        List<User> userList = query.getResultList();

        User user = null;
        if(!userList.isEmpty()){
            user = userList.get(0);
        }

        return user;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User findByAuthKey(String authKey){
        Query query = em.createNamedQuery("User.findByAuthKey");
        query.setParameter("authKey",authKey);

        return (User) query.getSingleResult();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User persist(User entity) {
        MessageDigest crypt = null;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            entity.setAuthKey(new BigInteger(1, crypt.digest()).toString(16));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        entity.setStatus(User.S_NEW_USER);

        return super.persist(entity);
    }
}
