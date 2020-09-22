package com.nbbang.board.model.dao;

import static com.nbbang.common.temp.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.nbbang.board.model.vo.Board;
import com.nbbang.board.model.vo.BoardFile;

public class BoardDao {
	
	Properties prop = new Properties();
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
		String filePath = BoardDao.class.getResource("/sql/board/board.properties").getPath();
		try {
			prop.load(new FileReader(filePath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Board> boardList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("boardList");
		List<Board> list = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rs.next()) {
				Board b = new Board();
				b.setBoardId(rs.getInt("BOARD_ID"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setWriterUsid(rs.getInt("WRITER_USID"));
				b.setHit(rs.getInt("HIT"));
				b.setLikeCount(rs.getInt("LIKE_COUNT"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public Board boardPage(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("boardPage");
		Board b = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b = new Board();
				b.setBoardId(rs.getInt("BOARD_ID"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setWriterUsid(rs.getInt("WRITER_USID"));
				b.setWriterNickname(rs.getString("WRITER_NICKNAME"));
				b.setContent(rs.getString("CONTENT"));
				b.setEnrollDate(rs.getDate("ENROLL_DATE"));
				b.setHit(rs.getInt("HIT"));
				b.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				b.setTradeArea(rs.getString("TRADE_AREA"));
				b.setMaxMems(rs.getInt("MAX_MEMS"));
				b.setLimitTime(rs.getDate("LIMIT_TIME"));
				b.setTradeStage(rs.getInt("LIKE_COUNT"));
				b.setPopularBoard(rs.getBoolean("POPULAR_BOARD"));
				b.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				b.setOwnStatus(rs.getString("TRADE_KIND"));
				b.setProductUrl(rs.getString("PRODUCT_URL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public int boardListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("boardListCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		} 
		return result;
	}
	
	public int boardInsert(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("boardInsert"));
			pstmt.setInt(1, b.getWriterUsid());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getWriterNickname());
			pstmt.setString(4, b.getContent());
			pstmt.setString(5, b.getProductCategory());
			pstmt.setString(6, b.getTradeArea());
			pstmt.setInt(7, b.getMaxMems());
			pstmt.setInt(8, b.getProductPrice());
			pstmt.setString(9, b.getOwnStatus());
			pstmt.setString(10, b.getTradeKind());
			pstmt.setString(11, b.getProductUrl());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int boardInsert(Connection conn, BoardFile bf) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("boardFileInsert"));
			for(String s : bf.getFileName()) {
				pstmt.setString(1, s);
				result += pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateReadCount(Connection conn, int boardId) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateReadCount"));
			pstmt.setInt(1, boardId);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}return result;
	}
	
	private String[] stringToArr(String str) {
		if(str==null) {
			return new String[1];
		}else {
			if(str.contains(";")) {
				return str.split(";");
			}
			else {
				String[] arr = {str};
				return arr;
			}
		}
	}
}
