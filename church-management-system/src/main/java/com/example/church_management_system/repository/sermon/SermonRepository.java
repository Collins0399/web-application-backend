package com.example.church_management_system.repository.sermon;

import com.example.church_management_system.Models.sermon.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SermonRepository extends JpaRepository<Sermon, Long> {

}
