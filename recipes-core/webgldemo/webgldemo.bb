DESCRIPTION = "WebGL Samples and Examples https://webglsamples.org"

SECTION = "demos"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

S = "${WORKDIR}/git"

SRCREV = "91d806c94c2f7efd098d482fe2a774f98d8dcce8"
SRC_URI = "git://github.com/WebGLSamples/WebGLSamples.github.io.git;protocol=git \
           file://webgldemo.service \
           "

REQUIRED_DISTRO_FEATURES= "systemd"

RDEPENDS_${PN} += "chromium-ozone-wayland rng-tools lprng python"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "webgldemo.service"

do_install () {
           	install -d ${D}/home/root/webgldemo
            cp -arf ${S}/* ${D}/home/root/webgldemo

            install -d ${D}/lib/systemd/system/
            cp -rf ${WORKDIR}/webgldemo.service ${D}/lib/systemd/system/
}

FILES_${PN} += "/home/root/webgldemo"
FILES_${PN} += "/lib/systemd/system"

INSANE_SKIP_${PN} += "host-user-contaminated"
