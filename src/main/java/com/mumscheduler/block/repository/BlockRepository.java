package com.mumscheduler.block.repository;

import com.mumscheduler.block.model.Block;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("blockRepository")
public interface BlockRepository extends JpaRepository<Block, Long> {

	/**
	 * get a block for a given section id number
	 * @param id
	 */
	@Query(value="SELECT * FROM block_sections s WHERE s.section_id=:id", nativeQuery=true)
	public List<Block> getBlocksBySectionId(@Param("id") Long id);
}
