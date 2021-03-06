package com.nbbang.customer.model.dao;

import static com.nbbang.common.temp.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.nbbang.board.model.dao.BoardDao;
import com.nbbang.customer.model.vo.CustomerCenter;
import com.nbbang.customer.model.vo.CustomerFile;

public class CustomerDAO {

	private Properties prop = new Properties();

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
		String filePath = CustomerDAO.class.getResource("/sql/customer/customer.properties").getPath();
		try {
			prop.load(new FileReader(filePath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<CustomerCenter> qnAList(Connection conn, int cPage, int numPerPage, String nick) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomerCenter> list = new ArrayList();

		try {
			pstmt = conn.prepareStatement(prop.getProperty("qnAList"));
			pstmt.setString(1, nick);
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomerCenter cc = new CustomerCenter();
				cc.setCsId(rs.getInt("cs_id"));
				cc.setCsWriterUsid(rs.getInt("cs_writer_usid"));
				cc.setCsType(rs.getString("cs_type"));
				cc.setCsTitle(rs.getString("cs_title"));
				cc.setCsContent(rs.getString("cs_content"));
				cc.setCsDate(rs.getDate("cs_date"));

				cc.setCsIscheck(rs.getBoolean("cs_ischeck"));
				cc.setCsNickname(rs.getString("cs_nickname"));
				cc.setCsAnswer(rs.getString("cs_answer"));
				list.add(cc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;

	}

	public int qnACount(Connection conn, String nick) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("qnaCount"));
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int insertQna(Connection conn, CustomerCenter c) {
		System.out.println("insertQna 실행됨");
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertQnA"));
			pstmt.setInt(1, c.getCsWriterUsid());
			pstmt.setString(2, c.getCsType());
			pstmt.setString(3, c.getCsTitle());
			pstmt.setString(4, c.getCsContent());
			pstmt.setString(5, c.getCsNickname());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int viewDetailRead(Connection conn, CustomerCenter c) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("viewRead"));

			pstmt.setString(1, c.getCsContent());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertQna(Connection conn, CustomerFile cf) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertFile"));
			pstmt.setString(1, cf.getCsFileName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAnswer(Connection conn, CustomerCenter c) {
		System.out.println("c in dao before update: " + c);
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertAnswer"));

			pstmt.setString(1, c.getCsAnswer());
			pstmt.setInt(2,c.getCsId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public CustomerCenter viewDetailRead(Connection conn, int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerCenter cc = null;
		
		System.out.println("num in dao: " + num);
		try {
			pstmt = conn.prepareStatement(prop.getProperty("viewRead"));

			pstmt.setInt(1,num);

			rs = pstmt.executeQuery();
			if(rs.next()) {
			
				cc = new CustomerCenter();
				cc.setCsId(rs.getInt("cs_id"));
				cc.setCsWriterUsid(rs.getInt("cs_writer_usid"));
				cc.setCsType(rs.getString("cs_type"));
				cc.setCsTitle(rs.getString("cs_title"));
				cc.setCsContent(rs.getString("cs_content"));
				cc.setCsDate(rs.getDate("cs_date"));
				cc.setCsIscheck(rs.getBoolean("cs_ischeck"));
				cc.setCsNickname(rs.getString("cs_nickname"));
				cc.setCsAnswer(rs.getString("cs_answer"));
								
				List<CustomerFile> files=new ArrayList();
				CustomerFile cf = new CustomerFile();
				cf.setCsFileId(rs.getInt(1));
				cf.setCsFileName(rs.getString(2));//첫번째파일
				files.add(cf);
				while(rs.next()) {//기타파일
					CustomerFile cfEx = new CustomerFile();
					cf.setCsFileId(rs.getInt(1));
					cf.setCsFileName(rs.getString(2));
					files.add(cfEx);
				}
				cc.setCf(files);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return cc;
	}

}
