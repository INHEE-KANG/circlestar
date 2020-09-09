package com.nbbang.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.nbbang.notice.model.service.NoticeService;
import com.nbbang.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/notice/noticeWriteEnd")
public class NoticeWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!ServletFileUpload.isMultipartContent(request)) {

			request.setAttribute("msg", "�������� �ۼ�����");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		

		String path=getServletContext().getRealPath("/")+"upload/noticeFile";

		int maxSize=1024*1024*10;

		String encode="UTF-8";

		MultipartRequest mPr=new MultipartRequest(request,path,maxSize,encode,new DefaultFileRenamePolicy());
		

		String title=mPr.getParameter("title");
		String writer=mPr.getParameter("writer");
		String filePath=mPr.getFilesystemName("upfile"); 
		String content=mPr.getParameter("content");
		
		Notice n=new Notice(0,title,writer,content,null,filePath,false);
		int result=new NoticeService().insertNotice(n);
		
		String msg="";
		String loc="";
		
		if(result>0) {
			msg="��� ����";
			loc="/notice/noticeList";
		}else {
			msg="��� ����";
			loc="/notice/noticeWrite";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
