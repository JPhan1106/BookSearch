package coding.mentor.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coding.mentor.entity.Book;
import coding.mentor.entity.Category;
import coding.mentor.service.BookService;
import coding.mentor.service.CategoryService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryService categoryService = new CategoryService();
			List<Category> categoryList = categoryService.getAllCategories();
			
			BookService bookService = new BookService();
			
			String bookName = request.getParameter("bookName");
			List<Book> bookListBySearch = new ArrayList<Book>();
			
			String categoryId = request.getParameter("categoryId");
			List<Book> bookList = new ArrayList<Book>();
			
			
			if (categoryId == null && bookName == null) {
				bookList = bookService.getAllBooks();
			}
			if (bookName != null){
				bookListBySearch = bookService.getBooksByName(bookName);	
				}
			if (categoryId != null)	{	
					bookList = bookService.getBooksByCategoryId(Integer.parseInt(categoryId));
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("bookList", bookList);
			request.setAttribute("bookListBySearch", bookListBySearch);
			rd.forward(request, response);

		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
