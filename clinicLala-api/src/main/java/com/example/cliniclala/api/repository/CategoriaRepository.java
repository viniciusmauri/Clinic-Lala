/**
 * 
 */
package com.example.cliniclala.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cliniclala.api.model.Categoria;

/**
 * @author Vinic
 *
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
