package com.clubcard.ejb.repository;

import com.clubcard.entities.Company;
import com.clubcard.generic.repository.GenericRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CompanyRepository extends GenericRepository<Company, Long> {

    @PersistenceContext(unitName = "ClubCardPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Company> getEntityClass() {
        return Company.class;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Company> findBySocnetUidAndSocnetType(String socnetUid, short socnetType){
        List<Company> companyList = new ArrayList<Company>();
        return companyList;
    }
}