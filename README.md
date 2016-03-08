# uhd-java

Java wrapper library for the [USRP Hardware Driver](http://code.ettus.com/redmine/ettus/projects/uhd/wiki)
via [JavaCPP](https://github.com/bytedeco/javacpp).

## Install UHD
Install UHD and the UHD development files on your system, Check out
[this guide](http://files.ettus.com/manual/page_install.html)
for detailed install instructions. You may also need to install the
`build-essential` & `libboost-dev` packages.

```
# add-apt-repository ppa:ettusresearch/uhd
# apt-get update
# apt-get install uhd-host build-essential libuhd-dev libboost-dev
```

## Verify UHD
Connect your USRP hardware and run `$ uhd_find_devices`, **you must
be able to do this without root permissions**. The output should look
something like this:

```
linux; GNU C++ version 4.8.4; Boost_105400; UHD_003.009.002-release

--------------------------------------------------
-- UHD Device 0
--------------------------------------------------
Device Address:
    type: b100
    name:
    serial: ABC123DEF
```

## Test configuration
uhd-java contains integration tests that must be run on your actual
USRP hardware to verify proper functionality of the library. Copy the
file `example-usrp.properties` to another file named `usrp.properties`
and edit it as follows:

* **device_args** - address that uniquely identifies your device
* **clock_source** - clock source that will be used for testing
* **rx_antenna** - antenna that will be used to receive samples for testing
* **sub_device** - sub device spec to use for testing
* **rx_freq1/2** - two arbitrary frequencies that your USRP is capable of tuning to

## Install
```
$ mvn install
```

## License

Copyright 2015 An Honest Effort LLC

Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html