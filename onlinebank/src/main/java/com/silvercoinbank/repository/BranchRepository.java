package com.silvercoinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silvercoinbank.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{

}
