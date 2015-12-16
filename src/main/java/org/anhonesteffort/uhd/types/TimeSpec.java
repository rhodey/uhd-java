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
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
  link    = "uhd",
  include = {"<uhd/types/time_spec.hpp>"}
)
@Name("uhd::time_spec_t")
public class TimeSpec extends Pointer {

  static { Loader.load(); }

  public TimeSpec(double seconds) { allocate(seconds); }
  public TimeSpec(@Cast("time_t") long seconds, double frac_seconds) { allocate(seconds, frac_seconds); }
  public TimeSpec(@Cast("time_t") long seconds, long tick_count, double tick_rate) { allocate(seconds, tick_count, tick_rate); }

  private native void allocate(double seconds);
  private native void allocate(@Cast("time_t") long seconds, double frac_seconds);
  private native void allocate(@Cast("time_t") long seconds, long tick_count, double tick_rate);

  public native static @ByVal TimeSpec get_system_time();
  public native static @ByVal TimeSpec from_ticks(long ticks, double tick_rate);

  public native long get_tick_count(double tick_rate);
  public native long to_ticks(double tick_rate);
  public native double get_real_secs();
  public native @Cast("time_t") long get_full_secs();
  public native double get_frac_secs();

}
