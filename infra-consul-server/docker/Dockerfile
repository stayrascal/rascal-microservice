FROM nginx:1.11.10

ADD consul-template /usr/local/bin
RUN mkdir /etc/consul-templates

# 模板文件
ADD files/nginx.tpl /etc/consul-templates/nginx.tpl

ENV CT_FILE /etc/consul-templates/nginx.tpl
ENV NX_FILE /etc/nginx/conf.d/default.conf # 目标文件
ENV SERVICE identity # 注册在Consul的服务名

COPY dist /usr/share/nginx/html

RUN mkdir -p /data/cache

CMD /usr/sbin/nginx -c /etc/nginx/nginx.conf  \
  & CONSUL_TEMPLATE_LOG=debug \
  consul-template -consul-addr=$CONSUL -template "$CT_FILE:$NX_FILE:/usr/sbin/nginx -s reload";
