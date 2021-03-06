package com.nbbang.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbbang.customer.model.service.CustomerService;
import com.nbbang.customer.model.vo.CustomerCenter;
import com.nbbang.member.model.service.MemberService;
import com.nbbang.member.model.vo.Member;

/**
 * Servlet implementation class CustomerQnAServlet
 */
@WebServlet("/customer/customerQnA")
public class CustomerQnAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerQnAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nick=request.getParameter("nick");
		
//		int num = Integer.parseInt(request.getParameter("num"));
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		int numPerPage=7;
		
		
		List<CustomerCenter> list=new CustomerService().qnAList(cPage,numPerPage,nick);
		for(CustomerCenter c : list) {
			System.out.println("c : "+ c);
		}
		int totalData=new CustomerService().qnACount(nick);
		int totalPage=(int)(Math.ceil((double)totalData/numPerPage));
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>&laquo;</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/customer/customerQnA?cPage="+(pageNo-1)+"&nick="+nick+"'>&laquo;</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/customer/customerQnA?cPage="+(pageNo)+"&nick="+nick+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>&raquo;</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/customer/customerQnA?cPage="+(pageNo)+"&nick="+nick+"'>&raquo;</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/customer/customerQnA.jsp").forward(request,response);
		
//		request.getRequestDispatcher("/views/customer/customerRead.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
