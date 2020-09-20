package com.nbbang.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbbang.admin.model.service.AdminService;
import com.nbbang.board.model.service.BoardService;
import com.nbbang.board.model.vo.Board;
import com.nbbang.customer.model.vo.CustomerCenter;

/**
 * Servlet implementation class AdminCustomerListServlet
 */
@WebServlet("/admin/adminCustomerList")
public class AdminCustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCustomerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage = 1;
		}
		int numPerPage = 10;
		
		List<CustomerCenter> list = new AdminService().customerList(cPage, numPerPage);
		
		int totalData = new AdminService().customerListCount();
		int totalPage = (int)(Math.ceil((double)totalData/numPerPage));
		int pageBarSize = 5;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		String pageBar = "";
		if(pageNo == 1) {
			pageBar += "<li class='page-item disabled'><a class='page-link' href='#' tabindex='-1' aria-disabled='true'>이전</a></li>";
		}else {
			pageBar += "<li class='page-item'><a class='page-link' href='"+ request.getContextPath() + "/board/boList?cPage=" + (pageNo-1) + "'>이전</a></li>";
		}
		
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(cPage == pageNo) {
				pageBar += "<li class='page-item disabled'><a class='page-link' href='#' tabindex='-1' aria-disabled='true'>" + pageNo + "</a></li>";
			}else {
				pageBar+="<li class='page-item'><a class='page-link' href='" + request.getContextPath() + 
				"/board/boList?cPage="+pageNo+"')>"+pageNo+"</a></li>";
			}
			pageNo++;
		}
		
		if(pageNo > totalPage) {
			pageBar += "<li class='page-item disabled'><a class='page-link' href='#' tabindex='-1' aria-disabled='true'>다음</a></li>";
		}else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/board/boList?cPage=" + pageNo + "'>다음</a></li>";
		}
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/views/admin/customerList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}