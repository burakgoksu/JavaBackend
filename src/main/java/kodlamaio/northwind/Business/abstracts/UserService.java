package kodlamaio.northwind.Business.abstracts;

import java.util.List;


import kodlamaio.northwind.Core.utilities.entities.User;
import kodlamaio.northwind.Core.utilities.results.DataResult;
import kodlamaio.northwind.Core.utilities.results.Result;

public interface UserService {
	Result add(User user);
	
	Result delete(User user);
	
	Result update(User user);
	
	DataResult<List<User>> getall();
	
	DataResult<List<User>> getAllSorted();
	
	DataResult<List<User>> getAll(int pageNo, int pageSize);
	
	DataResult<User> getByUserEmailAndUserId(String email, int id);
	
	DataResult<List<User>> getByUserEmailOrUserId(String email, int id);
	
	DataResult<List<User>> getByEmailStartsWith(String email);

	DataResult<List<User>> getByEmailContains(String email);

	DataResult<User> getByEmail(String email);
	
	
}
