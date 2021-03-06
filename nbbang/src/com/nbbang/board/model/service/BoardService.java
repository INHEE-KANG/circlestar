package com.nbbang.board.model.service;

import static com.nbbang.common.temp.JDBCTemplate.close;
import static com.nbbang.common.temp.JDBCTemplate.commit;
import static com.nbbang.common.temp.JDBCTemplate.getConnection;
import static com.nbbang.common.temp.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nbbang.board.model.dao.BoardDao;
import com.nbbang.board.model.vo.Board;
import com.nbbang.board.model.vo.BoardFile;
import com.nbbang.board.model.vo.Card;
import com.nbbang.board.model.vo.Comment;
import com.nbbang.member.model.vo.LikeList;

public class BoardService {

	BoardDao dao = new BoardDao();

	public List<Card> boardList(int cPage, int numPerPage, String boardTitle) {
		Connection conn = getConnection();
		List<Card> boardList = dao.boardList(conn, cPage, numPerPage, boardTitle);
		close(conn);
		return boardList;
	}
	
	public List<Card> boardListSearch(int cPage, int numPerPage, String keyword, String category) {
		Connection conn = getConnection();
		List<Card> boardList = dao.boardListSearch(conn, cPage, numPerPage, keyword, category);
		close(conn);
		return boardList;
	}

	public Card boardPage(String boardId, boolean hasRead, int writerUsid) {
		Connection conn = getConnection();
		Card c = dao.boardPage(conn, boardId);
		c = dao.boardPageForProfile(conn, c, writerUsid);
		if (c != null && !hasRead) {
			int result = dao.updateReadCount(conn, Integer.parseInt(boardId));
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
		}
		close(conn);
		return c;
	}

	public int boardListCount(String boardTitle) {
		Connection conn = getConnection();
		int result = dao.boardListCount(conn, boardTitle);
		close(conn);
		return result;
	}
	
	public int boardListCountSearch(String category, String keyword) {
		Connection conn = getConnection();
		int result = dao.boardListCountSearch(conn, category, keyword);
		close(conn);
		return result;
	}

	public int boardInsert(Board b, BoardFile bf) {
		Connection conn = getConnection();
		int result = dao.boardInsert(conn, b);
		result += dao.boardInsert(conn, bf);
		result += dao.boardInsertTradeList(conn, b.getWriterUsid(), b.getWriterNickname());
		if(result > 2) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int boardModify(Board b, BoardFile bf, boolean hasFile) {
		Connection conn = getConnection();
		int result = dao.boardModifyBoard(conn, b);
		if(hasFile) {
		//파일 있으면
		result += dao.boardModifyFileDelete(conn, bf);
		result += dao.boardModifyFileInsert(conn, bf);
			//파일 삭제 후 업데이트
			if(result > 2) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}else {
			//없으면 Board만 실행
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}
		close(conn);
		return result;
	}
	
	public int boardLikeInsert(LikeList list) {
		Connection conn = getConnection();
		int result = dao.boardLikeInsert(conn, list);
		result += dao.boardLikeUpdate(conn, list);
		if(result > 1) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int boardLikeDelete(LikeList list) {
		Connection conn = getConnection();
		int result = dao.boardLikeDelete(conn, list);
		result += dao.boardLikeDeleteUpdate(conn, list);
		if(result > 1) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int commentInsert(Comment c) {
		Connection conn = getConnection();
		int result = dao.commentInsert(conn, c);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public List<Comment> commentList(int boardId) {
		Connection conn = getConnection();
		List<Comment> commentList = dao.commentList(conn, boardId);
		close(conn);
		return commentList;
		
	}
	
	public List<Card> mainViewList(String key){
		Connection conn = getConnection();
		List<Card> list = dao.mainViewList(conn, key);
		close(conn);
		return list;
	}
	
	public ArrayList<Integer> tradeUserList(int boardId) {
		Connection conn = getConnection();
		ArrayList<Integer> tradeUserList = dao.tradeUserList(conn, boardId);
		close(conn);
		return tradeUserList;
	}
	
	public int boardPay(int userUsid, int boardId, int productPrice, int writerUsid) {
		Connection conn = getConnection();
		int result = dao.boardPayMinusPoint(conn, userUsid, productPrice);
		result += dao.boardPayTradeList(conn, userUsid, boardId);
		result += dao.boardPayPlusPoint(conn,writerUsid, productPrice);
		if(result > 1) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public Card boardModifyCard(String boardId) {
		Connection conn = getConnection();
		Card c = dao.boardPage(conn, boardId);
		close(conn);
		return c;
	}
	
	public int boardDelete(int boardId) {
		Connection conn = getConnection();
		int result = dao.boardDelete(conn, boardId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int commentModify(String content, int comId) {
		Connection conn = getConnection();
		int result = dao.commentModify(conn, content, comId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int commentDelete(int comId) {
		Connection conn = getConnection();
		int result = dao.commentDelete(conn, comId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int requestCount(int boardId) {
		Connection conn = getConnection();
		int result = dao.requestCount(conn, boardId);
		close(conn);
		return result;
	}
	
	public List<Integer> paidUsers(int boardId) {
		Connection conn = getConnection();
		List<Integer> paidUsers = dao.paidUsers(conn, boardId);
		close(conn);
		return paidUsers;
	}
	
	public List<Integer> deliveryUsers(int boardId) {
		Connection conn = getConnection();
		List<Integer> deliveryList = dao.deliveryUsers(conn, boardId);
		close(conn);
		return deliveryList;
	}
	
	public int boardShipping(int boardId) {
		Connection conn = getConnection();
		int result = dao.boardShipping(conn, boardId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int boardDelivery(int boardId, int buyerUsid) {
		Connection conn = getConnection();
		int result = dao.boardDelivery(conn, boardId, buyerUsid);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int boardEnd(int boardId) {
		Connection conn = getConnection();
		int result = dao.boardEnd(conn, boardId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 삭제 추가 옵션들.
//	public int boardDeleteBoardfile(int boardId) {
//		Connection conn = getConnection();
//		int result = dao.boardDeleteBoardfile(conn, boardId);
//		if(result > 0) {
//			commit(conn);
//		}else {
//			rollback(conn);
//		}
//		close(conn);
//		return result;
//	}
//	
//	public int boardDeleteLikelist(int boardId) {
//		Connection conn = getConnection();
//		int result = dao.boardDeleteLikelist(conn, boardId);
//		if(result > 0) {
//			commit(conn);
//		}else {
//			rollback(conn);
//		}
//		close(conn);
//		return result;
//	}
//	
//	public int boardDeleteComment(int boardId) {
//		Connection conn = getConnection();
//		int result = dao.boardDeleteComment(conn, boardId);
//		if(result > 0) {
//			commit(conn);
//		}else {
//			rollback(conn);
//		}
//		close(conn);
//		return result;
//	}
//	
//	public int boardDeleteTradelist(int boardId) {
//		Connection conn = getConnection();
//		int result = dao.boardDeleteTradelist(conn, boardId);
//		if(result > 0) {
//			commit(conn);
//		}else {
//			rollback(conn);
//		}
//		close(conn);
//		return result;
//	}
}
