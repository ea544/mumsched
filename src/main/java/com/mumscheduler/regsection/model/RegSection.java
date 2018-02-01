package com.mumscheduler.regsection.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class RegSection {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)	
		private long StdId;
		
		@NotEmpty
		private String block;
		private String course;
		
		public long getStdId() {
			return StdId;
		}
		public void setStdId(long stdId) {
			StdId = stdId;
		}
		public String getBlock() {
			return block;
		}
		public void setBlock(String block) {
			this.block = block;
		}
		public String getCourse() {
			return course;
		}
		public void setCourse(String course) {
			this.course = course;
		}
		public RegSection(long stdId, String block, String course) {
			super();
			StdId = stdId;
			this.block = block;
			this.course = course;
		}
		
		public RegSection() {}
		@Override
		public String toString() {
			return "RegSection [StdId=" + StdId + ", block=" + block + ", course=" + course + ", toString()="
					+ super.toString() + "]";
		};
		
		
		
}
