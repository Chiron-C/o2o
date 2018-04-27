package com.util;

public class PathUtil {
	private static String seperator=System.getProperty("file.separator");//获取文件分隔符
	public static String getImgBasePath() {
		String os=System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")) {
			basePath="D:/img";
		}else {
			basePath="/home/img";
		}
		return basePath.replace("/", seperator);
	}
	//返回shop图片的相对子路径
	public static String getShopImagePath(long shopId) {
		String imagePath="/upload/item/shop/"+shopId+"/";
		return imagePath.replace("/", seperator);
	}
}
