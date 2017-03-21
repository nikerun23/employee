package com.test.sts;

public class FileRenamePolicy {
	
	public static String rename(String orginalFileName) {
		String result = "";
		
		//랜덤하게 이름을 생성해주는 유틸 클래스
		//예를 들어, 18c076fc-d3f3-4189-8fc9-d650df376330 형태의 문자열 생성됨.
		String uniqueFileName = java.util.UUID.randomUUID().toString();

        int dot = orginalFileName.lastIndexOf(".");
        String ext = orginalFileName.substring(dot); // includes "."
        result = uniqueFileName + ext;
        
		return result;
	}

}
