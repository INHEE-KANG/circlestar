package com.nbbang.member.model.dao;

import static com.nbbang.common.temp.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.nbbang.board.model.vo.Board;
import com.nbbang.board.model.vo.BoardFile;
import com.nbbang.board.model.vo.Card;
import com.nbbang.common.temp.AESCrypto;
import com.nbbang.member.model.vo.Grade;
import com.nbbang.member.model.vo.LikeList;
import com.nbbang.member.model.vo.Member;
import com.nbbang.member.model.vo.Report;

public class MemberDao {
private Properties prop=new Properties();
	
	public MemberDao() {
		try {
			String filePath=MemberDao.class.getResource("/sql/member/member.properties").getPath();
			prop.load(new FileReader(filePath));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, String memberId, String memberPwd) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMember"));
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=inputData(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public Member findId(Connection conn, String memberName, String email) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("findId"));
			pstmt.setString(1, memberName);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=inputData(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public Member selectMember(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberOne"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=inputData(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;		
	}

	private Member inputData(ResultSet rs) {
		Member m=new Member();
		try {
			m.setUsid(rs.getInt("USID"));
			m.setMemberId(rs.getString("MEMBER_ID"));
			m.setMemberPwd(rs.getString("MEMBER_PWD"));
			m.setMemberName(rs.getString("MEMBER_NAME"));
			m.setNickname(rs.getString("NICKNAME"));
			m.setGender(rs.getString("GENDER"));
			m.setBirthday(rs.getDate("BIRTHDAY"));
			m.setPhone(rs.getString("PHONE"));
			m.setAddress(rs.getString("ADDRESS"));
			m.setEnrollDate(rs.getDate("ENROLL_DATE"));
			m.setPoint(rs.getInt("POINT"));
			m.setLeaveMem(rs.getBoolean("LEAVE_MEM"));
			m.setNbbangScore(rs.getInt("NBBANG_SCORE"));
			m.setMemberPicture(rs.getString("MEMBER_PICTURE"));
			m.setPwIsUuid(rs.getBoolean("PW_IS_UUID"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	public Member findPw(Connection conn, String memberName, String email, String memberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("findPw"));
			pstmt.setString(1, memberName);
			pstmt.setString(2, email);
			pstmt.setString(3, memberId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=inputData(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public Member myPage(Connection conn, int usid) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("myPage"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=inputData(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public Member nickDuplicate(Connection conn, String nick) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("nickDuplicate"));
			pstmt.setString(1, nick);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=inputData(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public int memberEnroll(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("memberEnroll"));
			pstmt.setString(1, m.getMemberPwd());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getNickname());
			pstmt.setString(4, m.getGender());
			pstmt.setDate(5, m.getBirthday());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getMemberId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int memberInsertGrade(Connection conn, String nickname) {
		PreparedStatement pstmt=null;
		int resultGrade=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("memberInsertGrade"));
			pstmt.setString(1, nickname);
			pstmt.setString(2, nickname);
			resultGrade=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return resultGrade;
	}
	
	public Member phoneDuplicate(Connection conn, String phone) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("phoneDuplicate"));
			pstmt.setString(1, phone);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=inputData(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public int updateFindPwMember(Connection conn, String encryptedUuid, int pwIsUuid, int usid) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updatePassword"));
			pstmt.setString(1, encryptedUuid);
			pstmt.setInt(2, pwIsUuid);
			pstmt.setInt(3, usid);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePhone(Connection conn, int usid, String phone) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updatePhone"));
			pstmt.setString(1, phone);
			pstmt.setInt(2, usid);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAddress(Connection conn, int usid, String address) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateAddress"));
			pstmt.setString(1, address);
			pstmt.setInt(2, usid);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Grade methodForGrade(Connection conn, int usid) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Grade g=new Grade();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("methodForGrade"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				g.setGradeUsid(rs.getInt("GRADE_USID"));
				g.setMaxRoomCount(rs.getInt("MAX_ROOM_COUNT"));
				g.setReliabilityScore(rs.getInt("RELIABILITY_SCORE"));
				g.setGradeLevel(rs.getInt("GRADE_LEVEL"));
				g.setGradeNickname(rs.getString("GRADE_NICKNAME"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return g;
	}

	public int myPageReport(Connection conn, int usid) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int reportCount=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("myPageReport"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				reportCount=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return reportCount;
	}

	public int modifyNick(Connection conn, int usid, String nick) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateNick"));
			pstmt.setString(1, nick);
			pstmt.setInt(2, usid);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int modifyPic(Connection conn, int usid, String fileName) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("modifyPic"));
			pstmt.setString(1, fileName);
			pstmt.setInt(2, usid);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public LikeList methodForLikelist(Connection conn, int usid) {
		LikeList ll=new LikeList();
		List<Integer> lbi=new LikeList().getLikeBoardId();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("methodForLikelist"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, usid);
			pstmt.setInt(3, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ll.setLikeUsid(usid);
				String result=rs.getString("LISTAGG(LIKE_BOARD_ID,',')WITHINGROUP(ORDERBYLIKE_BOARD_ID)");
				String[] splitResult=result.split(",");
				
				int[] intResult=new int[splitResult.length];
				for(int i=0; i<splitResult.length; i++) {
					intResult[i]=Integer.parseInt(splitResult[i]);
				}
				
				for(int i=0; i<intResult.length; i++) {
					lbi.add(intResult[i]);
				}
				
				ll.setLikeBoardId(lbi);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return ll;
	}

	public int getCrtPoint(Connection conn, int usid) {
		int crtPoint=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getCrtPoint"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				crtPoint=rs.getInt("POINT");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return crtPoint;
	}

	public void chargePoint(Connection conn, int usid, int newPoint) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("chargePoint"));
			pstmt.setInt(1, newPoint);
			pstmt.setInt(2, usid);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
	}

	public List<Card> boardList(Connection conn, int cPage, int numPerPage, int usid) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Card> list=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("boardList"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			list=new ArrayList<Card>();
			while(rs.next()) {
				Card c = new Card(new Board(), new BoardFile());
				c.getCardBoard().setBoardId(rs.getInt("BOARD_ID"));
				c.getCardBoard().setBoardTitle(rs.getString("BOARD_TITLE"));
				c.getCardBoard().setWriterUsid(rs.getInt("WRITER_USID"));
				c.getCardBoard().setHit(rs.getInt("HIT"));
				c.getCardBoard().setLikeCount(rs.getInt("LIKE_COUNT"));
				try {
					c.getCardBoard().setTradeArea(AESCrypto.decrypt(rs.getString("TRADE_AREA")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					c.getCardBoard().setTradeArea(rs.getString("TRADE_AREA"));
				} 
				c.getCardBoard().setProductPrice(rs.getInt("PRODUCT_PRICE"));
				c.getCardFile().setFileName(stringToArr(rs.getString("FILE_NAME")));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Card> waitlist(Connection conn, int cPage, int numPerPage, int usid) {
		List<Card> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("waitList"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, usid);
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rs=pstmt.executeQuery();
			list=new ArrayList<Card>();
			while(rs.next())
			{
				Card c = new Card(new Board(), new BoardFile());
				c.getCardBoard().setBoardId(rs.getInt("BOARD_ID"));
				c.getCardBoard().setBoardTitle(rs.getString("BOARD_TITLE"));
				c.getCardBoard().setWriterUsid(rs.getInt("WRITER_USID"));
				c.getCardBoard().setHit(rs.getInt("HIT"));
				c.getCardBoard().setLikeCount(rs.getInt("LIKE_COUNT"));
				try {
					c.getCardBoard().setTradeArea(AESCrypto.decrypt(rs.getString("TRADE_AREA")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					c.getCardBoard().setTradeArea(rs.getString("TRADE_AREA"));
				} 
				c.getCardBoard().setProductPrice(rs.getInt("PRODUCT_PRICE"));
				c.getCardFile().setFileName(stringToArr(rs.getString("FILE_NAME")));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
		return list;
	}

	private String[] stringToArr(String str) {
		if(str==null) {
			return new String[1];
		}else {
			if(str.contains(",")) {
				return str.split(",");
			}
			else {
				String[] arr = {str};
				return arr;
			}
		}
	}

	public int boardListCount(Connection conn, int usid) {
		int totalData=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("boardListCount"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalData=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalData;
	}

	public int waitCount(Connection conn, int usid) {
		int totalData=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("waitCount"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalData=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalData;
	}

	public List<Card> onGoingList(Connection conn, int cPage, int numPerPage, int usid) {
		List<Card> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("onGoingList"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			list=new ArrayList<Card>();
			while(rs.next()){
				Card c = new Card(new Board(), new BoardFile());
				c.getCardBoard().setBoardId(rs.getInt("BOARD_ID"));
				c.getCardBoard().setBoardTitle(rs.getString("BOARD_TITLE"));
				c.getCardBoard().setWriterUsid(rs.getInt("WRITER_USID"));
				c.getCardBoard().setHit(rs.getInt("HIT"));
				c.getCardBoard().setLikeCount(rs.getInt("LIKE_COUNT"));
				try {
					c.getCardBoard().setTradeArea(AESCrypto.decrypt(rs.getString("TRADE_AREA")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					c.getCardBoard().setTradeArea(rs.getString("TRADE_AREA"));
				} 
				c.getCardBoard().setProductPrice(rs.getInt("PRODUCT_PRICE"));
				c.getCardFile().setFileName(stringToArr(rs.getString("FILE_NAME")));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
		return list;
	}

	public int onGoingCount(Connection conn, int usid) {
		int totalData=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("onGoingCount"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalData=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalData;
	}

	public List<Card> pastList(Connection conn, int cPage, int numPerPage, int usid) {
		List<Card> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("pastList"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			list=new ArrayList<Card>();
			while(rs.next()){
				Card c = new Card(new Board(), new BoardFile());
				c.getCardBoard().setBoardId(rs.getInt("BOARD_ID"));
				c.getCardBoard().setBoardTitle(rs.getString("BOARD_TITLE"));
				c.getCardBoard().setWriterUsid(rs.getInt("WRITER_USID"));
				c.getCardBoard().setHit(rs.getInt("HIT"));
				c.getCardBoard().setLikeCount(rs.getInt("LIKE_COUNT"));
				try {
					c.getCardBoard().setTradeArea(AESCrypto.decrypt(rs.getString("TRADE_AREA")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					c.getCardBoard().setTradeArea(rs.getString("TRADE_AREA"));
				} 
				c.getCardBoard().setProductPrice(rs.getInt("PRODUCT_PRICE"));
				c.getCardFile().setFileName(stringToArr(rs.getString("FILE_NAME")));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
		return list;
	}

	public int pastCount(Connection conn, int usid) {
		int totalData=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("pastCount"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalData=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalData;
	}

	public List<Card> likeList(Connection conn, int cPage, int numPerPage, int usid) {
		List<Card> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("likeList"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			list=new ArrayList<Card>();
			while(rs.next()){
				Card c = new Card(new Board(), new BoardFile());
				c.getCardBoard().setBoardId(rs.getInt("BOARD_ID"));
				c.getCardBoard().setBoardTitle(rs.getString("BOARD_TITLE"));
				c.getCardBoard().setWriterUsid(rs.getInt("WRITER_USID"));
				c.getCardBoard().setHit(rs.getInt("HIT"));
				c.getCardBoard().setLikeCount(rs.getInt("LIKE_COUNT"));
				try {
					c.getCardBoard().setTradeArea(AESCrypto.decrypt(rs.getString("TRADE_AREA")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					c.getCardBoard().setTradeArea(rs.getString("TRADE_AREA"));
				} 
				c.getCardBoard().setProductPrice(rs.getInt("PRODUCT_PRICE"));
				c.getCardFile().setFileName(stringToArr(rs.getString("FILE_NAME")));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
		return list;
	}

	public int likeCount(Connection conn, int usid) {
		int totalData=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("likeCount"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalData=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalData;
	}

	public int byebye(Connection conn, int usid, String password) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("byebye"));
			pstmt.setInt(1, usid);
			pstmt.setString(2, password);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Board boardData(Connection conn, int boardId) {
		Board b=new Board();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("boardData"));
			pstmt.setInt(1, boardId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b.setBoardId(rs.getInt("BOARD_ID"));
				b.setWriterUsid(rs.getInt("WRITER_USID"));
				b.setBoardTitle(rs.getString("BOARD_TITLE"));
				b.setWriterNickname(rs.getString("WRITER_NICKNAME"));
				b.setContent(rs.getString("CONTENT"));
				b.setEnrollDate(rs.getDate("ENROLL_DATE"));
				b.setHit(rs.getInt("HIT"));
				b.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				b.setTradeArea(rs.getString("TRADE_AREA"));
				b.setMaxMems(rs.getInt("MAX_MEMS"));
				b.setLimitTime(rs.getDate("LIMIT_TIME"));
				b.setTradeStage(rs.getInt("TRADE_STAGE"));
				b.setLikeCount(rs.getInt("LIKE_COUNT"));
				int popluarInt=Integer.parseInt(rs.getString("POPULAR_BOARD"));
				if(popluarInt==0) {
					b.setPopularBoard(false);					
				}else {
					b.setPopularBoard(true);
				}
				b.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				b.setOwnStatus(rs.getString("OWN_STATUS"));
				b.setTradeKind(rs.getString("TRADE_KIND"));
				b.setProductCategory(rs.getString("PRODUCT_URL"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}

	public int sendReport(Connection conn, Report r) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("sendReport"));
			pstmt.setInt(1, r.getReportUserUsid());
			pstmt.setInt(2,r.getReportBoardId());
			pstmt.setInt(3, r.getReportTargetUsid());
			pstmt.setString(4, r.getReportType());
			pstmt.setString(5, r.getReportTitle());
			pstmt.setString(6, r.getReportContent());
			pstmt.setString(7, r.getReportFile());
			pstmt.setString(8, r.getReportTargetNickname());
			pstmt.setString(9, r.getReportUserNickname());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Report> getReportList(Connection conn, int cPage, int numPerPage, int usid) {
		List<Report> rlist=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getReportList"));
			pstmt.setInt(1, usid);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			rlist=new ArrayList<Report>();
			while(rs.next()) {
				Report r=new Report();
				r.setReportId(rs.getInt("REPORT_ID"));
				r.setReportUserUsid(rs.getInt("REPORT_USER_USID"));
				r.setReportBoardId(rs.getInt("REPORT_BOARD_ID"));
				r.setReportTargetUsid(rs.getInt("REPORT_TARGET_USID"));
				r.setReportType(rs.getString("REPORT_TYPE"));
				r.setReportTitle(rs.getString("REPORT_TITLE"));
				r.setReportContent(rs.getString("REPORT_CONTENT"));
				r.setReportDate(rs.getDate("REPORT_DATE"));
				r.setReportFile(rs.getString("REPORT_FILE"));
				r.setReportIscheck(rs.getBoolean("REPORT_ISCHECK"));
				r.setReportTargetNickname(rs.getString("REPORT_TARGET_NICKNAME"));
				r.setReportUserNickname(rs.getString("REPORT_USER_NICKNAME"));
				r.setReportAnswer(rs.getString("REPORT_ANSWER"));
				r.setReportIswarning(rs.getBoolean("REPORT_ISWARNING"));
				rlist.add(r);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return rlist;
	}

	public int reportListCount(Connection conn, int usid) {
		int totalData=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("reportListCount"));
			pstmt.setInt(1, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalData=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalData;
	}

	public List<Report> getReportListAll(Connection conn, int cPage, int numPerPage) {
		List<Report> rlist=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getReportListAll"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			rlist=new ArrayList<Report>();
			while(rs.next()) {
				Report r=new Report();
				r.setReportId(rs.getInt("REPORT_ID"));
				r.setReportUserUsid(rs.getInt("REPORT_USER_USID"));
				r.setReportBoardId(rs.getInt("REPORT_BOARD_ID"));
				r.setReportTargetUsid(rs.getInt("REPORT_TARGET_USID"));
				r.setReportType(rs.getString("REPORT_TYPE"));
				r.setReportTitle(rs.getString("REPORT_TITLE"));
				r.setReportContent(rs.getString("REPORT_CONTENT"));
				r.setReportDate(rs.getDate("REPORT_DATE"));
				r.setReportFile(rs.getString("REPORT_FILE"));
				r.setReportIscheck(rs.getBoolean("REPORT_ISCHECK"));
				r.setReportTargetNickname(rs.getString("REPORT_TARGET_NICKNAME"));
				r.setReportUserNickname(rs.getString("REPORT_USER_NICKNAME"));
				r.setReportAnswer(rs.getString("REPORT_ANSWER"));
				r.setReportIswarning(rs.getBoolean("REPORT_ISWARNING"));
				rlist.add(r);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return rlist;
	}

	public int reportListAllCount(Connection conn) {
		int totalData=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("reportListAllCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalData=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalData;
	}

	public Report reportDetail(Connection conn, int usid, int reportId) {
		Report r=new Report();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("reportDetail"));
			pstmt.setInt(1, reportId);
			pstmt.setInt(2, usid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				r.setReportId(rs.getInt("REPORT_ID"));
				r.setReportUserUsid(rs.getInt("REPORT_USER_USID"));
				r.setReportBoardId(rs.getInt("REPORT_BOARD_ID"));
				r.setReportTargetUsid(rs.getInt("REPORT_TARGET_USID"));
				r.setReportType(rs.getString("REPORT_TYPE"));
				r.setReportTitle(rs.getString("REPORT_TITLE"));
				r.setReportContent(rs.getString("REPORT_CONTENT"));
				r.setReportDate(rs.getDate("REPORT_DATE"));
				r.setReportFile(rs.getString("REPORT_FILE"));
				r.setReportIscheck(rs.getBoolean("REPORT_ISCHECK"));
				r.setReportTargetNickname(rs.getString("REPORT_TARGET_NICKNAME"));
				r.setReportUserNickname(rs.getString("REPORT_USER_NICKNAME"));
				r.setReportAnswer(rs.getString("REPORT_ANSWER"));
				r.setReportIswarning(rs.getBoolean("REPORT_ISWARNING"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return r;
	}

	public Report reportDetail(Connection conn, int reportId) {
		Report r=new Report();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("reportDetailAll"));
			pstmt.setInt(1, reportId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				r.setReportId(rs.getInt("REPORT_ID"));
				r.setReportUserUsid(rs.getInt("REPORT_USER_USID"));
				r.setReportBoardId(rs.getInt("REPORT_BOARD_ID"));
				r.setReportTargetUsid(rs.getInt("REPORT_TARGET_USID"));
				r.setReportType(rs.getString("REPORT_TYPE"));
				r.setReportTitle(rs.getString("REPORT_TITLE"));
				r.setReportContent(rs.getString("REPORT_CONTENT"));
				r.setReportDate(rs.getDate("REPORT_DATE"));
				r.setReportFile(rs.getString("REPORT_FILE"));
				r.setReportIscheck(rs.getBoolean("REPORT_ISCHECK"));
				r.setReportTargetNickname(rs.getString("REPORT_TARGET_NICKNAME"));
				r.setReportUserNickname(rs.getString("REPORT_USER_NICKNAME"));
				r.setReportAnswer(rs.getString("REPORT_ANSWER"));
				r.setReportIswarning(rs.getBoolean("REPORT_ISWARNING"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return r;
	}

	public int reportAnswer(Connection conn, String reportAnswer, int rboardId) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("reportAnswer"));
			pstmt.setString(1, reportAnswer);
			pstmt.setInt(2, rboardId);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int reportWarning(Connection conn, int tusid) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("reportWarning"));
			pstmt.setInt(1, tusid);
			result=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}



}
