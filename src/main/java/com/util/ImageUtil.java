package com.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFromat=new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r=new Random();
/*	public static void main(String [] args) {
		try {
			Thumbnails.of(new File(basePath+"/xiaohuangren.jpg"))
			.size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/mao.jpg")), 0.25f)
			.outputQuality(0.8f).toFile(basePath+"/new.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	//CommonsMultipartFile
	public static String generateThumbnail(MultipartFile thumbnail,String targetAddr) {
		String realFileName=getRandomFileName();//文件名
		String extension=getFileExtension(thumbnail);//文件类型
		makeDirPath(targetAddr);
		String relativeAddr=targetAddr+realFileName+extension;
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of((thumbnail).getInputStream()).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(PathUtil.getImgBasePath()+"\\watermark.jpg")),0.25f)
			.outputQuality(0.8f).toFile(dest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}
	/**
	 * 生成随机数，当前年月日时分秒+五位随机数 
	 * @return
	 */
	public static String getRandomFileName() {
		//获取随机五位数
		int rannum=r.nextInt(89999)+10000;
		String nowTimeStr=sDateFromat.format(new Date());
		return nowTimeStr+rannum;
	}
	/**
	 * 获取输入文件流的扩展名
	 * @return
	 */
	public static String getFileExtension(MultipartFile cFile) {
		//String originalFileName=cFile.getOriginalFilename();
		String originalFileName=cFile.getName();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}
	/**
	 * 创建目标路径涉及的路径
	 */
	public static void makeDirPath(String targetAddr) {
		String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
}
