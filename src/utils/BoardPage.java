package utils;

public class BoardPage {
	public static String pagingStr(int totalCount,int pageSize,int blockPage,int pageNum,String reqUrl) {
		String pagingStr="";
		//전체페이지수 계산. totalCount대신 totalPage를 parameter로 받아서 사용 추천
		int totalPages=(int)Math.ceil((double)totalCount/pageSize);
		//pageTemp는 구간번호. 예) 2번째 구간 :  6 ~ 10
		//이전블럭을 누르면 pageTemp의 첫번째값 - 1 => 이전구간의 마지막페이지로 이동
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1; //현재구간의 첫번째 번호 구하기.
		//첫번째 구간이 아니면. 첫페이지, 이전블록 출력
		//첫번째 구간에서는 pageTemp의 첫번째 값이 1이므로, pageTemp가 1이라면 첫번째 구간인 셈.
		if(pageTemp!=1) {
			pagingStr+="<a href='/MVC_Board/"+reqUrl+"?pageNum=1'>[&lt;&lt;]</a>"
					+ " <a href='/MVC_Board/"+reqUrl+"?pageNum="+(pageTemp-1)+"'>[&lt;]</a>";
		}
		//구간출력 예) 1 2 3 4 5 
		int blockCount=1;
		//pageTemp는 전체페이지까지만 반복. 마지막페이지는 전체페이지로 보정(변경)
		while(blockCount<=blockPage && pageTemp<=totalPages) {
			//pageTemp와 현재페이지가 같으면 링크걸지 않음
			if(pageTemp==pageNum) {
				pagingStr+="<span class='active'> "+pageTemp+" </span>";				
			}else {
				pagingStr+=" <a href='/MVC_Board/"+reqUrl+"?pageNum="+pageTemp+"'>"+pageTemp+"</a> ";	
			}
			pageTemp++;
			blockCount++;
		}
		//다음블록,마지막페이지 출력
		//pageTemp가 totalpages보다 작아야 다음블록, 마지막페이지 출력
		if(pageTemp<totalPages) {
			pagingStr+="<a href='/MVC_Board/"+reqUrl+"?pageNum="+pageTemp+"'>[&gt;]</a>"
					+ " <a href='/MVC_Board/"+reqUrl+"?pageNum="+totalPages+"'>[&gt;&gt;]</a>";
		}
		
		return pagingStr;
		
	}

}
