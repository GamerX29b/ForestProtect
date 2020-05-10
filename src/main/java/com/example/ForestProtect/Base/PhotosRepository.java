package com.example.ForestProtect.Base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long>{
    @Query(value = "SELECT * FROM public.photos WHERE verification = true ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
    List<Photos> get4Photo();
    @Query(value = "SELECT * FROM public.photos WHERE verification != true ORDER BY RANDOM() LIMIT 1 ", nativeQuery = true)
    Photos getNotVerifyPhoto();

    Page<Photos> findAllByDateBetween(Date dateStart, Date dateEnd, Pageable pageable);

    @Query(value = "select a from public.photos a where a.Date <= :Date", nativeQuery = true)
    Page<Photos> findAllWithDateBefore(@Param("Date") Date creationDateTime, Pageable pageable);

    @Query(value = "select a from public.photos a where a.Date >= :Date", nativeQuery = true)
    Page<Photos> findAllDateAfter(@Param("Date") Date Date, Pageable pageable);


}

