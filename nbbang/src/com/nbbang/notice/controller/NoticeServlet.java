package com.nbbang.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbbang.notice.model.service.NoticeService;
import com.nbbang.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/notice/noticeList")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no=Integer.parseInt(request.getParameter("no"));
		Notice n=new NoticeService().selectNoticeOne(no);
		String view="";
		if(n==null) {
			//������ �ڷᰡ ������
			//�ڷᰡ ���ٴ� �޼��� ���
			request.setAttribute("msg", "��ȸ�� ���������� ����.");
			request.setAttribute("loc", "/notice/notice.jsp");
			view="/views/common/msg.jsp";
		}else {
			//���õ� ������������ �̵�
			request.setAttribute("notice", n);
			view="/views/notice/noticeView.jsp";
		};
		
		
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
