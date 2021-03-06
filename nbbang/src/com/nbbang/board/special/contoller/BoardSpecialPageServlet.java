package com.nbbang.board.special.contoller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbbang.board.model.service.BoardService;
import com.nbbang.board.model.vo.Card;
import com.nbbang.board.special.model.service.BoardSpecialService;

/**
 * Servlet implementation class BoardPageServlet
 */
@WebServlet("/board/boardSpecialPage")
public class BoardSpecialPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardSpecialPageServlet() {
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
		String boardId = request.getParameter("boardId");
		int writerUsid = Integer.parseInt(request.getParameter("writerUsid"));
		String reply = request.getParameter("reply");
		Cookie[] cookies = request.getCookies();
		String boardHistory = "";
		boolean hasRead = false;
		if (cookies != null) {
			for (Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();

				if ("boardHistory".equals(name)) {
					boardHistory = value;
					if (value.contains("|" + boardId + "|")) {
						// 읽은 게시글
						hasRead = true;
						break;
					}
				}
			}
		}

		// 읽지 않은 게시물
		if (!hasRead) {
			Cookie c = new Cookie("boardHistory", boardHistory + "|" + boardId + "|");
			c.setMaxAge(-1);
			response.addCookie(c);
		}

		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

		System.out.println("boardId : "+boardId+", writerUsid : "+writerUsid);
		
		Card c = new BoardSpecialService().boardPage(boardId, hasRead, writerUsid);
		System.out.println("page : "+c.getCardBoard().getLimitTime());
		String dd = f.format(c.getCardBoard().getLimitTime());
		System.out.println(dd);
		if (dd.contains("오전")) {
			// System.out.println("t");
			dd = dd.replace("오전", "AM");
		} else {
			// System.out.println("f");
			dd = dd.replace("오후", "PM");
		}
		c.getCardBoard().setTime(dd);
		System.out.println(dd);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<Integer> tradeUserList = new BoardService().tradeUserList(Integer.parseInt(boardId));
		int likeCount = new BoardService().requestCount(Integer.parseInt(boardId));
		if (c == null) {
			request.setAttribute("msg", "문서를 불러오는데 실패했습니다");
			request.setAttribute("loc", "/boList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		} else {
			String temp = c.getCardBoard().getTradeArea();
			if (temp != null) {
				if (temp.length() > 8) {
					String newTemp = temp.substring(0, temp.indexOf(" ", temp.indexOf(" ") + 1));
					c.getCardBoard().setTradeArea(newTemp);
				}
			}
			if (tradeUserList != null)
				request.setAttribute("tradeUserList", tradeUserList);
			request.setAttribute("requestCount", likeCount);
			request.setAttribute("reply", reply);
			request.setAttribute("curCard", c);
			request.getRequestDispatcher("/views/board/boSpecialPage.jsp").forward(request, response);
		}
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
