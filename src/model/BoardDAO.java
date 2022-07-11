package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;

public class BoardDAO extends DBConnPool {
	// 전체 글 수
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM img_board";
		if(map.get("searchWord") != null) {
			query += " WHERE "+map.get("searchField")+""
					+ " LIKE '%"+map.get("searchWord")+"%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("글 수 호출 중 에러 발생!");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 페이징 처리
	public List<BoardDTO> selectListPage(Map<String, Object> map){
		List<BoardDTO> board = new ArrayList<BoardDTO>();
		String query = "SELECT * FROM ( "
				+ "		SELECT tb.*, rownum rNum FROM ( "
				+ "		SELECT * FROM img_board ";
		if(map.get("searchWord") != null) {
			query += " WHERE "+map.get("searchField")+""
					+ " LIKE '%"+map.get("searchWord")+"%' ";
		}
		query += "		ORDER BY no DESC "
				+ "		) tb "
				+ " ) "
				+ " WHERE rNum BETWEEN ? AND ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, map.get("start").toString());
			pstmt.setString(2, map.get("end").toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setNo(rs.getString("no"));
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setClientId(rs.getString("clientId"));
				boardDTO.setPostdate(rs.getDate("postDate"));
				boardDTO.setOfile(rs.getString("ofile"));
				boardDTO.setSfile(rs.getString("sfile"));
				boardDTO.setVisitcount(rs.getInt("visitcount"));
				
				board.add(boardDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	// 글 등록
	public int insertWrite(BoardDTO boardDTO) {
		int result = 0;
		try {
			String query = "INSERT INTO img_board(no, title, content, clientId, ofile, sfile, visitcount)"
					+ " VALUES(seq_imgboard.nextval, ?,?,?,?,?,0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, boardDTO.getTitle());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getClientId());
			pstmt.setString(4, boardDTO.getOfile());
			pstmt.setString(5, boardDTO.getSfile());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 글 상세 보기
	public BoardDTO selectView(String no) {
		BoardDTO boardDTO = new BoardDTO();
		String query = "SELECT ib.*, c.clientid "
				+ " FROM client c INNER JOIN img_board ib "
				+ " ON c.clientid = ib.clientid "
				+ " WHERE no = ? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardDTO.setNo(rs.getString("no"));
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setClientId(rs.getString("clientId"));
				boardDTO.setPostdate(rs.getDate("postdate"));
				boardDTO.setOfile(rs.getString("ofile"));
				boardDTO.setSfile(rs.getString("sfile"));
				boardDTO.setVisitcount(rs.getInt("visitcount"));	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return boardDTO;
	}
	
	// 조회수 증가
	public void updateVisitCount(String no) {
		String query = "UPDATE img_board SET "
				+ " visitcount = visitcount+1 "
				+ " WHERE no = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, no);
			pstmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
