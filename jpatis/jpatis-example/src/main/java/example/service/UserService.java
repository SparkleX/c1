package example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.jpatis.core.DumpUtil;
import com.next.jpatis.core.SqlConnection;

import example.domain.User;
import example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository repoUser;

	@Autowired
	SqlConnection sqlConnection;
	
	@PersistenceContext 
	EntityManager em;
	
	public void entityManagerExample() {
		User oUser = new User();
		oUser.setId(1);
		oUser.setName("Mike");
		em.persist(oUser);
		User user =em.find(User.class, 1);
		System.out.println(user);
		
	}
	public void sqlConnectionExample() {
		List<User> oList =sqlConnection.selectAll(User.class);
		DumpUtil.show(oList);
	}
	
	public void repositoryExample() {
		User oUser = new User();
		oUser.setId(1);
		oUser.setName("Mike");
		repoUser.insert(oUser);
		
		oUser = repoUser.findById(1).get();
		System.out.println(oUser.getName());
		
		List<User> oList = repoUser.getUserListById(1);
		DumpUtil.show(oList);
		
		oUser = repoUser.getUserById(1);
		System.out.println(oUser);
		
		oUser.setName("Tom");
		repoUser.updateById(oUser.getId(), oUser);
		
		oList = repoUser.findAll();
		DumpUtil.show(oList);
		
		repoUser.delete(oUser);
		oList = repoUser.findAll();
		DumpUtil.show(oList);
	}
	public Integer create(User entity) {
		repoUser.insert(entity);
		return entity.getId();
	}
	public User update(Integer id, User entity) {
		repoUser.updateById(id, entity);
		return repoUser.findById(id).get();
	}
	
	public List<User> search() {
		return repoUser.findAll();
	}
	public void delete(Integer id) {
		repoUser.deleteById(id);		
	}
	public User findById(Integer id) {
		return repoUser.findById(id).get();	
	}
}