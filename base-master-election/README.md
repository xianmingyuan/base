## 1.开启master选举功能

    @EnableMasterElection
    
## 2.配置zookeeper相关信息

    base.master.election.address = 111.231.249.159:2181
    base.master.election.timeout = 5000
    
## 3.在需要使用master选举的方法使用注解

    # path 必须唯一并且要以 / 开头，value 随便给值，目前尚无用途
    # 当该实例方法没有抢占到master时，执行会抛出NoMasterException
    @UseMasterElection(path = "/master-election", value = "1")
    
