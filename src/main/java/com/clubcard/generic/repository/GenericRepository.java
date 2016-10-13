package com.clubcard.generic.repository;

import com.clubcard.generic.Direction;
import com.clubcard.generic.entity.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GenericRepository<E extends GenericEntity<K>, K extends Serializable> {

    private static final String MESSAGE_ENTITY_NOT_FOUND = "%s with PK = '%s' not found";

    protected abstract EntityManager getEntityManager();

    protected abstract Class<E> getEntityClass();

    public E find(K id) {
        return find(id, LockModeType.NONE);
    }

    public List<E> find(K... ids) {
        List<E> findedObjects = new ArrayList();
        for (K id : ids) {
            findedObjects.add(find(id));
        }
        return findedObjects;
    }

    public List<E> find(Collection<K> ids) {
        List<E> findedObjects = new ArrayList();
        for (K id : ids) {
            findedObjects.add(find(id));
        }
        return findedObjects;
    }

    public E find(K id, LockModeType lockModeType) {
        return getEntityManager().find(getEntityClass(), id, lockModeType);
    }

    public E findOrExcept(K id) {
        E finded = find(id);
        if (finded == null) {
            throw new EntityNotFoundException(getEntityClass(), String.format(MESSAGE_ENTITY_NOT_FOUND, getEntityClass().getName(), id));
        }

        return finded;
    }

    public List<E> findOrExcept(K... ids) {
        List<E> findedObjects = new ArrayList();
        for (K id : ids) {
            findedObjects.add(findOrExcept(id));
        }
        return findedObjects;
    }

    public List<E> findOrExcept(Collection<K> ids) {
        List<E> findedObjects = new ArrayList();
        for (K id : ids) {
            findedObjects.add(findOrExcept(id));
        }

        return findedObjects;
    }

    public E findOrExcept(K id, LockModeType lockModeType) {
        E finded = find(id, lockModeType);
        if (finded == null) {
            throw new EntityNotFoundException(getEntityClass(), String.format(MESSAGE_ENTITY_NOT_FOUND, getEntityClass().getName(), id));
        }

        return finded;
    }

    public boolean exists(E entity) {
        return find(entity.getId()) != null;
    }

    public boolean exists(E... entities) {
        for (E entity : entities) {
            if (find(entity.getId()) == null) {
                return false;
            }
        }
        return true;
    }

    public boolean exists(Collection<E> entities) {
        return exists((E[]) entities.toArray(new GenericEntity[0]));
    }

    public void remove(E entity) {
        if (entity != null) {
            getEntityManager().remove(entity);
        }
    }

    public void remove(E... entities) {
        for (E entity : entities) {
            remove(entity);
        }

    }

    public void remove(Collection<E> entities) {
        remove((E[]) entities.toArray(new GenericEntity[0]));
    }

    public void remove(K id) {
        if (id != null) {
            E entity = find(id);
            remove(entity);
        }
    }

    public void remove(K... ids) {
        for (K id : ids) {
            remove(id);
        }

    }

    public E persist(E entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public void persist(E... entities) {
        for (E entity : entities) {
            persist(entity);
        }
    }

    public void persist(Collection<E> entities) {
        persist((E[]) entities.toArray(new GenericEntity[0]));
    }

    public E merge(E entity) {
        return getEntityManager().merge(entity);
    }

    public void merge(E... entities) {
        for (E entity : entities) {
            merge(entity);
        }
    }

    public void merge(Collection<E> entities) {
        merge((E[]) entities.toArray(new GenericEntity[0]));
    }

    public void refresh(E entity) {
        if (entity != null) {
            getEntityManager().refresh(entity);
        }
    }

    public void refresh(E... entities) {
        for (E entity : entities) {
            refresh(entity);
        }

    }

    public void refresh(Collection<E> entities) {
        refresh((E[]) entities.toArray(new GenericEntity[0]));
    }

    public void detach(E entity) {
        getEntityManager().detach(entity);
    }

    public void detach(E... entities) {
        for (E entity : entities) {
            detach(entity);
        }
    }

    public void detach(Collection<E> entities) {
        for (E entity : entities) {
            detach(entity);
        }
    }

    public void flush() {
        getEntityManager().flush();
    }

    public void lock(E entity, LockModeType lockModeType) {
        getEntityManager().lock(entity, lockModeType);
    }

    protected Order getOrder(Path path, Direction direction) {
        return direction == Direction.ASCENDING ? getEntityManager().getCriteriaBuilder().asc(path) : getEntityManager().getCriteriaBuilder().desc(path);
    }

    public Query nativeQuery(String query) {
        return getEntityManager().createNativeQuery(query, getEntityClass());
    }

    public TypedQuery<E> namedQuery(String queryName) {
        return getEntityManager().createNamedQuery(queryName, getEntityClass());
    }

    public TypedQuery<E> namedQuery(String queryName, int page, int count) {
        return namedQuery(queryName).setFirstResult(page * count).setMaxResults(count);
    }

    public List<E> findAll() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<E> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root);

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<E> findAll(String field, Direction direction) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<E> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root);
        criteriaQuery.orderBy(getOrder(root.get(field), direction));

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<E> findAll(String field, int page, int count, Direction direction) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<E> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root);

        if (field != null) {
            criteriaQuery.orderBy(getOrder(root.get(field), direction));
        }

        return getEntityManager().createQuery(criteriaQuery).setFirstResult(page * count).setMaxResults(count).getResultList();
    }

    public List<E> findAll(int page, int count) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<E> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root);

        return getEntityManager().createQuery(criteriaQuery).setFirstResult(page * count).setMaxResults(count).getResultList();
    }

    public int count() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<E> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(criteriaBuilder.count(root));
        Query query = getEntityManager().createQuery(criteriaQuery);

        return ((Long) query.getSingleResult()).intValue();
    }

}