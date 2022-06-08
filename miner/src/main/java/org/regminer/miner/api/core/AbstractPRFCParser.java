package org.regminer.miner.api.core;

import org.regminer.miner.model.PotentialRFC;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author: sxz
 * @Date: 2022/06/08/23:18
 * @Description:
 */
public abstract class AbstractPRFCParser{
    public abstract PriorityBlockingQueue<PotentialRFC> getPRFList();

    public abstract PotentialRFC parser();
}
