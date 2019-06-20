package example.repository;

import java.util.List;

import com.next.jpatis.spring.JpatisRepository;
import com.next.jpatis.spring.SQL;

import example.domain.User;

public interface UserRepository extends JpatisRepository<User,Integer>{
	@SQL("select * from User where id=?")
	List<User> getUserListById(Integer id);

	@SQL("select * from User where id=?")
	User getUserById(Integer id);

}
