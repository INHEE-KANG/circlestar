package com.nbbang.admin.model.service;

import static com.nbbang.common.temp.JDBCTemplate.close;
import static com.nbbang.common.temp.JDBCTemplate.commit;
import static com.nbbang.common.temp.JDBCTemplate.getConnection;
import static com.nbbang.common.temp.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.nbbang.admin.model.dao.AdminDao;
import com.nbbang.admin.model.vo.AdminBoard;
import com.nbbang.admin.model.vo.AdminMem;
import com.nbbang.member.model.vo.Report;
import com.nbbang.customer.model.vo.CustomerCenter;

public class AdminService {
	private AdminDao aa = new AdminDao();

	public List<CustomerCenter> customerList(int cPage, int numPerPage, String a) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<CustomerCenter> list = aa.customerList(conn, cPage, numPerPage, a);
		close(conn);
		return list;
	}

	public int customerListCount(String a) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = aa.customerListCount(conn, a);
		close(conn);
		return cnt;
	}

	public List<CustomerCenter> customerList(int cPage, int numPerPage, String a, String select, String search) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<CustomerCenter> list = null;
		if (select.equals("ALL")) {
			list = aa.customerList(conn, cPage, numPerPage, a, search);
		} else {
			list = aa.customerList(conn, cPage, numPerPage, a, select, search);
		}
		close(conn);
		return list;
	}

	public int customerListCount(String a, String select, String search) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = 0;
		if (select.equals("ALL")) {
			cnt = aa.customerListCount(conn, a, search);
		} else {
			cnt = aa.customerListCount(conn, a, select, search);
		}
		close(conn);
		return cnt;
	}

	public List<AdminMem> memberInfoList(int cPage, int numPerPage) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<AdminMem> list = aa.memberInfoList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int memberInfoListCount() {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = aa.memberInfoListCount(conn);
		close(conn);
		return cnt;
	}

	public List<AdminMem> memberInfoSearchList(int cPage, int numPerPage, String ra, String select, String search,
			String c) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<AdminMem> list = null;

		if (!select.equals("ALL")) {
			list = aa.memberInfoSearchList(conn, cPage, numPerPage, ra, select, search, c);
		} else {
			System.out.println("ALL 들어옴");
			list = aa.memberInfoAllSearchList(conn, cPage, numPerPage, ra, select, search, c);
		}
		close(conn);
		return list;
	}

	public int memberInfoSearchListCount(String ra, String select, String search, String c) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = 0;
		if (!select.equals("ALL")) {
			cnt = aa.memberInfoSearchListCount(conn, ra, select, search, c);
		} else {
			System.out.println("ALL 들어옴");
			cnt = aa.memberInfoAllSearchListCount(conn, ra, select, search, c);
		}
		close(conn);
		System.out.println(cnt);
		return cnt;
	}

	public List<AdminBoard> boardInfoList(int cPage, int numPerPage) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<AdminBoard> list = aa.boardInfoList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int boardInfoListCount() {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = aa.boardInfoListCount(conn);
		close(conn);
		return cnt;
	}

	public List<AdminBoard> boardInfoSearchList(int cPage, int numPerPage, String ra, String select, String search,
			String select2, String select3, String p) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<AdminBoard> list = null;
		if (!select.equals("ALL")) {
			list = aa.boardInfoSearchList(conn, cPage, numPerPage, ra, select, search, select2, select3, p);
		} else {
			list = aa.boardInfoAllSearchList(conn, cPage, numPerPage, ra, select, search, select2, select3, p);
		}
		close(conn);
		return list;
	}

	public int boardInfoSearchListCount(String ra, String select, String search, String select2, String select3,
			String p) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = 0;
		if (!select.equals("ALL")) {
			cnt = aa.boardInfoSearchListCount(conn, ra, select, search, select2, select3, p);
		} else {
			cnt = aa.boardInfoAllSearchListCount(conn, ra, select, search, select2, select3, p);
		}
		close(conn);
		return cnt;
	}

	public int byebye(int usid) {
		Connection conn = getConnection();
		int result = aa.byebye(conn, usid);
		if (result > 0) {
			commit(conn);
			result = 1;
		} else {
			rollback(conn);
			result = 0;
		}
		close(conn);
		return result;
	}

	public int iamSoSorry(int usid) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = aa.iamSoSorry(conn, usid);
		if (result > 0) {
			commit(conn);
			result = 3;
		} else {
			rollback(conn);
			result = 4;
		}
		close(conn);
		return result;
	}

	public List<Report> reportList(int cPage, int numPerPage, String a) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<Report> list = aa.reportList(conn, cPage, numPerPage, a);
		close(conn);
		return list;
	}

	public int reportListCount(String a) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = aa.reportListCount(conn, a);
		close(conn);
		return cnt;
	}

	public List<Report> reportSearchList(int cPage, int numPerPage, String a, String select, String search) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<Report> list = null;
		if (select.equals("ALL")) {
			list = aa.reportAllSearchList(conn, cPage, numPerPage, a, search);
		} else {
			list = aa.reportSearchList(conn, cPage, numPerPage, a, search,select);
		}
		close(conn);
		return list;
	}

	public int reportSearchListCount(String a, String select, String search) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int cnt = 0;
		if (select.equals("ALL")) {
			cnt = aa.reportAllSearchListCount(conn, a, search);
		} else {
			cnt = aa.reportSearchListCount(conn, a, search,select);
		}
		close(conn);
		return cnt;
	}

}
