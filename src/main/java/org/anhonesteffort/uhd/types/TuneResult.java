/**
 * Copyright (C) 2015 An Honest Effort LLC, coping.
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
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
  link    = "uhd",
  include = {"<uhd/types/tune_result.hpp>"}
)
@Name("uhd::tune_result_t")
public class TuneResult extends Pointer {

  static { Loader.load(); }

  public native void target_rf_freq(double hz);
  public native double target_rf_freq();

  public native void actual_rf_freq(double hz);
  public native double actual_rf_freq();

  public native void target_dsp_freq(double hz);
  public native double target_dsp_freq();

  public native void actual_dsp_freq(double hz);
  public native double actual_dsp_freq();

}
