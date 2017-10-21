create table buyer
(
	buyer_id varchar(32) not null
		primary key,
	buyer_mobile varchar(16) null comment '用户手机',
	buyer_wechatname varchar(64) null comment '微信昵称',
	buyer_wechatpic varchar(512) null comment '微信头像URL',
	buyer_name varchar(16) null comment '真是姓名',
	is_identity int null comment '是否实名 0无 1有',
	identity_no varchar(64) null comment '身份证号',
	create_time timestamp default CURRENT_TIMESTAMP not null,
	update_time timestamp default CURRENT_TIMESTAMP not null on UPDATE  current_timestamp comment '更新时间'
)
comment '购买者'
;

create table category
(
	category_id int auto_increment
		primary key,
	category_name varchar(64) not null comment '类目名称',
	category_type int not null comment '类目编号',
	create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time timestamp default CURRENT_TIMESTAMP not null on UPDATE  current_timestamp comment '更新时间'
)
;

create table item
(
	item_id varchar(32) not null
		primary key,
	item_name varchar(64) null comment '项名称',
	item_description varchar(512) null comment '项描述',
	product_id varchar(32) null comment '产品ID',
	create_time timestamp default CURRENT_TIMESTAMP not null,
	update_time timestamp default CURRENT_TIMESTAMP not null ON UPDATE current_timestamp
)
;

create table `order`
(
	order_id varchar(32) not null
		primary key,
	product_id varchar(32) not null comment '产品ID',
	category_type int null comment '类目编号',
	product_type int null comment '产品类型',
	product_name varchar(64) null comment '产品名称',
	buyer_id varchar(32) null comment '用户ID',
	buyer_mobile varchar(16) null comment '用户手机',
	order_status int default '0' null comment '订单状态 0新订单 1未使用 2已完结',
	pay_status int default '0' null comment '支付状态 0未支付 1已支付',
	pay_type int default '0' null comment '支付类型 0微信支付',
	order_channel int default '0' null comment '订单渠道 0微信公众号',
	order_count int default '1' null comment '订产品数量',
	order_date_in timestamp default CURRENT_TIMESTAMP not null comment '入住时间',
	order_date_out timestamp default CURRENT_TIMESTAMP not null comment '出去时间',
	order_amount decimal(8,2) null,
	create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time timestamp default CURRENT_TIMESTAMP not null on UPDATE  current_timestamp comment '更新时间'
)
comment '订单'
;

create table photo
(
	photo_id varchar(32) not null
		primary key,
	photo_type int null comment '图片类型',
	photo_name varchar(64) null comment '图片名称',
	photo_url varchar(512) null comment '图片路径',
	product_id varchar(32) null comment '产品Id',
	create_time timestamp default CURRENT_TIMESTAMP not null,
	update_time timestamp default CURRENT_TIMESTAMP not null on UPDATE  current_timestamp comment '更新时间'
)
;

create table product
(
	product_id varchar(32) not null
		primary key,
	product_name varchar(64) not null comment '产品名称',
	product_description varchar(128) null comment '产品描述',
	product_area int null comment '产品面积',
	product_address varchar(64) null comment '产品地址',
	category_type int null comment '类目编号',
	product_type int null comment '规格 0单人 1标准 3vip',
	product_checkout_time timestamp default CURRENT_TIMESTAMP not null comment '退房时间',
	product_pay_type int null comment '支付方式 0线上线下 1线上 2线下',
	product_price decimal(8,2) not null comment '产品价格',
	product_quantity int not null comment '产品数量',
	product_status int default '0' not null comment '产品状态 0上架 1下架',
	is_have_breakfast int default '0' null comment '是否有早饭 0有 1无',
	is_have_bathroom int default '0' null comment '是否有浴室 0有 1无',
	is_have_yard int default '0' null comment '是否有庭院 0有 1无',
	is_have_wifi int default '0' null comment '是否有网络 0有 1无',
	equipment varchar(16) default '0' null comment '设备列表［电视，冰箱，电脑，烧烤，空调，其他］0有 1无',
	others varchar(32) null comment '其他设备',
	refundRuleType int null comment '退票类型',
	product_pic varchar(512) null comment '图片URL',
	create_time timestamp default CURRENT_TIMESTAMP not null,
	update_time timestamp default CURRENT_TIMESTAMP not null on UPDATE  current_timestamp comment '更新时间'
)
comment '产品表'
;

create table refund_rule
(
	rule_id int auto_increment
		primary key,
	rule_no varchar(32) null comment '规则编号',
	rule_type int null comment '退款类型 0无时间限制 1有',
	rule_day int null comment '退款入住前的天数限制',
	rule_description varchar(64) null comment '退款描述',
	rule_discount int null comment '退款折扣 ％',
	create_time timestamp default CURRENT_TIMESTAMP not null,
	update_time timestamp default CURRENT_TIMESTAMP not null on UPDATE  current_timestamp comment '更新时间'
)
comment '退款规则'
;

create table room_user
(
	user_id varchar(32) not null
		primary key,
	buyer_id varchar(32) null comment '购买者ID',
	user_mobile varchar(16) null comment '使用者手机号',
	user_name varchar(32) null comment '使用者姓名',
	user_identity_no varchar(64) null comment '使用者身份证',
	create_time timestamp default CURRENT_TIMESTAMP not null,
	update_time timestamp default CURRENT_TIMESTAMP not null on UPDATE  current_timestamp comment '更新时间'
)
;

