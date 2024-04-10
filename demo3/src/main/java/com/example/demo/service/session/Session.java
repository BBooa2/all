package com.example.demo.service.session;

import io.netty.channel.Channel;

/**
 * 管理接口
 */

public interface Session {
    /**
     *  绑定用户和 channel
     * @param channel 端口？
     * @param username 用户名
     */
    void bind(Channel channel, String username);

    /**
     *  解绑
     * @param channel channel
     */
    void unbind(Channel channel);

    /**
     * 获取属性
     * @param channel 哪个 channel
     * @param name 属性名
     * @return 属性值
     */
    Object getAttribute(Channel channel, String name);

    /**
     * 设置属性
     * @param channel 哪个 channel
     * @param name 属性名
     * @param value 属性值
     */
    void setAttribute(Channel channel, String name, Object value);

    /**
     * 根据用户名获取 channel
     * @param username 用户名
     * @return channel
     */
    Channel getChannel(String username);

}
