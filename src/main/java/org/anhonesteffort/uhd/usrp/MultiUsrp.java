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
package org.anhonesteffort.uhd.usrp;

import org.anhonesteffort.uhd.*;
import org.anhonesteffort.uhd.types.MetaRange;
import org.anhonesteffort.uhd.types.StreamCommand;
import org.anhonesteffort.uhd.types.TuneRequest;
import org.anhonesteffort.uhd.types.DeviceAddress;
import org.anhonesteffort.uhd.types.SensorValue;
import org.anhonesteffort.uhd.types.TuneResult;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

import org.anhonesteffort.uhd.util.StringVector;

@Platform(
  link    = "uhd",
  include = {"<uhd/usrp/multi_usrp.hpp>"}
)
@Name("uhd::usrp::multi_usrp")
public class MultiUsrp extends Pointer {

  public final static long   ALL_MBOARDS = 0xFFFFFFFFFFFFFFFFL;
  public final static long   ALL_CHANS   = 0xFFFFFFFFFFFFFFFFL;
  public final static String ALL_GAINS   = "";

  static { Loader.load(); }

  private MultiUsrpSharedPtr ptr;
  private void saveReference(MultiUsrpSharedPtr ptr) {
    this.ptr = ptr;
  }

  private native static @ByVal MultiUsrpSharedPtr make(@ByRef DeviceAddress address);
  public static MultiUsrp build(DeviceAddress address) {
    MultiUsrpSharedPtr multiUsrpPtr = make(address);
    MultiUsrp          multiUsrp    = multiUsrpPtr.get();

    multiUsrp.saveReference(multiUsrpPtr);
    return multiUsrp;
  }

  public native double get_master_clock_rate(@Cast("size_t") long mboard);
  public native void set_master_clock_rate(double rate, @Cast("size_t") long mboard);
  public void set_master_clock_rate(double rate) { set_master_clock_rate(rate, ALL_MBOARDS); }

  public native long get_num_mboards();
  public native @StdString String get_mboard_name(@Cast("size_t") long mboard);

  public native void set_clock_source(@ByRef @StdString String source, @Cast("size_t") long mboard);
  public native @StdString String get_clock_source(@Cast("size_t") long mboard);
  public native @ByVal StringVector get_clock_sources(@Cast("size_t") long mboard);

  public native void set_rx_subdev_spec(@ByRef SubDeviceSpec spec, @Cast("size_t") long mboard);
  public native @ByVal SubDeviceSpec get_rx_subdev_spec(@Cast("size_t") long mboard);

  public native long get_rx_num_channels();

  public native void set_rx_rate(double rate, @Cast("size_t") long chan);
  public void set_rx_rate(double rate) { set_rx_rate(rate, ALL_CHANS); }
  public native double get_rx_rate(@Cast("size_t") long chan);

  public native @ByVal MetaRange get_rx_rates(@Cast("size_t") long chan);

  public native @ByVal TuneResult set_rx_freq(@ByRef TuneRequest tuneRequest, @Cast("size_t") long chan);
  public native double get_rx_freq(@Cast("size_t") long chan);

  public native void set_rx_gain(double gain, @ByRef @StdString String name, @Cast("size_t") long chan);
  public native void set_rx_gain(double gain, @Cast("size_t") long chan);

  public native double get_rx_gain(@ByRef @StdString String name, @Cast("size_t") long chan);
  public native double get_rx_gain(@Cast("size_t") long chan);
  public native @ByVal StringVector get_rx_gain_names(@Cast("size_t") long chan);

  public native void set_rx_bandwidth(double bandwidth, @Cast("size_t") long chan);
  public native double get_rx_bandwidth(@Cast("size_t") long chan);

  public native void set_rx_antenna(@ByRef @StdString String ant, @Cast("size_t") long chan);
  public native @StdString String get_rx_antenna(@Cast("size_t") long chan);
  public native @ByVal StringVector get_rx_antennas(@Cast("size_t") long chan);

  public native @ByVal StringVector get_rx_sensor_names(@Cast("size_t") long chan);
  public native @ByVal SensorValue get_rx_sensor(@ByRef @StdString String name, @Cast("size_t") long chan);

  public native @ByVal StringVector get_mboard_sensor_names(@Cast("size_t") long mboard);
  public native @ByVal SensorValue get_mboard_sensor(@ByRef @StdString String name, @Cast("size_t") long mboard);

  private native @ByVal RxStreamerSharedPtr get_rx_stream(@ByRef StreamArgs args);
  public RxStreamer getRxStream(StreamArgs args) {
    RxStreamerSharedPtr ptr        = get_rx_stream(args);
    RxStreamer          rxStreamer = ptr.get();

    rxStreamer.saveReference(ptr);
    return rxStreamer;
  }

  public native void issue_stream_cmd(@ByRef StreamCommand cmd, @Cast("size_t") long chan);

  public native @StdString String get_pp_string();

  // TX related methods
  private native @ByVal TxStreamerSharedPtr get_tx_stream(@ByRef StreamArgs args);

  public TxStreamer getTxStream(StreamArgs args) {
    TxStreamerSharedPtr ptr        = get_tx_stream(args);
    TxStreamer          txStreamer = ptr.get();

    txStreamer.saveReference(ptr);
    return txStreamer;
  }

  public native double get_tx_freq(@Cast("size_t") long chan);
  public native @ByVal TuneResult set_tx_freq(@ByRef TuneRequest tuneRequest, @Cast("size_t") long chan);

  public native double get_tx_gain(@ByRef @StdString String name, @Cast("size_t") long chan);
  public native double get_tx_gain(@Cast("size_t") long chan);
  public native @ByVal StringVector get_tx_gain_names(@Cast("size_t") long chan);
  public native void set_tx_gain(double gain, @ByRef @StdString String name, @Cast("size_t") long chan);
  public native void set_tx_gain(double gain, @Cast("size_t") long chan);

  public native void set_tx_antenna(@ByRef @StdString String ant, @Cast("size_t") long chan);
  public native void set_tx_bandwidth(double bandwidth, @Cast("size_t") long chan);
  public native void set_tx_rate(double rate, @Cast("size_t") long chan);
  public void set_tx_rate(double rate) { set_tx_rate(rate, ALL_CHANS); }
  public native double get_tx_rate(@Cast("size_t") long chan);
  public native @ByVal MetaRange get_tx_rates(@Cast("size_t") long chan);
}
