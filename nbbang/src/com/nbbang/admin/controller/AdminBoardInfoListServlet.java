package com.nbbang.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbbang.admin.model.service.AdminService;
import com.nbbang.admin.model.vo.AdminBoard;
import com.nbbang.admin.model.vo.AdminMem;
import com.nbbang.common.temp.AESCrypto;

/**
 * Servlet implementation class AdminMemberInfoServlet
 */
@WebServlet("/admin/boardInfoList")
public class AdminBoardInfoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminBoardInfoListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cPage;
		List<AdminBoard> list = null;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		int numPerPage = 30;

		list = new AdminService().boardInfoList(cPage, numPerPage);
	
		
		for (AdminBoard a : list) {
			String tradearea;
			try {
				tradearea = AESCrypto.decrypt(a.getBo().getTradeArea());
			} catch (Exception e) {
				tradearea = a.getBo().getTradeArea();
			}
			a.getBo().setTradeArea(tradearea);
		}

		int totalData = new AdminService().boardInfoListCount();
		System.out.println("totalData : "+totalData);
		int totalPage = (int) (Math.ceil((double) totalData / numPerPage));
		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		String pageBar = "";
		if (pageNo == 1) {
			pageBar += "<li class='page-item disabled'><a class='page-link' href='#' tabindex='-1' aria-disabled='true'>이전</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath()
					+ "/admin/boardInfoList?cPage=" + (pageNo - 1) + " '>이전</a></li>";
		}

		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (cPage == pageNo) {
				pageBar += "<li class='page-item disabled'><a class='page-link' href='#' tabindex='-1' aria-disabled='true'>"
						+ pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath()
						+ "/admin/boardInfoList?cPage=" + pageNo + "')>" + pageNo + "</a></li>";
			}
			pageNo++;
		}

		if (pageNo > totalPage) {
			pageBar += "<li class='page-item disabled'><a class='page-link' href='#' tabindex='-1' aria-disabled='true'>다음</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath()
					+ "/admin/boardInfoList?cPage=" + pageNo + "'>다음</a></li>";
		}
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/admin/boardInfoList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
