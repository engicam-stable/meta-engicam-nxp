SUMMARY = "Custom Regulatory Domain Configuration for Broadcom Wi-Fi"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://brcmfmac.conf"

# Skip configuration and compilation since this is just a text file
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Create the modprobe.d directory on the target rootfs
    install -d ${D}${sysconfdir}/modprobe.d
    
    # Modern Yocto uses UNPACKDIR instead of WORKDIR for local files
    install -m 0644 ${UNPACKDIR}/brcmfmac.conf ${D}${sysconfdir}/modprobe.d/
}

# Tell Yocto to package this specific file
FILES:${PN} += "${sysconfdir}/modprobe.d/brcmfmac.conf"
