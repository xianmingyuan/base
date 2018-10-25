package com.example.base.master.election;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
@Data
public class MasterElectionProperties {

    @Value("${base.master.election.address:111.231.249.159:2181}")
    private String address;
    @Value("${base.master.election.timeout:5000}")
    private int timeout;

}
