# haiwan
created by qyl
#20171021创建初始工程
开发人员 钱云龙 何永升

工具类说明com.lincomb.haiwan.util<br>
1EnumUtil 根据 status 1 找到具体的中文 展现到前端<br>
2KeyUtil 产生主键的工具类
3返回给前端结果工具类

ALTER TABLE product MODIFY product_checkout_time VARCHAR(64) COMMENT '退房时间';
ALTER TABLE product MODIFY rule_no VARCHAR(16) COMMENT '退票类型';


上线需要更改
1 更改 application中的source 指向
2 更改 对应的file_upload.properties [fastds中有配置指向]

