package ermakus.service.impl;

import ermakus.bean.container.Page;
import ermakus.dao.BookDao;
import ermakus.dao.ex.BookDaoEx;
import ermakus.exception.ConnectionException;
import ermakus.exception.DatabaseException;
import ermakus.exception.ServiceException;
import ermakus.service.AuthorService;
import ermakus.utils.Tools;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

import static ermakus.utils.Constants.*;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookDaoEx bookDaoEx;

	@Override
	public void getBooks(HttpServletRequest request, ServletResponse response, String author) throws DatabaseException, ServiceException {
		try {
			var requestDispatcher = request.getServletContext().getRequestDispatcher(PREFIX + AUTHOR_PAGE + POSTFIX);
			var bookPaging = (Page) request.getSession().getAttribute(BOOK_PAGING_PARAMS);
			request.setAttribute(AUTHOR_BOOKS, bookDao.ex(bookDaoEx).getBooksByAuthor(author, bookPaging));
			request.setAttribute(AUTHOR_NAME, author);
			requestDispatcher.forward(request, response);
		} catch (SQLException | ConnectionException e) {
			throw new DatabaseException(DB_EXCEPTION);
		} catch (IOException | ServletException e) {
			throw new ServiceException(SERVICE_EXCEPTION);
		}
	}

	@Override
	public void paging(HttpServletRequest request, ServletResponse response, String author) throws DatabaseException, ServiceException {
		try {
			var requestDispatcher = request.getServletContext().getRequestDispatcher(PREFIX + AUTHOR_PAGE + POSTFIX);
			request.setAttribute(AUTHOR_BOOKS, bookDao.ex(bookDaoEx).getBooksByAuthor(author, Tools.updatePagingParams(request, BOOK_PAGING_PARAMS)));
			request.setAttribute(AUTHOR_NAME, author);
			requestDispatcher.forward(request, response);
		} catch (SQLException | ConnectionException e) {
			throw new DatabaseException(DB_EXCEPTION);
		} catch (IOException | ServletException e) {
			throw new ServiceException(SERVICE_EXCEPTION);
		}
	}
}