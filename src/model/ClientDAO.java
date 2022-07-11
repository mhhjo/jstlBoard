package model;

import java.sql.SQLException;

import common.DBConnPool;

public class ClientDAO extends DBConnPool {
	private static ClientDAO clientDAO = new ClientDAO();
	
	public static ClientDAO getInstance() {
		return clientDAO;
	}
	public ClientDAO() {}
	
	// 회원가입
	public int register(ClientDTO clientDTO) {
		int result =0;
		String sql = "INSERT INTO client VALUES(?,?,?,?)";
		try {			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, clientDTO.getClientId()); // 1번째 ? 값에 clientId 값을 clientDTO로부터 호출하여 대입
			pstmt.setString(2, clientDTO.getClientPassword());
			pstmt.setString(3, clientDTO.getClientName());
			pstmt.setString(4, clientDTO.getClientEmail());
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 로그인
	public int loginCheck(ClientDTO clientDTO) {
		String sql = "SELECT clientPassword FROM client WHERE clientId =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, clientDTO.getClientId());
			rs = pstmt.executeQuery(); // 쿼리 실행 결과를 rs에 대입
			if(rs.next()) {
				if(rs.getString(1).equals(clientDTO.getClientPassword())) {
					return 1; // 로그인 성공
				}else {
					return 0; // 비밀번호 오류
				}
			}
			return -1; // 아이디 없음
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // 오류 발생
	}
	
	// 회원정보 호출
	public ClientDTO getClientInfo(String clientId) {
		ClientDTO resultDTO = null;
		try {
			String sql = "SELECT * FROM client WHERE clientId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, clientId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultDTO = new ClientDTO(rs.getString(1), null, rs.getString(3), rs.getString(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return resultDTO;
	}
	
	// 회원정보 수정
	public int updateClientInfo(ClientDTO clientDTO) {
		ClientDTO updateDTO = null;
		try {
			String sql = "UPDATE client SET clientPassword=?, clientName=?, clientEmail=? WHERE clientId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, clientDTO.getClientPassword());
			pstmt.setString(2, clientDTO.getClientName());
			pstmt.setString(3, clientDTO.getClientEmail());
			pstmt.setString(4, clientDTO.getClientId());
			
			System.out.println("UPDATE clientDTO : "+clientDTO);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정보수정 성공!");
				updateDTO = clientDTO;
				return 1;
			}else {
				System.out.println("정보수정 실패....");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	// 회원 아이디와 패스워드 일치 체크
	public int checkClient(String clientId, String clientPassword) {
		
		try {
			String sql = "SELECT COUNT(*) FROM client WHERE clientId=? AND clientPassword=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, clientId);
			pstmt.setString(2, clientPassword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return 1; // 일치 확인
			}else {
				return 0; // 불일치
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -2; // DB 오류
	}
	
	// 회원 탈퇴
	public void deleteClient(String id) {
		try {
			String sql = "DELETE FROM client WHERE clientId='"+id+"'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();	
		}
	}
}
