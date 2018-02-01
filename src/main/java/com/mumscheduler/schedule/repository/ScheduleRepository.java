package com.mumscheduler.schedule.repository;

import org.springframework.data.repository.CrudRepository;

import com.mumscheduler.schedule.model.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

}
