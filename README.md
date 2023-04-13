# 一个Angular + Springboot的集成研究

一般而言，前端Angualr开发，后端Springboot，都是运行在不同的服务器上面。比如前端 Apache，后端 直接Java或者其他Java容器。

但是对于简单开发，我想尝试两个同时在一个容器上执行，也就是Springboot的容器上执行

## 目录结构

1,src : Java的代码目录

2,ngsrc ： Anagular的代码目录

## 开发运行方法.

使用MSCode 配合Java扩展和Springboot扩展实现

1, `npm run start` 启动Angualr服务

2, Springboot通过MSCODE界面按钮启动，Debug等

配合ng serve -o参数 以及 launch.json，可以实现同时启动两个服务，并打开浏览器

    {
        "version": "0.2.0",
        "compounds": [
            {
                "name": "Debug All",
                "configurations": [
                    "Start APIApplication",
                    "Start Angular"
                ]
            }
        ],
        "configurations": [
            {
                "type": "java",
                "name": "Start APIApplication",
                "request": "launch",
                "mainClass": "com.exp.springboot.api.APIApplication",
                "projectName": "api_services"
            },
            {
                "type": "node-terminal",
                "command": "npm run start",
                "request": "launch",
                "name": "Start Angular",
            },
        ],
    }

## 编译 打包

1, `ng build` 编译Angualr代码，直接部署到 src/main/resources/static 目录

2, 使用Maven打包，`maven clean package` 直接打包到成果JAR

TODO， 上述两部一次处理

## 单元测试与结合测试
1，Angular 测试

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

2, Springboot 测试

执行 `mvn test` ，会运行 src/test/ 下所有的测试用例

TODO， 上述两部一次处理

## 备考，过程记录
20230409    已经实现了 http://localhost:8080/api/version 访问API http://localhost:8080/访问站点

TODO:为了开发方便CORS设定了*，因为Angular开发时候4200端口，站点不符，但在运行期间端口一致所以可以不考虑,尝试通过MAVEN执行时候修改这个配置

## TODO

1,简易内存数据库特定表CRUD操作

2,尽量优化Angular类
