package com.mumscheduler.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.schedule.factory.ScheduleFactory;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;
import com.mumscheduler.schedule.repository.ScheduleRepository;
import com.mumscheduler.section.model.Section;

@Service
public class ScheduleService implements ScheduleServiceInterface {

	@Autowired
	private ScheduleRepository repo;

	@Override
	public List<Schedule> getScheduleList() {
		List<Schedule> schedules = new ArrayList<>();
		repo.findAll().forEach(schedules::add);
		return schedules;
	}

	@Override
	public ScheduleFacade getScheduleById(long id) {
		Schedule schedule = repo.findOne(id);
		return ScheduleFactory.validateSchedule(schedule);
	}

	@Override
	public Schedule saveSchedule(Schedule schedule) {
		return repo.save(schedule);
	}

	@Override
	public ScheduleFacade generateSchedule(long id) {
		ScheduleFacade sf = getScheduleById(id);

		if (sf.isActionSuccess()) {
			sf = checkBlockRequirements(sf);
			sf = checkSectionRequirements(sf);
		}
		/*
		 * TODO: Change status and save.
		 */

		return sf;
	}

	@Override
	public void checkUpdateRequirements(Schedule schedule) {
		/*
		 * TODO: Review need for this.
		 */

	}

	@Override
	public void updateSchedule(Schedule schedule) {
		Schedule s = repo.save(schedule);
	}

	@Override
	public void checkDeleteRequirements(long id) {
		/*
		 * TODO: For as long as the first block has not started.
		 */

	}

	@Override
	public void removeScheduleById(long id) {
		repo.delete(id);
	}

	@Override
	public ScheduleFacade checkBlockRequirements(ScheduleFacade sf) {
		if (sf.isActionSuccess()) {
			/*
			 * TODO: Confirm there exists a sufficient number of blocks. 10 blocks are
			 * required.
			 */
			Set<Block> blocks = sf.getSchedule().getBlocks();

			if (blocks.size() >= 10) {
				sf = addBlocksToSchedule(sf, blocks);
			} else {
				sf.setActionSuccess(false);
				sf.setActionResponse(ScheduleFactory.INSUFFICIENT_BLOCKS);
			}
		}

		return sf;
	}

	@Override
	public ScheduleFacade addBlocksToSchedule(ScheduleFacade sf, Set<Block> blocks) {
		if (sf.isActionSuccess()) {
			sf.getSchedule().setBlocks(blocks);
		}

		return sf;
	}

	@Override
	public ScheduleFacade checkSectionRequirements(ScheduleFacade sf) {
		if (sf.isActionSuccess()) {
			/*
			 * TODO: First block has FPP and MPP Second block has MPP for FPP track students
			 * and Sections (classes) enough to cater for total number of non-fpp track
			 * students The next 4 blocks cater for total amount of students The next 5th
			 * block caters for OPT track and US residents The last 3 blocks cater for US
			 * residents
			 */

			// checkFppandMpp(sf.getSchedule().getEntry());
			// checkMppForFppTrack(sf.getSchedule().getEntry());
			// checkSectionsForNonFppTrack(sf.getSchedule().getEntry());
			// checkSectionsForAllTracks();
		}

		return sf;
	}

}
