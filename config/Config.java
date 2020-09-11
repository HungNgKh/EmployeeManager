/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import gui.widgets.TabButtonPanel;
import gui.main.AppWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author Admins
 */
public class Config {
    public static String DATABASE = "Employee_Manager.s3db";
    public static Connection connection = support.DataBase.ConnectDB(DATABASE);

    public static final String EMPLOYEE_MANAGER = "Quản lý nhân viên";
    public static final String EMPLOYEE_LIST = "Danh sách nhân viên";
    public static final String[] EMPLOYEE_LIST_COLUMN = {"TT", "Mã số", "Họ tên"};
    public static final String EMPLOYEE_SEARCH = "Tìm kiếm";
    public static final String EMPLOYEE_NAME = "Tên nhân viên";
    public static final String EMPLOYEE_ID = "Mã nhân viên";
    public static final String EMPLOYEE_INFORMATION = "Thông tin nhân viên";
    public static final String DEPARTMENT = "Đơn vị";
    public static final String INFORMATION = "Thông tin";
    public static final String SALARY = "Lương";
    public static final String SALARY_WEIGHT = "Hệ số lương";
    public static final String GENDER = "Giới tính";
    public static final String DEPARTMENT_ID = "Mã phòng";
    public static final String POSITION = "Chức vụ";
    public static final String BIRTHDAY = "Ngày sinh";
    public static final String ADDRESS = "Địa chỉ";
    public static final String PHONE_NUMBER = "Điện thoại";
    public static final String IDENTITY_NUMBER = "Số CMND";
    public static final String BANK_ACC = "TK trả lương";

    public static final int FRAME_REFRESH_TIME = 17;
    public static final double RESIZE_RATE = (double) AppWindow.app_size.getHeight()/614;

    public static final String PROJECT_MANAGER = "Quản lý dự án";
    public static final String PROJECT_LIST = "Danh sách dự án";
    public static final String[] PROJECT_LIST_COLUMN = {"TT", "Mã số", "Tên dự án"};
    public static final String PROJECT_NAME = "Tên dự án";
    public static final String PROJECT_ID = "Mã dự án";
    public static final String PROJECT_INFORMATION = "Thông tin dự án";
    public static final String PROJECT_START_TIME = "Bắt đầu";
    public static final String PROJECT_FINISH_TIME = "đến";
    public static final String DESCRIPTION = "Chú thích, miêu tả dự án";

    public static final String PROJECT_START_DATE = "Ngày bắt đầu";
    public static final String PROJECT_FINISH_DATE = "Ngày kết thúc";
    public static final String PROJECT_INPUT = "Nhập dự án";
    public static final String EMPLOYEE_INPUT = "Nhập dự án";
    public static final String SAVE = "Lưu";
    public static final String[] ADD_EMPLOYEE_LIST_COLUMN = {"TT", "Mã số", "Họ tên", "Chọn"};

    public static final String UPDATE_ERROR = "Cập nhật bị lỗi!";
    public static final String UPDATE_SUCCESS = "Đã cập nhật";
    public static final String DELETE_SUCCESS = "Đã xóa !";
    public static final String DELETE_ERROR = "Quá trình xóa xảy ra lỗi !!!";
    public static final String DELETE_CONFIRM = "Có chắc chắn xóa không?";
    public static final String SAVE_SUCCESS = "Thêm mới thành công";
    public static final String SAVE_ERROR = "Thêm mới lỗi! Hãy thử lại";
    public static final String ADD_NEW_EMPLOYEE = "Form thêm nhân viên";
    public static final String[] GENDER_BOX = {"Nam", "Nữ"};

    public static Rectangle resize(Rectangle rect)
    {
        int x = (int)(rect.x * RESIZE_RATE);
        int y = (int)(rect.y * RESIZE_RATE);
        int width = (int)(rect.width * RESIZE_RATE);
        int height = (int)(rect.height * RESIZE_RATE);
        Rectangle new_rect = new Rectangle(x, y, width, height);
        return new_rect;
    }

    public static int resize(int value)
    {
        return (int)( value * RESIZE_RATE);
    }
    public static Dimension resize(Dimension dim)
    {
        return new Dimension((int)(dim.width * RESIZE_RATE), (int)(dim.height * RESIZE_RATE));
    }

    public static void setIcon(JButton label, String fileName)
    {
        try {
            BufferedImage image = ImageIO.read(TabButtonPanel.class.getClassLoader().getResource(fileName));
            int x =label.getPreferredSize().width;
            int y =label.getPreferredSize().height;
            int ix =image.getWidth();
            int iy =image.getHeight();
            int dx=0;
            int dy=0;
            if(x /y > ix /iy){
                dy=y;
                dx=dy*ix /iy;
            }else{
                dx=x;
                dy=dx*iy/ix;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance((int)(dx * 0.4), (int)(dy * 0.4), BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
