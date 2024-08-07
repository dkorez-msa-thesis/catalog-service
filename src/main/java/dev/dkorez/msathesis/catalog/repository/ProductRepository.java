package dev.dkorez.msathesis.catalog.repository;

import dev.dkorez.msathesis.catalog.entity.ProductDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductDao> {

    public List<ProductDao> findProducts(String query) {
        return list(" (lower(name) LIKE ?1 OR lower(description) LIKE ?2) AND active = true",
                query.toLowerCase() + "%", query.toLowerCase() + "%");
    }

    public List<ProductDao> findByCategory(Long categoryId) {
        return list(" category.id = ?1 AND active = true", categoryId);
    }

    public List<ProductDao> findByBrand(String brand) {
        return list(" brand.name = ?1 AND active = true", brand);
    }

    public List<ProductDao> findByTag(String tag) {
        TypedQuery<ProductDao> query = getEntityManager().createQuery(
                "SELECT p FROM ProductDao p JOIN p.tags t WHERE t.name = :tagName AND p.active = true",
                ProductDao.class
        );
        query.setParameter("tagName", tag);
        return query.getResultList();
    }
}
