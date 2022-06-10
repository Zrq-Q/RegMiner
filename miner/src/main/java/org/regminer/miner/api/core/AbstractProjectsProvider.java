package org.regminer.miner.api.core;

import java.util.PriorityQueue;

/**
 * @Author: sxz
 * @Date: 2022/06/09/01:38
 * @Description:
 */
public abstract class AbstractProjectsProvider {
    public abstract PriorityQueue getProjectList();
}
