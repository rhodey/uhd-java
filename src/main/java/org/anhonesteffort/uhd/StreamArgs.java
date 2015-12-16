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
package org.anhonesteffort.uhd;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

@Platform(
  link    = "uhd",
  include = {"<uhd/stream.hpp>"}
)
@Name("uhd::stream_args_t")
public class StreamArgs extends Pointer {

  static { Loader.load(); }
  public StreamArgs(@ByRef @StdString String cpu, @ByRef @StdString String otw) {
    allocate(cpu, otw);
  }
  private native void allocate(@ByRef @StdString String cpu, @ByRef @StdString String otw);

  public native @StdString String cpu_format();
  public native void cpu_format(@StdString String cpu);

  public native @StdString String otw_format();
  public native void otw_format(@StdString String otw);

}
