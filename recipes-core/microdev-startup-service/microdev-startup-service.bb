LICENSE = "CLOSED"

REQUIRED_DISTRO_FEATURES= "systemd"

inherit systemd features_check

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "microdev.service"

SRC_URI_append = " file://microdev.service \
		   file://startUpMicrodev.sh "

do_install_append() {
  install -d ${D}/${systemd_unitdir}/system
  install -d ${D}/${bindir}
  install -m 0644 ${WORKDIR}/microdev.service ${D}/${systemd_unitdir}/system
  install -m 0751 ${WORKDIR}/startUpMicrodev.sh ${D}/${bindir}
}

#Pack the path
FILES_${PN} += "${bindir}/startUpMicrodev.sh"
FILES_${PN} += "${systemd_unitdir}/system/microdev.service"
