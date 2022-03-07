package kodlamaio.northwind.Core.dataAccess;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.Core.utilities.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User getByEmail(String email);
	
	User getByEmailAndId(String email, int category);
	
	List<User> getByEmailOrId(String email, int category);
	
	List<User> getByEmailContains(String email);
	
	List<User> getByEmailStartsWith(String email);
}
;