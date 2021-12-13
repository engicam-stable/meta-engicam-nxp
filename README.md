
meta-engicam-nxp
================ 

Based on NXP Yocto Gatesgarth 2.3 GA


```
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-gatesgarth -m imx-5.10.9-1.0.0.xml
repo sync
cd sources
git clone https://github.com/engicam-stable/meta-engicam-nxp.git -b gatesgarth
```

SOMs supported
--------------

- imx6ull-gea
- imx6ull-gea-emmc
- imx6ull-microgea
- imx6ull-microgea-y1
- imx8dx-icore
- imx8mm-icore-2gb
- imx8mm-icore
- imx8mp-icore-4gb
- imx8mp-icore
- imx8mp-smarcore
- imx8qx-icore

Supported distros
-----------------

- eng-imx-wayland: Distro for Wayland without X11. This distro includes wayland feature but doesn’t have X11 support.
- eng-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.

Images available
----------------

- engicam-evaluation-image
- engicam-evaluation-image-mx6ull

First build
-----------

```
DISTRO=eng-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```

