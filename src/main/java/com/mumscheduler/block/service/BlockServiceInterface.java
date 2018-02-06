package com.mumscheduler.block.service;

import com.mumscheduler.block.model.Block;

import java.util.List;

public interface BlockServiceInterface {

	public Block save(Block block);
	public Block getBlock(Long id);
	public List<Block> getBlockList();
	public void delete(Long id);
	public List<Block> getBlocksBySectionId(Long id);
	public boolean blockExists(Block block);
}
