## Proxy 服务器

### mitmproxy

* Man in the Middle.
* 环境需求： 

> Python3:    version >= 3.6

> pip3：   version 最新18.1

> OS:    CentOS,  Ubuntu TLS , Windows.

* mitmproxy指令:

> mitmproxy: 调试时使用，会像VIM样打开界面，查看每个包

> mitmweb: 会启动站点 http://127.0.0.1:8081，用Web来查看每个包

> mitmdump：线上环境使用，可以保存每个包落成文件。

* 线上使用指令:

```
  nohup mitmdump -p 8765 -s addon.py --set block_global=false &
```

> -p 选择启动端口号

> -s 加载插件，做包的抓取和篡改

> --set block_global=false 不设置在收到包时会报错

* 代码

counter.py 统计包
```
import mitmproxy.http
from mitmproxy import ctx


class Counter:
    def __init__(self):
        self.num = 0

    def request(self, flow: mitmproxy.http.HTTPFlow):
        self.num = self.num + 1
        ctx.log.info("We've seen %d flows" % self.num)
```

attendance.py 包的篡改
```
import mitmproxy.http
from mitmproxy import ctx


class Attendance:

    def request(self, flow: mitmproxy.http.HTTPFlow):

        if flow.request.host != "www.baidu.com":
            return

        ctx.log.info("url: %s" % flow.request.pretty_url)
        ctx.log.info("content: %s" % flow.request.content)

        if flow.request.urlencoded_form and "uid" in flow.request.urlencoded_form and "MeOpen" in flow.request.pretty_url:
            # If there's already a form, one can just add items to the dict:
            ctx.log.info("user: %s" % flow.request.urlencoded_form["uid"])

```

addon.py 做成插件集添加
```
import counter
import attendance

addons = [
    counter.Counter(),
    attendance.Attendance()
]
```


### Client

* 需要安装证书,不安装会出现https网站无法访问，许多应用都无法正常显示

> 连接上Proxy, 访问http://mitm.it，选择自带的证书进行安装

> 如无法访问该站点，如腾讯云阻挡，可以通过邮件方式安装证书，证书目录在 ~/.mitproxy下

#### iOS

* WIFI环境下，设置http 代理即可
* 4G下采用 APN:

> 使用Mac 工具, Applie Configure 2

> 创建新的 描述文件， 选择" 蜂窝移动网络"， “数据APN”, 新建APN指向Proxy

> 通过邮件发送新的描述文件，并在iphone上打开，装载。

> 所有流量都会过此Proxy， 所以仅在需要时开通此APN, 没有用时卸载，再使用时再从邮件安装。 暂未找到“开关”的方法。

#### Android

* WIFI环境下，设置http 代理即可
* 4G下，单独设置APN，比如COPY CMNET,然后添加代理设置

> 一些APK采用自己证书认证，不信任系统的，可能需要破解APK才行。  

> APK自己认证采用的技术为 Pinning. https://www.jianshu.com/p/259c02ac481b


### 附录
#### 一些尝试和失败经历

* 利用VPN：

> 手机(4G & WIFI) -> VPN -> Proxy -> Internet

> 流量定向:   将 VPN 流量，在本机导入 Proxy. 可以使用 iptables

```
iptables -t nat -A PREROUTING -p tcp --dport 8444 -j REDIRECT --to-ports 8888
iptables -t nat -A OUTPUT -p tcp -d localhost --dport 8444 -j REDIRECT --to-ports 8888
```

> 当前主流VPN搭建有  SS 和 OpenVPN. 二者都有自己的客户端，它们均要在客户端本地搭建一个Server, 代理所有4G流量

> 在使用SS导向流量时，mitmproxy接收到的http请求，URL会被 BASE64 encode，所以解析不出来

#### 用 netty 自己实现 Proxy Server.

* 核心思想:   搭建Server 接收http请求，再用 Client转发该请求并跟进后续Response.
* netty 官方例子:
https://github.com/netty/netty/tree/4.1/example/src/main/java/io/netty/example/proxy

#### 用到的几个命令
```
netstat -apn | grep LISTEN

nslookup www.baidu.com
```
