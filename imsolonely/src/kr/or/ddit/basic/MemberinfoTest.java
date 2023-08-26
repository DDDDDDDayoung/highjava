package kr.or.ddit.basic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class MemberinfoTest {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	private Scanner sc =  new Scanner(System.in);
	
	
	public void diplayManu() {
		System.out.println();
		System.out.println("----------------------"); 
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
		
	}
	
	public void start() {
		int choice;
		
		do {
			diplayManu();
			
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				insertMember();
				break;
			case 2:
				deleteMember();
				break;
			case 3:
				updateMember();
				break;
			case 4:
				selectAll();
				break;
			case 5:
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("잘못된 선택입니다. 다시입력하세요.");
			}
		}while(choice != 5);
	}



	private void selectAll() {
		
		try {
			conn = JDBCUtil.getConnection();			
			stmt = conn.createStatement();
			
			String sql = "select * from MEMBERTEST ";
			rs = stmt.executeQuery(sql);
			
			System.out.println("----------------------");
			System.out.println("ID\t생성일\t\t\t이 름\t전화번호\t\t주 소");
			System.out.println("----------------------");
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				Date regDt = rs.getTimestamp("reg_dt");
				
				System.out.println(memId + "\t" + regDt 
								+ "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			
			System.out.println("----------------------");
			System.out.println("       출력 완료");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		
	}

	private void updateMember() {
		boolean isEixt = false;
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원 아이디를 입력해주세요.");
			System.out.println("회원 ID >>");
			
			memId=sc.next();
			
			isEixt = checkMember(memId);
			
			if(!isEixt) {
				System.out.println("존재하지 않는 회원ID 입니다.");
			}
		}while(!isEixt);
		
		System.out.println("회원 이름>> ");
		String memName = sc.next();
		
		System.out.println("회원 전화번호>> ");
		String memTel = sc.next();
		
		System.out.println("회원 주소>> ");
		String memAddr = sc.next();
		
		
		
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update member set mem_name=?, mem_tel=?, mem_addr=? where mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	
		
	}
	
	public boolean checkMember(String memId) {
		boolean isExist = false;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from MEMBERTEST where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt=rs.getInt("CNT");
			}
			
			if(cnt>0) {
				isExist=true;
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.getConnection();
		}
		
		return isExist;
		
		
	}

	private void deleteMember() {
		boolean isExist = false;
		String memId = "";
		
		
		do {
			System.out.println();
			System.out.println("삭제할 회원 아이디를 입력해주세요.");
			System.out.println("회원 ID >> ");
			memId = sc.next();
			
			isExist = checkMember(memId);
			
			if(!isExist) {
				System.out.println("존재하지 않는 회원 ID입니다.");
			}
			
		}while(!isExist);
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from membertest where mem_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("회원 정보가 삭제되었습니다.");
			}else {
				System.out.println("회원 정보 삭제에 실패하였습니다.");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	
	}

	private void insertMember() {
		boolean isEixt = false;
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("등록할 회원 아이디를 입력해주세요.");
			System.out.println("회원 ID >>");
			
			memId=sc.next();
			
			isEixt = checkMember(memId);
			
			if(isEixt) {
				System.out.println("이미 존재하는 ID 입니다.");
			}
		}while(isEixt);
		
		System.out.println("회원 이름>> ");
		String memName = sc.next();
		
		System.out.println("회원 전화번호>> ");
		String memTel = sc.next();
		
		sc.nextLine();
		
		System.out.println("회원 주소>> ");
		String memAddr = sc.next();
		
		System.out.println(memId);
		System.out.println(memTel);
		System.out.println(memName);
		System.out.println(memAddr);
		
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO membertest (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR, REG_DT) "
					+ " VALUES (?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("회원 등록 성공");
			}else {
				System.out.println("회원 등록 실패");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	
		
	}

	public static void main(String[] args) {
		MemberinfoTest mt = new MemberinfoTest();
		mt.start();

	}

}
