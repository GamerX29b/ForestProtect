package com.example.ForestProtect.Base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long>{
    @Query(value = "SELECT * FROM public.photos WHERE verification = true ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
    List<Photos> get4Photo();
    @Query(value = "SELECT * FROM public.photos WHERE verification != true ORDER BY RANDOM() LIMIT 1 ", nativeQuery = true)
    Photos getNotVerifyPhoto();
}

