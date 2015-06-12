package com.lts.core.failstore.leveldb;

import com.lts.core.cluster.Config;
import com.lts.core.cluster.NodeType;
import com.lts.core.commons.utils.CollectionUtils;
import com.lts.core.commons.utils.JSONUtils;
import com.lts.core.constant.Constants;
import com.lts.core.domain.Job;
import com.lts.core.domain.KVPair;
import com.lts.core.failstore.FailStore;
import com.lts.core.failstore.FailStoreException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by hugui on 6/12/15.
 */
public class LeveldbFailStoreTest {

    private String key = "23412x";
    FailStore failStore;

    @Before
    public void setup() throws FailStoreException {
        failStore = new LeveldbFailStore("/Users/hugui/.lts/TASK_TRACKER/test_trade_TaskTracker/");
        failStore.open();
    }

    @Test
    public void put() throws FailStoreException {
        Job job = new Job();
        job.setTaskId("2131232");
        failStore.put(key, job);
        System.out.println("这里debug测试多线程");
        failStore.close();
    }

    @Test
    public void fetchTop() throws FailStoreException {
        List<KVPair<String, Job>> kvPairs = failStore.fetchTop(5, Job.class);
        if (CollectionUtils.isNotEmpty(kvPairs)) {
            for (KVPair<String, Job> kvPair : kvPairs) {
                System.out.println(JSONUtils.toJSONString(kvPair));
            }
        }
    }

}