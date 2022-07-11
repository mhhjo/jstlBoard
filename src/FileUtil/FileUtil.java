package FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {
	//파일업로드
	public static MultipartRequest uploadFile(HttpServletRequest request,String saveDirectory,int maxPostSize) {
		try {
			return new MultipartRequest(request,saveDirectory,maxPostSize,"UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//파일다운로드
	public static void download(HttpServletRequest request,HttpServletResponse response,String directory,String sfileName,String ofileName) {
		String saveDirectory=request.getServletContext().getRealPath(directory);
		try{
			File file=new File(saveDirectory,sfileName);
			InputStream inStream=new FileInputStream(file);
			
			String client=request.getHeader("User-Agent");
			if(client.indexOf("WOW64")==-1){
				ofileName=new String(ofileName.getBytes("UTF-8"),"ISO-8859-1");
			}else{
				ofileName=new String(ofileName.getBytes("KSC5601"),"ISO-8859-1");
			}
			
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\""+ofileName+"\"");
			response.setHeader("Content-Length", ""+file.length());			
			
			OutputStream outStream=response.getOutputStream();
			
			byte[] b=new byte[1024*1024];//버퍼생성
			int readBuffer=0;
			while((readBuffer=inStream.read(b))>0){
				outStream.write(b,0,readBuffer);
			}
			inStream.close();
			outStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	// 지정한 위치 파일 삭제
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
		if(file.exists()) {
			file.delete();
		}
	}
}
