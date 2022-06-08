package org.regminer.miner.api.core;

import org.apache.commons.collections4.QueueUtils;
import org.regminer.miner.model.PotentialBFC;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: sxz
 * @Date: 2022/06/08/23:17
 * @Description:
 */
public abstract class AbstractPBFCParser {
  public abstract List<PotentialBFC> getPBFCs();
  public abstract PotentialBFC parser();
}
