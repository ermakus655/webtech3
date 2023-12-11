package ermakus.dao.ex;

import ermakus.bean.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDaoEx extends JpaRepository<Author, Integer> {
}