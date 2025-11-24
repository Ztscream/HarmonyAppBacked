package com.harmonyapp.backend.repository;

import com.harmonyapp.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
