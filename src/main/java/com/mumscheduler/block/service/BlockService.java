package com.mumscheduler.block.service;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.block.repository.BlockRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blockService")
public class BlockService implements BlockServiceInterface {

	@Autowired
	BlockRepository blockRepository;
	
	@Override
	public Block save(Block block) {
		return blockRepository.save(block);
	}

	@Override
	public Block getBlock(Long id) {
		return blockRepository.getOne(id);
	}

	
	@Override
	public List<Block> getBlockList() {
		return blockRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		blockRepository.delete(id);
	}

	@Override
	public List<Block> getBlocksBySectionId(Long id) {
		return blockRepository.getBlocksBySectionId(id);
	}

}
