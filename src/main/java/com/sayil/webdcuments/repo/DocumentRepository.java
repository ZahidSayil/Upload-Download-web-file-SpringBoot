package com.sayil.webdcuments.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sayil.webdcuments.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
