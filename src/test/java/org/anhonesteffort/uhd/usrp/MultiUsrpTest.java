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

import org.anhonesteffort.uhd.RxStreamer;
import org.anhonesteffort.uhd.StreamArgs;
import org.anhonesteffort.uhd.types.StreamCommand;
import org.anhonesteffort.uhd.types.TuneRequest;

import org.anhonesteffort.uhd.types.DeviceAddress;
import org.anhonesteffort.uhd.types.RxMetadata;
import org.anhonesteffort.uhd.util.ComplexFloatVector;
import org.anhonesteffort.uhd.util.StringVector;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MultiUsrpTest {

  private Properties properties;
  private String     DEVICE_ARGS;

  @Before
  public void setUp() throws IOException {
    if (properties == null) {
      properties = new Properties();
      properties.load(new FileInputStream("usrp.properties"));

      DEVICE_ARGS = properties.getProperty("device_args");
    }
  }

  @Test
  public void testMake() {
    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    MultiUsrp.build(ADDRESS);
    assert true;
  }

  @Test
  public void testMakeLookupError() {
    final DeviceAddress ADDRESS = new DeviceAddress("name=,serial=nope,type=nope");

    try {

      MultiUsrp.build(ADDRESS);
      assert false;

    } catch (RuntimeException e) {
      assert true;
    }
  }

  @Test
  public void testGetSetMasterClock() {
    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP    = MultiUsrp.build(ADDRESS);

    assert USRP.get_master_clock_rate(0) > 0;
    USRP.set_master_clock_rate(USRP.get_master_clock_rate(0));
  }

  @Test
  public void testClockSources() {
    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP    = MultiUsrp.build(ADDRESS);
    final StringVector  SOURCES = USRP.get_clock_sources(0);

    assert SOURCES.size() > 0;

    for (long i = 0; i < SOURCES.size(); i++) {
      if (!SOURCES.get(i).equals("gpsdo")) {
        USRP.set_clock_source(SOURCES.get(i), 0);
        assert USRP.get_clock_source(0).equals(SOURCES.get(i));
      }
    }
  }

  @Test
  public void testSubDevices() {
    final DeviceAddress ADDRESS    = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP       = MultiUsrp.build(ADDRESS);
    final SubDeviceSpec SET_SUBDEV = new SubDeviceSpec(properties.getProperty("sub_device"));

    USRP.set_rx_subdev_spec(SET_SUBDEV, 0);
    final SubDeviceSpec GET_SUBDEV = USRP.get_rx_subdev_spec(0);

    assert SET_SUBDEV.size() == GET_SUBDEV.size();
    assert SET_SUBDEV.to_string().equals(GET_SUBDEV.to_string());
  }

  @Test
  public void testRxRates() {
    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP    = MultiUsrp.build(ADDRESS);
    final double        RATE0   = 1000000;
    final double        RATE1   = 2000000;

    USRP.set_rx_rate(RATE0, 0);
    assert Math.abs(USRP.get_rx_rate(0) - RATE0) < 0.0001;

    USRP.set_rx_rate(RATE1, 0);
    assert Math.abs(USRP.get_rx_rate(0) - RATE1) < 0.0001;

    USRP.set_rx_rate(RATE0);
    assert Math.abs(USRP.get_rx_rate(0) - RATE0) < 0.0001;

    USRP.set_rx_rate(RATE1);
    assert Math.abs(USRP.get_rx_rate(0) - RATE1) < 0.0001;
  }

  @Test
  public void testRxFrequencies() {
    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP    = MultiUsrp.build(ADDRESS);
    final TuneRequest   TUNE0   = new TuneRequest(Double.valueOf(properties.getProperty("rx_freq1")));
    final TuneRequest   TUNE1   = new TuneRequest(Double.valueOf(properties.getProperty("rx_freq2")));

    assert Math.abs(USRP.set_rx_freq(TUNE0, 0).target_rf_freq() - TUNE0.target_freq()) < 0.01;
    assert Math.abs(USRP.get_rx_freq(0) - TUNE0.target_freq()) < 0.01;

    assert Math.abs(USRP.set_rx_freq(TUNE1, 0).target_rf_freq() - TUNE1.target_freq()) < 0.01;
    assert Math.abs(USRP.get_rx_freq(0) - TUNE1.target_freq()) < 0.01;
  }

  @Test
  public void testRxGain() throws Exception {
    final DeviceAddress ADDRESS     = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP        = MultiUsrp.build(ADDRESS);
    final StringVector  GAIN_NAMES  = USRP.get_rx_gain_names(0);
    final double        GAIN_LEVEL0 = 1;
    final double        GAIN_LEVEL1 = 2;

    assert GAIN_NAMES.size() > 0;

    USRP.set_rx_gain(GAIN_LEVEL0, 0);
    assert Math.abs(USRP.get_rx_gain(0) - GAIN_LEVEL0) < 0.1;

    USRP.set_rx_gain(GAIN_LEVEL1, 0);
    assert Math.abs(USRP.get_rx_gain(0) - GAIN_LEVEL1) < 0.1;
  }

  @Test
  public void testRxBandwidth() {
    final DeviceAddress ADDRESS    = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP       = MultiUsrp.build(ADDRESS);
    final double        BANDWIDTH0 = 1e6;
    final double        BANDWIDTH1 = 2e6;

    USRP.set_rx_bandwidth(BANDWIDTH0, 0);
    assert Math.abs(USRP.get_rx_bandwidth(0) - BANDWIDTH0) < 0.0001;

    USRP.set_rx_bandwidth(BANDWIDTH1, 0);
    assert Math.abs(USRP.get_rx_bandwidth(0) - BANDWIDTH1) < 0.0001;
  }

  @Test
  public void testRxAntenna() {
    final DeviceAddress ADDRESS  = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP     = MultiUsrp.build(ADDRESS);
    final StringVector  ANTENNAS = USRP.get_rx_antennas(0);

    assert ANTENNAS.size() > 0;

    for (long i = 0; i < ANTENNAS.size(); i++) {
      USRP.set_rx_antenna(ANTENNAS.get(i), 0);
      assert USRP.get_rx_antenna(0).equals(ANTENNAS.get(i));
    }
  }

  @Test
  public void testRxSensors() {
    final DeviceAddress ADDRESS      = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP         = MultiUsrp.build(ADDRESS);
    final StringVector  SENSOR_NAMES = USRP.get_rx_sensor_names(0);

    assert SENSOR_NAMES.size() > 0;
    assert USRP.get_rx_sensor(SENSOR_NAMES.get(0), 0).to_pp_string() != null;
  }

  @Test
  public void testMotherboardSensors() {
    final DeviceAddress ADDRESS      = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP         = MultiUsrp.build(ADDRESS);
    final StringVector  SENSOR_NAMES = USRP.get_mboard_sensor_names(0);

    if (SENSOR_NAMES.size() > 0) {
      assert USRP.get_mboard_sensor(SENSOR_NAMES.get(0), 0).to_pp_string() != null;
    }
  }

  @Test
  public void testRxStreamContinuous() throws Exception{
    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP    = MultiUsrp.build(ADDRESS);
    final TuneRequest   TUNE    = new TuneRequest(Double.valueOf(properties.getProperty("rx_freq1")));

    USRP.set_clock_source(properties.getProperty("clock_source"), MultiUsrp.ALL_MBOARDS);
    USRP.set_rx_rate(1000000);
    USRP.set_rx_freq(TUNE, 0);
    USRP.set_rx_gain(0, 0);
    USRP.set_rx_bandwidth(1e6, 0);
    USRP.set_rx_antenna(properties.getProperty("rx_antenna"), 0);

    final StringVector SENSORS = USRP.get_rx_sensor_names(0);
    for (long i = 0; i < SENSORS.size(); i++) {
      if (SENSORS.get(i).equals("lo_locked")) {
        assert USRP.get_rx_sensor("lo_locked", 0).to_bool();
        break;
      }
    }

    final StreamArgs STREAM_ARGS = new StreamArgs("fc32", "sc16");
    final RxStreamer RX_STREAMER = USRP.getRxStream(STREAM_ARGS);

    assert RX_STREAMER.get_num_channels() == 1;

    final StreamCommand STREAM_START_CMD = new StreamCommand(StreamCommand.START_CONTINUOUS);
    USRP.issue_stream_cmd(STREAM_START_CMD, MultiUsrp.ALL_CHANS);

    final ComplexFloatVector SAMPLES = new ComplexFloatVector(20);
    final RxMetadata         RX_META = new RxMetadata();

    final long SAMPLE_COUNT = RX_STREAMER.recv(SAMPLES.front(), SAMPLES.size(), RX_META, 3.0, false);

    assert RX_META.error_code() == RxMetadata.ERROR_NONE;
    assert SAMPLE_COUNT         == SAMPLES.size();

    final StreamCommand STREAM_STOP_CMD = new StreamCommand(StreamCommand.STOP_CONTINUOUS);
    USRP.issue_stream_cmd(STREAM_STOP_CMD, MultiUsrp.ALL_CHANS);

    final long DRAIN_TIMEOUT = System.currentTimeMillis() + 10000;
    while (RX_META.error_code()       != RxMetadata.ERROR_TIMEOUT &&
           System.currentTimeMillis() <= DRAIN_TIMEOUT)
    {
      RX_STREAMER.recv(SAMPLES.front(), SAMPLES.size(), RX_META, 0.1, false);
    }

    assert RX_META.error_code() == RxMetadata.ERROR_TIMEOUT;
  }

  @Test
  public void testRxStreamNumAndDone() throws Exception{
    if (DEVICE_ARGS.contains("usrp1")) {
      System.out.println();
      System.out.println("###########################################################################");
      System.out.println("NOTE: skipping RxStreamer NUM_SAMPS_AND_DONE test, usrp1 does not support.");
      System.out.println("###########################################################################");
      System.out.println();
      return;
    }

    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    final MultiUsrp     USRP    = MultiUsrp.build(ADDRESS);
    final TuneRequest   TUNE    = new TuneRequest(Double.valueOf(properties.getProperty("rx_freq1")));

    USRP.set_clock_source(properties.getProperty("clock_source"), MultiUsrp.ALL_MBOARDS);
    USRP.set_rx_rate(1000000);
    USRP.set_rx_freq(TUNE, 0);
    USRP.set_rx_gain(0, 0);
    USRP.set_rx_bandwidth(1e6, 0);
    USRP.set_rx_antenna(properties.getProperty("rx_antenna"), 0);

    final StringVector SENSORS = USRP.get_rx_sensor_names(0);
    for (long i = 0; i < SENSORS.size(); i++) {
      if (SENSORS.get(i).equals("lo_locked")) {
        assert USRP.get_rx_sensor("lo_locked", 0).to_bool();
        break;
      }
    }

    final StreamArgs STREAM_ARGS = new StreamArgs("fc32", "sc16");
    final RxStreamer RX_STREAMER = USRP.getRxStream(STREAM_ARGS);

    assert RX_STREAMER.get_num_channels() == 1;

    final StreamCommand STREAM_START_CMD = new StreamCommand(StreamCommand.NUM_SAMPS_AND_DONE);
    STREAM_START_CMD.stream_now(true);
    STREAM_START_CMD.num_samps(10);
    USRP.issue_stream_cmd(STREAM_START_CMD, MultiUsrp.ALL_CHANS);

    final ComplexFloatVector SAMPLES = new ComplexFloatVector(STREAM_START_CMD.num_samps());
    final RxMetadata         RX_META = new RxMetadata();

    final long SAMPLE_COUNT = RX_STREAMER.recv(SAMPLES.front(), SAMPLES.size(), RX_META, 3.0, false);

    assert RX_META.error_code() == RxMetadata.ERROR_NONE;
    assert SAMPLE_COUNT         == 10;

    RX_STREAMER.recv(SAMPLES.front(), SAMPLES.size(), RX_META, 3.0, false);

    assert RX_META.error_code() == RxMetadata.ERROR_TIMEOUT;
  }

}
