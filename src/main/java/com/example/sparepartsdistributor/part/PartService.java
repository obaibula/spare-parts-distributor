package com.example.sparepartsdistributor.part;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface defining the contract for managing spare parts.
 * <p>By using an interface, it allows Spring to create JDK dynamic proxies if the implementing class implements at least one interface.
 * When the implementing class doesn't implement any interfaces, Spring falls back to using CGLIB proxies.
 * According to the Spring documentation, "JDK dynamic proxies are preferred whenever you have a choice."
 * @see <a href="https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch08s06.html">Spring Documentation - 8.6 Proxying mechanisms</a>
 */
public interface PartService {
    Part save(Part part);
    List<PartDto> findAll(Pageable pageable);

    PartDto findById(Long partId);
}
