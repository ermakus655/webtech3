package ermakus.dao.ex;

import ermakus.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDaoEx extends JpaRepository<Book, Integer> {
}