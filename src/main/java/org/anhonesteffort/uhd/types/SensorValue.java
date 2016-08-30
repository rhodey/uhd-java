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
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

@Platform(
  link    = "uhd",
  include = {"<uhd/types/sensors.hpp>"}
)
@Name("uhd::sensor_value_t") @NoOffset
public class SensorValue extends Pointer {

  public static final int
    BOOLEAN = 'b',
    INTEGER = 'i',
    REALNUM = 'r',
    STRING  = 's';

  static { Loader.load(); }

  public SensorValue() { }
  public SensorValue(Pointer p) { super(p); }

  public SensorValue(
    @Const @StdString @ByRef String name,
    @Cast("bool") boolean value,
    @Const @StdString @ByRef String unitTrue,
    @Const @StdString @ByRef String unitFalse
  ) { allocate(name, value, unitTrue, unitFalse); }

  public SensorValue(
    @Const @StdString @ByRef String name,
    int value,
    @Const @StdString @ByRef String unit,
    @Const @StdString @ByRef String formatter
  ) { allocate(name, value, unit, formatter); }

  public SensorValue(
    @Const @StdString @ByRef String name,
    int value,
    @Const @StdString @ByRef String unit
  ) { allocate(name, value, unit); }

  public SensorValue(
    @Const @StdString @ByRef String name,
    double value,
    @Const @StdString @ByRef String unit,
    @Const @StdString @ByRef String formatter
  ) { allocate(name, value, unit, formatter); }

  public SensorValue(
    @Const @StdString @ByRef String name,
    double value,
    @Const @StdString @ByRef String unit
  ) { allocate(name, value, unit); }

  public SensorValue(
    @Const @StdString @ByRef String name,
    @Const @StdString @ByRef String value,
    @Const @StdString @ByRef String unit
  ) { allocate(name, value, unit); }

  private native void allocate(@StdString @ByRef String name,
                               @Cast("bool") boolean value,
                               @StdString @ByRef String unitTrue,
                               @StdString @ByRef String unitFalse);

  private native void allocate(@StdString @ByRef String name,
                               int value,
                               @StdString @ByRef String unit,
                               @StdString @ByRef String formatter);

  private native void allocate(@StdString @ByRef String name,
                              int value,
                              @StdString @ByRef String unit);

  private native void allocate(@StdString @ByRef String name,
                               double value,
                               @StdString @ByRef String unit,
                               @StdString @ByRef String formatter);

  private native void allocate(@StdString @ByRef String name,
                               double value,
                               @StdString @ByRef String unit);

  private native void allocate(@StdString @ByRef String name,
                               @StdString @ByRef String value,
                               @StdString @ByRef String unit);

  public native @Cast("bool") boolean to_bool();
  public native int to_int();
  public native double to_real();

  @MemberGetter public native @StdString String name();
  @MemberGetter public native @StdString String value();
  @MemberGetter public native @StdString String unit();
  @MemberGetter public native @Cast("const uhd::sensor_value_t::data_type_t") int type();

  public native @StdString @ByVal String to_pp_string();

}
