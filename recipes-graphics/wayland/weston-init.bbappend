FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

RDEPENDS_${PN} += " bash"

SRC_URI += "file://save-calibration.sh \
	"

do_install_append() {

	if [ ${@bb.utils.contains('MACHINE', 'imx6ull-gea', 'true', '', d)} ]; then 
	    install -d ${D}${bindir}
	    install -d ${D}${sysconfdir}/xdg/weston

	    sed -i -e '/^touchscreen_calibrator.*/a calibration_helper=/usr/bin/save-calibration.sh' ${D}${sysconfdir}/xdg/weston/weston.ini

	    install -m 0777 ${WORKDIR}/save-calibration.sh ${D}${bindir}/save-calibration.sh		
	fi

}

#	    printf "\n[core]\nrequire-input=false\n" >> ${D}${sysconfdir}/xdg/weston/weston.ini 
#	    printf "\n[libinput]\ncalibration_helper=/usr/bin/save-calibration.sh\n" >> ${D}${sysconfdir}/xdg/weston/weston.ini
