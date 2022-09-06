package com.appnotes.vnote.repository;

import com.appnotes.vnote.model.Vnotedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Vnoterepository extends JpaRepository<Vnotedata,Long> {

    @Query(value = "Select * from vnotedata where topic like %?1%",nativeQuery = true)
    public List<Vnotedata> getbytopic (String topic);
    
    @Query(value = "Select * from vnotedata where topic like %?1%",nativeQuery = true)
    public List<Vnotedata> getbytopicres (String topic);

}
