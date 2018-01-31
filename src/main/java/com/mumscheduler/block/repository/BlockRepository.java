package com.mumscheduler.block.repository;

import com.mumscheduler.block.model.Block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("blockRepository")
public interface BlockRepository extends JpaRepository<Block, Long> {

}
