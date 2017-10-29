package com.lincomb.haiwan.service;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/28 19:04
 */
public interface TimedTasksService {

    /**
     * 已取消订单任务
     */
    void cancelOrderTasks();

    /**
     * 已过期订单任务
     */
    void overtimeOrderTasks();
}
