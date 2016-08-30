/**
 * Copyright (C) 2016 An Honest Effort LLC.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.anhonesteffort.uhd.types;

import org.junit.Test;

public class TuneRequestTest {

  @Test
  public void testConstruct() {
    final double TARGET_FREQ = 1000;

    final TuneRequest tuneRequest = new TuneRequest(TARGET_FREQ);

    assert tuneRequest.target_freq()     == TARGET_FREQ;
    assert tuneRequest.rf_freq_policy()  == TuneRequest.POLICY_AUTO;
    assert tuneRequest.dsp_freq_policy() == TuneRequest.POLICY_AUTO;
  }

  @Test
  public void testConstructWithOffset() {
    final double TARGET_FREQ    = 1000;
    final double LO_OFFSET_FREQ = 2000;

    final TuneRequest tuneRequest = new TuneRequest(TARGET_FREQ, LO_OFFSET_FREQ);

    assert tuneRequest.target_freq()     == TARGET_FREQ;
    assert tuneRequest.rf_freq()         == TARGET_FREQ + LO_OFFSET_FREQ;
    assert tuneRequest.rf_freq_policy()  == TuneRequest.POLICY_MANUAL;
    assert tuneRequest.dsp_freq_policy() == TuneRequest.POLICY_AUTO;
  }

}
