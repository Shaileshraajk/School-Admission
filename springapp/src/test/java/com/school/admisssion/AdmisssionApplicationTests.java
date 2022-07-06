package com.school.admisssion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.school.admisssion.model.User;
import com.school.admisssion.repository.ClassRepo;
import com.school.admisssion.repository.UserRepo;
import com.school.admisssion.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdmisssionApplicationTests {

	@Autowired
	private UserService userservice;

	@MockBean
	private ClassRepo courserepo;
	@MockBean
	private UserRepo userrepo;



	@Test
	public void saveUserTest() {
		User user = new User(3,"1","anirudh@gmail.com","Anirudh","9875698756","ani@1608","ani@1608");
		when(userrepo.save(user)).thenReturn(user);

	}

	@Test
     public void getAllUserTest()
	 {
		when(userrepo.findAll()).thenReturn(Stream.of(new User(2, "2","vikram@gmail.com","Vikram","8888898888","NVRoaGhoWWI=","NVRoaGhoWWI="), new User( 1,"1","lokesh@gmail.com", "Lokesh","8888899888", "dmlra2k=","dmlra2k="
		)).collect(Collectors.toList()));
		assertEquals(2, userservice.getAllUsers().size());
	 }

}
