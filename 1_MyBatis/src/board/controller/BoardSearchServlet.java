package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.model.exception.BoardException;
import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.SearchCondition;
import common.Pagination;

@WebServlet("/search.bo")
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String condition = request.getParameter("searchCondition");
		String value = request.getParameter("searchValue");
		
		SearchCondition sc = new SearchCondition();
		if(condition.equals("writer")) {
			sc.setWriter(value);
		} else if(condition.equals("title")) {
			sc.setTitle(value);
		} else if(condition.equals("content")) {
			sc.setContent(value);
		}
		
		BoardService service = new BoardService();
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		
		try {
			int listCount = service.getSearchResultListCount(sc);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			
			ArrayList<Board> list = service.selectSearchResultList(sc, pi);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
			request.setAttribute("searchCondition", condition);
			request.setAttribute("searchValue", value);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardList.jsp").forward(request, response);			
		} catch (BoardException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
