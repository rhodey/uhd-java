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

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
  link    = "uhd",
  include = {"<uhd/types/tune_request.hpp>"}
)
@Name("uhd::tune_request_t")
public class TuneRequest extends Pointer {

  public static final int
    POLICY_NONE   = 'N',
    POLICY_AUTO   = 'A',
    POLICY_MANUAL = 'M';

  static { Loader.load(); }

  public TuneRequest(double targetFreq) { allocate(targetFreq); }
  public TuneRequest(double targetFreq, double loOff) { allocate(targetFreq, loOff); }

  private native void allocate(double targetFreq);
  private native void allocate(double targetFreq, double loOff);

  public native void target_freq(double hz);
  public native double target_freq();

  public native void rf_freq_policy(@Cast("uhd::tune_request_t::policy_t") int policy);
  public native @Cast("uhd::tune_request_t::policy_t") int rf_freq_policy();

  public native void rf_freq(double hz);
  public native double rf_freq();

  public native void dsp_freq_policy(@Cast("uhd::tune_request_t::policy_t") int policy);
  public native @Cast("uhd::tune_request_t::policy_t") int dsp_freq_policy();

  public native void dsp_freq(double hz);
  public native double dsp_freq();

}
