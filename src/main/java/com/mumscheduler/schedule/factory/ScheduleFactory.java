package com.mumscheduler.schedule.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.context.request.RequestContextHolder;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.course.model.Course;
import com.mumscheduler.course.service.CourseServiceInterface;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;
import com.mumscheduler.schedule.service.ScheduleServiceInterface;
import com.mumscheduler.section.model.Section;

public final class ScheduleFactory {

	private static final HashMap<String, ScheduleFacade> SF_FAILED = new HashMap<>();

	public static CourseServiceInterface courseService;

	public static final String FPP_COURSE_CODE = "FPP";
	public static final String INSUFFICIENT_BLOCKS = "You don't have a sufficient number of blocks to generate this schedule.";
	public static final int MIN_BLOCKS_REQUIRED = 10;
	public static final String MPP_COURSE_CODE = "MPP";
	public static final String NO_COURSES_FOUND = "You need at least one more course, FPP and MPP excluded.";
	public static final String NO_COURSE_FOUND_FOR = "There is no course with code: ";
	public static final String NO_SCHEDULE_FOUND = "No schedule found !";

	private static String getSessionId() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}

	public static ScheduleFacade getScheduleById(ScheduleServiceInterface svc, long id) {
		ScheduleFacade sf = null;

		if (SF_FAILED.get(getSessionId()) == null) {
			sf = svc.getScheduleById(id);
		} else {
			sf = SF_FAILED.get(getSessionId());
			SF_FAILED.put(getSessionId(), null);
		}

		return sf;
	}

	public static void keepStateOnFail(ScheduleFacade sf) {
		if (!sf.isActionSuccess()) {
			SF_FAILED.put(getSessionId(), sf);
		}
	}

	public static CourseServiceInterface getCourseService() {
		return ScheduleFactory.courseService;
	}

	private static String getCourseResponse(String courseCode) {
		if (courseCode == "") {
			return NO_COURSES_FOUND;
		} else {
			return NO_COURSE_FOUND_FOR + courseCode;
		}
	}

	public static ScheduleFacade createEmptyScheduleFacade() {
		return new ScheduleFacade(true, "", null);
	}

	public static ScheduleFacade createScheduleFacade(Schedule schedule) {
		return new ScheduleFacade(true, "", schedule);
	}

	public static ScheduleFacade validateSchedule(Schedule schedule) {
		ScheduleFacade sf = ScheduleFactory.createScheduleFacade(schedule);

		if (schedule == null) {
			sf.setActionSuccess(false);
			sf.setActionResponse(NO_SCHEDULE_FOUND);
		}

		return sf;
	}

	public static ScheduleFacade createSections(ScheduleFacade sf, Block block, long capacity, String courseCode) {
		if (sf.isActionSuccess()) {
			Course course = getCourseByCode(courseCode);
			/*
			 * TODO: Some process needs to exist for course and faculty
			 */
			if (course != null) {
				block.getSections().addAll(createSectionsBasedOnCourse(courseCode, capacity));
				sf.getSchedule().getBlocks().add(block);

			} else {
				sf.setActionSuccess(false);
				sf.setActionResponse(getCourseResponse(courseCode));
			}
		}

		return sf;
	}

	private static Course getCourseForSection() {
		Course course = null;
		List<Course> courses = getCourseService().getCourseList();
		courses.sort((c1, c2) -> c1.getCapacity().compareTo(c2.getCapacity()));

		if (courses.size() > 0) {
			courses = courses.stream()
					.filter(c -> !(c.getCode().equals(FPP_COURSE_CODE) || c.getCode().equals(MPP_COURSE_CODE)))
					.collect(Collectors.toList());
			Random rand = new Random();
			rand.nextInt(courses.size());

			course = courses.get(rand.nextInt(courses.size()));
		}

		return course;
	}
	
	private static Course getCourseByCode(String courseCode) {
		Course course = null;
		if (courseCode == "") {
			course = getCourseForSection();
		} else {
			course = getCourseForSection(courseCode);
		}
		
		return course;
	}

	private static List<Section> createSectionsBasedOnCourse(String courseCode, long capacity) {
		Course course = getCourseByCode(courseCode);
		List<Section> sections = new ArrayList<>();

		if (course.getCapacity() == capacity) {
			sections.add(new Section(course.getCapacity(), course, null));
		} else {
			long remCap = capacity;
			
			while(remCap > 0) {
				sections.add(new Section(course.getCapacity(), course, null));
				course = getCourseByCode(courseCode);
				remCap -= course.getCapacity();
			}
		}

		return sections;
	}

	private static Course getCourseForSection(String courseCode) {
		List<Course> courses = getCourseService().getCourseList();

		return courses.stream().filter(c -> c.getCode().equals(courseCode)).distinct().findFirst().orElse(null);
	}

}
