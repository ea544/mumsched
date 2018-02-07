package com.mumscheduler.schedule.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.web.context.request.RequestContextHolder;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.course.model.Course;
import com.mumscheduler.course.service.CourseService;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;
import com.mumscheduler.schedule.service.ScheduleServiceInterface;
import com.mumscheduler.section.model.Section;

public final class ScheduleFactory {

	private static final HashMap<String, ScheduleFacade> SF_FAILED = new HashMap<>();

	public static final String FPP_COURSE_CODE = "FPP";
	public static final String INSUFFICIENT_BLOCKS = "You don't have a sufficient number of blocks to generate this schedule.";
	public static final String MPP_COURSE_CODE = "MPP";
	public static final String NO_COURSES_FOUND = "There are no available courses. You need at least one course";
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

	private static CourseService getCourseService() {
		return new CourseService();
	}

	private static String getCourseResponse(String courseCode) {
		if (courseCode == "") {
			return NO_COURSES_FOUND;
		} else {
			return "There is no course with code: " + courseCode;
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
			Section section = new Section();
			Course course = null;
			if (courseCode == "") {
				course = getCourseForSection();
			} else {
				course = getCourseForSection(courseCode);
			}
			section.setCapacity((int) capacity);

			/*
			 * TODO: Some process needs to exist for course and faculty
			 */
			if (course != null) {
				section.setCourse(course);
			} else {
				sf.setActionSuccess(false);
				sf.setActionResponse(getCourseResponse(courseCode));
			}
			section.setFaculty(null);
		}

		return sf;
	}

	private static Course getCourseForSection() {
		Course course = null;
		List<Course> courses = getCourseService().getCourseList();

		if (courses.size() == 0) {
			Random rand = new Random();
			rand.nextInt(courses.size());

			course = courses.get(rand.nextInt(courses.size()));
		}

		return course;
	}

	private static Course getCourseForSection(String courseCode) {
		List<Course> courses = getCourseService().getCourseList();

		return courses.stream().filter(c -> c.getCode() == courseCode).distinct().findFirst().orElse(null);
	}

}
