
meta-engicam-nxp
================

Based on NXP Yocto hardknott 2.0 GA


Retrieve the sources
--------------------

```
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://github.com/nxp-imx/imx-manifest -b imx-linux-hardknott -m imx-5.10.35-2.0.0.xml
repo sync
```

SOMs supported
--------------

- imx8mp-icore
- imx8mp-icore-2e
- imx8mp-icore-2e-phy [1]
- imx8mm-icore
- imx8mm-icore-2gb
- imx8mp-smarcore
- imx8xd-smarcore
- imx8xq-smarcore
- imx8xd-icore

Supported distros
-----------------

- fsl-imx-wayland: Distro for Wayland without X11. This distro includes wayland feature but doesn’t have X11 support.
- fsl-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.

Images available
----------------

- engicam-evaluation-image

First build
-----------

```
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```



[1] The machine imx8mp-icore-2e-phy apply a kernel patch out of tree. See 0001-mx8mp-icore-2e-net-mac-devicetree-added.patch file under linux-engicam folder.
