# uhd-java
Java wrapper library for the [USRP Hardware Driver](http://code.ettus.com/redmine/ettus/projects/uhd/wiki)
via [JavaCPP](https://github.com/bytedeco/javacpp).

## Install UHD
Install UHD (version `003.009.005`) on your system, check out [this guide](http://files.ettus.com/manual/page_build_guide.html)
for operating system specific instructions. What follows should work on
debian-based systems:
```
$ sudo apt-get install git libboost-all-dev libusb-1.0-0-dev python-mako doxygen python-docutils cmake build-essential
$ git clone https://github.com/ettusresearch/uhd && cd ./uhd
$ git checkout release_003_009_005 -b v003.009.005
$ cd ./host && mkdir build && cd ./build
$ cmake ../
$ make && make test
$ sudo make install && ldconfig
```

## Configure USB Permissions
If your USRP is a member of the "bus series" (has USB) you need to configure
your operating system to mount it as readable by non-root users, on
debian-based systems this can be done easily:
```
$ cd <uhd-root-dir>
$ sudo cp host/utils/uhd-usrp.rules /etc/udev/rules.d/10-uhd.rules
$ sudo udevadm control --reload-rules
$ sudo udevadm trigger
```

## Verify UHD
Connect your USRP hardware and run `$ uhd_find_devices`, **you must be able to
do this without root permissions**. The output should look something like this:
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
uhd-java contains integration tests that must be run on your actual USRP
hardware to verify proper functionality of the library. Copy the file
`example-usrp.properties` to another file named `usrp.properties` and edit it
as follows:

* **device_args** - address that uniquely identifies your device
* **clock_source** - clock source that will be used for testing
* **rx_antenna** - antenna that will be used to receive samples for testing
* **sub_device** - sub device spec to use for testing
* **rx_freq1/2** - two arbitrary frequencies that your USRP is capable of tuning to

## Install uhd-java
```
$ mvn install
```

## License
Copyright 2017 An Honest Effort LLC
Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html
