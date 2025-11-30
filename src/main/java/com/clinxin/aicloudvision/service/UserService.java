package com.clinxin.aicloudvision.service;

import com.clinxin.aicloudvision.model.dto.UserQueryRequest;
import com.clinxin.aicloudvision.model.entity.User;
import com.clinxin.aicloudvision.model.vo.LoginUserVO;
import com.clinxin.aicloudvision.model.vo.UserVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 用户 服务层
 *
 * @author <a href="https://github.com/clinxin">clinxin</a>
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request      HttpServletRequest
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 请求体
     * @return 当前登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request 请求体
     * @return 是否注销成功
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的用户信息
     *
     * @param user 用户信息
     * @return 脱敏后的用户信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息（分页）
     *
     * @param userList 用户信息列表
     * @return 脱敏后的用户信息列表
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return 脱敏后的已登录用户信息
     */
    LoginUserVO getLoginUserVO(User user);


    /**
     * 获取加密后的密码
     *
     * @param userPassword 用户密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);

    /**
     * 根据查询条件构造数据查询参数
     *
     * @param userQueryRequest 用户查询请求参数
     * @return 数据查询参数
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);
}
