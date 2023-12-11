package ermakus.dao.ex;

import ermakus.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoEx extends JpaRepository<User, Integer> {
}