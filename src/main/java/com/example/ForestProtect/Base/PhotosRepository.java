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

    @Query(value = "select * from public.photos a where a.Date <= ?1", nativeQuery = true)
    Page<Photos> findAllWithDateBefore(Date dateBefore, Pageable pageable);

    @Query(value = "select * from public.photos a where a.Date >= ?1", nativeQuery = true)
    Page<Photos> findAllDateAfter(Date dateAfter, Pageable pageable);

    @Query(value = "SELECT * FROM public.photos where public.photos.coordinates like '%' || :coordinates ||'%'", nativeQuery = true)
    Page<Photos> findByCoorinat(@Param("coordinates")String coordinates, Pageable pageable);


}

