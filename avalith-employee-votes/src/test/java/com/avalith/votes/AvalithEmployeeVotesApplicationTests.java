package com.avalith.votes;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.avalith.votes.model.Area;
import com.avalith.votes.model.Employee;
import com.avalith.votes.repository.AreaRepository;
import com.avalith.votes.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class AvalithEmployeeVotesApplicationTests {

	@Autowired
	private EmployeeRepository repoEmp;
	@Autowired AreaRepository repoArea;
	@Autowired BCryptPasswordEncoder encoder;
	
	@Test
	public void create_area_test() {
//		Area area = new Area("Team player", "avalith");
		Area area = new Area("Technical referent", "avalith");
//		Area area = new Area("Key Player", "avalith");		
		Area areaSaved = repoArea.save(area);
		assertTrue(repoArea.findAll().size() > 0);
	}

	@Test
	public void create_employee_test() {
		Employee emp = new Employee();
		emp.setFirtsName("Pepe");
		emp.setLastName("Lopez");
		emp.setUserName("pepe");
		emp.setPassword(encoder.encode("pepe"));
		emp.setUserCreate("avalith");
		emp.setDateCreate(new Date());
		Employee empSaved = repoEmp.save(emp);

		assertTrue(empSaved.getPassword().equalsIgnoreCase(emp.getPassword()));
	}

}
