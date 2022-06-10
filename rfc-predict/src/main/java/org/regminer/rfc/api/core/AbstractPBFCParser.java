package org.regminer.rfc.api.core;


import org.regminer.rfc.model.PotentialBFC;

import java.util.List;

/**
 * @Author: sxz
 * @Date: 2022/06/08/23:17
 * @Description:
 */
public abstract class AbstractPBFCParser {
  public abstract List<PotentialBFC> getPBFCs();
  public abstract PotentialBFC parser();
}
