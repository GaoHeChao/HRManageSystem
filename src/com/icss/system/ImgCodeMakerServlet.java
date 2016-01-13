package com.icss.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ImgCodeMakerServlet")
public class ImgCodeMakerServlet extends HttpServlet {

	/**
	 * 生成验证码图片
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        //设置有效期
        response.setDateHeader("Expires", 0);
 
        //设置图片的宽度和高度
        int width = 60, height = 20;
 
        //创建一个图像对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
        //获取画图对象（图片上的画笔）
        Graphics g = image.createGraphics();
 
        //创建随机对象
        Random random = new Random();
 
        //用随机颜色填充图像背景
        g.setColor(new Color(255, 255, 255));
 
        //图像形状
        g.fillRect(0, 0, width, height);
 
        //设置字体
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
 
        //随机数字符串
        String sRand = "";
 
        for (int i = 0; i < 4; i++) {
            //生成四个数字字符
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            //生成随机颜色
            g.setColor(new Color(100 + random.nextInt(155), random.nextInt(100), 100 + random.nextInt(155)));
            //将随机数字画在图像上 g.drawString(数字， x 轴, y 轴）
            g.drawString(rand, (12 + random.nextInt(2)) * i + 6, 15+random.nextInt(5));
        }
 
        //生成干扰线
        for (int k = 0; k < 48; k++) {
            g.setColor(getRandomColor(0, 255));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(6);
            int y1 = random.nextInt(6);
            g.drawLine(x, y, x + x1, y + y1);
        }
         
        //生成干扰像素点
        for (int i = 0; i < 100; i++) {
            g.setColor(getRandomColor(50, 180));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 1, 1); //画椭圆g.drawOval(x,y圆心坐标,短半轴,长半轴)
        }
         
        //将生成的随机数字写入Session会话
        request.getSession().setAttribute("randcode", sRand);
 
        //使图像生效
        g.dispose();
 
        //输出图像到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
 
    }
 
    /**
     * 产生一个随机颜色
     *
     * @param fc 颜色分量最小值
     * @param bc 颜色分量最大值
     * @return
     */
    public Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
 
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
 
        return new Color(r, g, b);
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
