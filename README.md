# cloud-radio-access-network-simulation-platform
CRAN
本团队所在的网络管理研究中心的研究组长期致力于无线网络管理机制和方法的研究，并关注面向未来的新型无线网络关键技术。云无线接入网(Cloud Radio Access Network, CRAN)是5G移动通信网关键技术之一，区别于传统蜂窝网中每个基站独立处理移动用户请求，CRAN将处理用户数据的基带处理单元(BBU)拉远并集中到BBU池进行集中式的管理和维护，能显著减少基站机房数量，降低能耗，实现资源共享和动态调度。
由于CRAN架构研究属于当前移动无线接入网研究的前沿课题，目前常用的仿真工具和平台缺乏相应的仿真功能和模块，为了从仿真平台和工具上支撑研究组对CRAN架构下无线通信网管理技术进行研究，本团队从2012年开始着手该CRAN仿真平台的设计和研发工作，并严格按照软件工程的相关规范，撰写并维护相关开发文档，形成了包含项目开发管理文档、项目代码，可运行软硬件平台在内的一整套产品。该平台为研究组的多项理论研究成果的仿真验证提供了有力的实验保障。
本团队的CRAN仿真平台具有如下特征：
1）平台的界面子系统提供了的较完善的操作和控制功能，便于使用者学习和使用；
2）平台的演示子系统支持动态实时的网络拓扑和状态展示，和交互式网络拓扑编辑操作；
3）平台提供了丰富的CRAN系统资源库，包括光路消耗模型、光路分配模型、基站业务量模型等；
4）平台的运行子系统支持TTI级别实时资源调度和网络状态计算，并支持多个时间尺度的系统级仿真；
5）平台内置较丰富的CRAN典型用例，可以直接导入系统使用；
6）平台的各子系统及资源库采用模块化封装，通过API接口进行信息和指令交互，便于平台功能拓展和二次开发；
基于本平台的论文成果：
1. An Enhanced OFDM Resource Allocation Algorithm in C-RAN Based 5G Public Safety Network	丰雷，喻鹏，李文璟，Mobile Information Systems，2016-7.
2. Self-Healing Based on Cooperative Transmission via Bender's Decomposition in Cloud Radio Access Network，Yin Mengjun; Li Wenjing; Yu Peng; Feng Lei; Qiu Xuesong, China Communications,2015-11.
3. Clustering-based KPI Data Association Analysis Method in Cellular Networks, Xingyu Guo, Peng Yu, Wenjing Li and Xuesong Qiu, NOMS2016, 2016-05.
4. A Handover Statistics based Approach for Cell Outage Detection in Self-organized Heterogeneous Networks, Zhang Tao, Lei Feng, Peng Yu, Wenjing Li IEEE IM 2017
